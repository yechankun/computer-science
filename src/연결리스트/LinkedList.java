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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
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
