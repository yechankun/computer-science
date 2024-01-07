package 연결리스트;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.w3c.dom.Node;

/**
 * java.util.LinkedList의 구현을 직접 보고 내가 구현한 양방향연결리스트와
 * 어떻게 다른지를 살펴보자
 * 일단 편의로 생략했던 내 구현과 달리 제네릭 클래스로 선언되어있다.
 * 또한 ArrayList와 동일하기 List 인터페이스를 가지고 있다. 별도로 Deque가 있고
 * 이번엔 Cloneable과 Serializable(ArrayList에선 생략했던) 인터페이스도 둘 다 구현해보고자 한다.
 *  */ 
public class LinkedList<E> extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, Serializable{

    /**
     * size는 직렬화된 오브젝트가 인스턴스화 활 때 다시 정해지므로
     * transient 키워드가 사용된다.
     */
    transient int size = 0;

    /**
     * 첫번째 노드의 주소 포인터.
     * 여기서 벌써 다른 점은 java.util에선 Node가 정적 이너 클래스로 선언되어 있다.
     */
    transient Node<E> first;

    /**
     * 마지막 노드에 대한 주소 포인터.
     */
    transient Node<E> last;

    /**
     * 노드에 대한 선언이다.
     * 노드들은 각각 자신의 데이터와
     * 이전과 다음에 대한 주소 포인터를 가지고 있다.
     */
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.data = element;
            this.next = next;
            this.prev = prev;
        }
    } 

    /**
     * 빈 리스트에 대한 생성자이다.
     * 이 선언이 없으면 자동으로 빈리스트에 대한 선언을 추가해버린다.
     */
    public LinkedList(){

    }

    /**
     * 특정 자료형의 요소들을 가진 콜렉션으로 LinkedList를 초기화 함
     * @param c
     * @throws NullPointerException addAll에서 null포인트 오류를 체크함
     */
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    

    @Override
    public ListIterator<E> listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * LinkedList의 super클래스인 AbstractSequentialList의 clone클래스를 가져온다.
     * clone은 기본적으로 Object의 native clone 메서드를 활용하며 이는 C++로 구현된 부분이다.
     * jdk17 아카이브에서 공개된 것은 다음뿐이며
     * https://github.com/openjdk/jdk17/blob/master/src/java.base/share/native/libjava/Object.c
     * 
     * https://github.com/cscott/Harpoon/blob/bcec08dbbaed226fe653203e18d6a2c3a8b105a9/Runtime/src/sunjdk/java.lang/java_lang_Object.h#L31
     * https://github.com/cscott/Harpoon/blob/bcec08dbbaed226fe653203e18d6a2c3a8b105a9/Runtime/src/sunjdk/java.lang/java_lang_Object.c#L39C27-L39C54
     * https://github.com/cscott/Harpoon/blob/bcec08dbbaed226fe653203e18d6a2c3a8b105a9/Runtime/src/java.lang/object.h#L12
     * 자바 객체의 복제는 fni_object_cloneHelper라는 함수에 의해서 핵심 기능이 수행된다.
     * 1. FNI_Alloc을 통해 jobject clone 변수에 변수공간을 할당한다.
     * FNI_Alloc에서부터 어렵게 느껴지는데 JNIEnv *env, struct FNI_classinfo *info, struct claz *claz, void *(*allocfunc)(size_t length), size_t length
     * 를 매개변수로 받고 info는 null이어도 되지만, claz는 assert로 체크되며 null이면 안된다. claz는 클래스 정보를 포함하는 구조체다.
     * void *(*allocfunc)(size_t length) 커스텀 allocfunc를 쓸 때 사용되는 매개변수이고 null을 넣으면 기본적으로 FNI_RawAlloc가 사용된다.
     * FNI_RawAlloc는 FNI_RawAlloc의 선언부 위에 구현되어 있고 여러 조건을 다 체크해서 메모리 할당이 이루어진다.
     * 기본적으로 java는 GC를 활용하기 때문에 할당하는 메모리를 기억해야하기 때문이다.
     * 
     * WITH_CLUSTERED_HEAPS 플래그가 있으면 NGBL_malloc함수를 호출해서 전역 힙에 저장을 한다.
        NGBL_malloc 함수에는 UPDATE_NIFTY_STATS매크로가 있는데 이는 gbl이라는 텍스트를 매크로와 size를 전달해 통계 변수들을 지정한다.
        NGBL_malloc은 여러 스레드에서 호출될 수 있고 따라서 UPDATE_NIFTY_STATS매크로의 gbl이란 통계변수는 공유변수이므로 Lock 매커니즘을 활용한다.
        
        그 다음 WITH_PRECISE_GC 플래그가 정의되어 있다면 precise_malloc함수를 호출하는데 이는 자바를 위한 정확한 할당용 함수이다.
        이는 여러 WITH_COPYING_GC, WITH_MARKSWEEP_GC, WITH_GENERATIONAL_GC와 같은 매크로를 검사해 internal_malloc가 가르키는 함수를 치환시킨다.
        각 GC의 구현부는 나중에 따로 더 정확히 공부해봐야할 듯 하다. 웹에서 GC에 대한 설명을 추상적으로 보여주긴 하지만 한 번도 코드로 정확히 본 적은 없다.
        
        WITH_PRECISE_GC 플래그가 정의되어 있지 않다면 BDW_CONSERVATIVE_GC 플래그를 체크하고 BDW Garbage Collector 오픈소스를 활용한다.
        GC_malloc이라고 하는 함수를 호출하며 주어진 포인터 영역에서 유효한 포인터로 추정하는 방식을 활용한다고 한다.
        
        그것도 정의되어 있지 않다면 시스템 기본인 C++의 malloc(size)를 사용한다.
     * 
     * WITH_CLUSTERED_HEAPS가 없고 WITH_REALTIME_JAVA 플래그가 있다면
        RTJ_malloc(length) 란 함수를 호출한다.
        이는 RealTime(실시간)에서 활용되기 위해 고안된 자바 전용 malloc 함수이며 스레드의 현재 메모리 블록을 찾아서 할당한다.
        자바 파일이 컴파일될 때 --with-realtime-java라는 옵션이 지정되어 있어야 한다.
        하지만 이는 RTSJ가 적용되어 있는 JVM에서만 구동하며 이는 매우 옛날버전에서만 동작한다. 따라서 걍 못 쓴다고 보면 된다.
     * 
     * WITH_REALTIME_JAVA도 없고 WITH_PRECISE_GC 플래그가 있다면
        precise_malloc(length) 함수를 호출한다. WITH_CLUSTERED_HEAPS과 다른 점이 통계변수를 저장하는 것 말곤 거의 모두 같다.

     * WITH_PRECISE_GC도 없고 BDW_CONSERVATIVE_GC 플래그가 있다면
     * WITH_GC_STATS플래그가 있다면 GC_malloc_stats(length)를 호출하고 없다면 GC_malloc(length)를 호출한다
     * 
     * 위 모든 과정이 거짓이라면 그냥 시스템 기본 할당인 malloc(length)를 활용한다.
     * 
     * 대부분의 경우엔 WITH_PRECISE_GC 플래그가 활성화되고 저것이 실행된다고 보면 된다.
     * 그렇지 않으면 할당된 메모리가 해제되지 않는 메모리 누수 문제가 발생할 수 있다.
     * 
     * 살펴본 바로는 C++ 기본 malloc으로 할당되었고 WITH_GENERATIONAL_GC플래그가 없다면 논리상으론 메모리 누수가 발생할 수 있는 것으로 보인다.
     * 기본적으로 WITH_GENERATIONAL_GC플래그가 있다면 
     * fni_object_cloneHelper함수에서 add_to_curr_obj_list(FNI_UNWRAP_MASKED(clone));를 사용해 할당된 포인터를 추적하지만
     * WITH_GENERATIONAL_GC플래그가 없다면 하지 않는다. 해당 플래그들을 전부 사용하지 않는 JVM이 있다면? 메모리 누수가 발생할 것이다.     * 
     * @return Object에서 상속된 clone메서드로 복사된 {@code LinkedList<E>}의 인스턴스를 반환한다.
     * @throw CloneNotSupportedException 
     */
    @SuppressWarnings("unchecked")
    private LinkedList<E> superClone() {
        try {
            return (LinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     * 이 링크드리스트에 대한 얕은 복사를 반환한다.
     * 각각의 요소들 자체가 복사되는 것은 아니다.
     *
     * @return 얕은 복사가 된 {@code LinkedList} instance 반환
     */
    @Override
    public Object clone() {
        LinkedList<E> clone = superClone();

        // clone 변수에 초기 상태를 추가한다.
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // clone 변수에 add메서드로 전체 요소를 끝까지 추가한다.
        for (Node<E> x = first; x != null; x = x.next)
            clone.add(x.data);

        return clone;
    }

    @Override
    public void addFirst(E e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addLast(E e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<E> descendingIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E element() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E getFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E getLast() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean offer(E e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean offerFirst(E e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public E peek() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E peekFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E peekLast() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E poll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E pollFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E pollLast() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E pop() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void push(E e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public E remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E removeFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public E removeLast() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    
    
}
