package 배열리스트;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;


public class ArrayList<E> implements List<E>, Cloneable {
    // 배열리스트(ArrayList)를 구현해본다.
    // java의 ArrayList는 RandomAccess, java.io.Serializable를 추가로 구현한다.
    
    // 기본 배열리스트 크기
    private static final int DEFAULT_CAPACITY = 10;

    // 빈 배열의 경우 인스턴스를 공유하기 위한 배열 인스턴스
    private static final Object[] EMPTY_ARRAY = {};
    
    // 요소를 담을 배열
    Object[] array; 

    // 배열을 크기를 추적하는 크기
    private int size;

    public ArrayList(int capacity){
        if (capacity == 0){
            this.array = EMPTY_ARRAY;
        } else {
            this.array = new Object[capacity];
        }
    }

    public ArrayList(){
        this.array = EMPTY_ARRAY;
    }

    /**
     * 크기를 어레이의 크기에 맞추는 메소드
     */
    public void trimToSize(){
        final Object[] a = array;
        if (size < a.length){
            array = (size == 0) ? EMPTY_ARRAY : Arrays.copyOf(a, size);
        }
    }

    // 어레이의 크기를 키우는 메소드
    private Object[] growUp(int minCapacity){
        int oldCapacity = array.length;
        if(oldCapacity > 0){
            // 현재 배열 크기의 절반만큼 키우거나, minCapacity만큼 키움
            int newCapacity = oldCapacity + Math.max(minCapacity - oldCapacity, oldCapacity >> 1);

            if(newCapacity < 0){
                throw new OutOfMemoryError(
                    "메모리 용량 초과: " + oldCapacity + " + " + newCapacity + "의 배열로는 확장할 수 없습니다."
                );
            }else{
                return array = Arrays.copyOf(array, newCapacity);
            }
        }else{
            return array = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }
        
    // 어레이의 크기를 1만 키우기 위한 다형성 메소드
    private Object[] growUp(){
        return growUp(this.size+1);
    }

    // 다형성 디자인을 쉽게 하기 위한 헬퍼 메소드
    private void add(E element, Object[] array, int s) {
        if (s == array.length)
            array = growUp();
        array[s] = element;
        size = s + 1;
    }

    private void checkIndexRange(int index){
        if(size < index || index < 0)
            throw new IndexOutOfBoundsException(
                "인덱스 위치 밖입니다. Size: " + size + ", Index: " + index
                );
    }


    @Override
    public boolean add(E element) {
        add(element, this.array, size);
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndexRange(index);
        final int s;
        // 배열이 풀이라면 사이즈 키우기
        if ((s = size) == array.length)
            array = growUp();
        // 배열을 한 칸 뒤로 민다. 
        System.arraycopy(array, index, array, index+1, s-index);
        array[index] = element;
        size++;
    }

    //콜렉션의 모든 것을 추가한다.
    @Override
    public boolean addAll(Collection<? extends E> c) {
        final int addSize;
        if ((addSize = c.size()) == 0)
            return false;
        Object[] addArray = c.toArray();
        // 기존 배열의 사이즈를 키움
        final int s;
        if (array.length - (s = size) < addSize){
            array = growUp(s + addSize);
        }
        System.arraycopy(addArray, 0, array, s, addSize);
        size = size + addSize;       
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndexRange(index);
        final int addSize;
        if ((addSize = c.size()) == 0)
            return false;
        Object[] addArray = c.toArray();
        // 기존 배열의 사이즈를 키움
        final int s;
        if (array.length - (s = size) < addSize)
            array = growUp(s + addSize);
        
        // 밀어야하는 인덱스만큼 밀어냄
        int moveNum = s - index;
        if (moveNum > 0)
            System.arraycopy(array, index, array, index+addSize, moveNum);
        System.arraycopy(addArray, 0, array, index, addSize);
        size = s + addSize;       
        return true;
    }

    // 배열리스트의 값을 모두 빈 값으로 초기화
    @Override
    public void clear() {
        final Object[] a = array;
        for (int to = size, i = size = 0; i < to; i++)
            a[i] = null;
    }

    // 범위를 탐색해서 주어진 오브젝트와 같은 인덱스를 반환
    int indexOfRange(Object o, int start, int end) {
        Object[] a = array;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (a[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(a[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 범위를 탐색해서 주어진 오브젝트와 같은 인덱스를 반환
    int lastIndexOfRange(Object o, int start, int end) {
        Object[] a = array;
        if (o == null) {
            for (int i = end - 1; i >= start; i--) {
                if (a[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = end - 1; i >= start; i--) {
                if (o.equals(a[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 오브젝트의 인덱스를 앞에서부터 탐색해서 반환
    @Override
    public int indexOf(Object o){
        return indexOfRange(o, 0, size);
    }

    // 오브젝트의 인덱스를 뒤에서부터 탐색해서 반환
    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    // 해당 값을 포함하고 있는지 체크
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    // 전체를 포함하고 있는지 체크
    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object iter : c){
            if (!contains(iter))
                return false;
        }
        return true;
    }
    
    // 인덱스의 값을 변경
    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        checkIndexRange(index);
        E oldValue = (E) array[index];
        array[index] = element;
        return oldValue;
    }

    // 인덱스의 값을 반환
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        checkIndexRange(index);
        return (E) array[index];
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    
    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    // 이터레이터 인터페이스를 구현한 이너클래스를 정의
    // 이터레이터를 상송한 이너클래스를 구현해 이터레이터 인스턴스를 반환해야만 이터레이터 문법을 활용할 수 있음.
    private class Itr implements Iterator<E> {
        // 다음으로 리턴되어야 할 요소의 위치
        int cursor;
        // 마지막으로 리턴된 요소의 위치; -1아면 리턴된 것이 없음
        int lastRet = -1; 
        // 합성 생성자를 방지함. 이거 없어도 되지만 이게 있는 것이 최적화 관점에서 더 좋음
        // JVM이 기본 생성자가 없으면 이너 클래스에서 합성생성자를 자동으로 만들어서 메모리의 낭비가 발생함
        Itr() {}

        // 커서가 마지막 위치인지 체크
        public boolean hasNext() {
            return cursor != size;
        }

        // 커서를 다음으로 넘김, 자바는 python의 __iter__구현으로 주로 쓰는 yield와 다르게 클래스와 필드를 활용한다.
        @SuppressWarnings("unchecked")
        public E next() {
            // 커서 위치 임시 저장
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] objects = ArrayList.this.array;
            if (i >= objects.length)
                throw new ConcurrentModificationException();
            // 커서 위치 증가
            cursor = i + 1;
            // 임시 저장 커서 위치 값 반환
            return (E) objects[lastRet = i];
        }

        // 마지막 반환된 인덱스를 삭제함
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        // 함수형 프로그래밍을 구현하기 위해 도입된 것
        // action은 함수나 람다식의 일종이고 action의 동작을 이터레이터의 데이터를 통해 에러가 나거나 남는 데이터가 없을 때까지 처리한다.
        @SuppressWarnings("unchecked")
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            // Null 이 들어오면 에러 반환
            Objects.requireNonNull(action);
            final int s = ArrayList.this.size;
            int i = cursor;
            // 커서가 사이즈보다 작으면 시작
            if (i < s) {
                final Object[] objects = array;
                // 동시성 문제로 array의 크기보다 i의 크기가 크면 에러
                if (i >= objects.length)
                    throw new ConcurrentModificationException();
                for (; i < s; i++)
                    action.accept((E) array[i]);
                cursor = i;
                lastRet = i - 1;
            }
        }
    }

    // Iterator의 구현을 확장하는 자바의 리스트 이터레이터
    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }
        // 거꾸로 순회할 수도 있기 때문에 Previous를 체크한다
        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        // 거꾸로 순회한다.
        @SuppressWarnings("unchecked")
        public E previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] objects = ArrayList.this.array;
            if (i >= objects.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) objects[lastRet = i];
        }

        // 이 확장은 마지막으로 반환한 인덱스의 값을 수정할 수 있다.
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                ArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        // 현재 커서의 위치에 값을 추가한다.
        public void add(E e) {
            try {
                int i = cursor;
                ArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private void fastRemove(Object[] objects, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(objects, i + 1, objects, i, newSize - i);
        objects[size = newSize] = null;
    }


    // 처음 조회되는 특정 값 삭제
    @Override
    public boolean remove(Object o) {
        final Object[] objects = array;
        int i = indexOf(o);
        if (i < 0)
            return false;
        fastRemove(objects, i);
        return true;
    }

    // 특정 인덱스 삭제 후 값 반환
    @Override
    public E remove(int index) {
        checkIndexRange(index);
        final Object[] objects = array;

        @SuppressWarnings("unchecked") 
        E removeValue = (E) objects[index];

        fastRemove(objects, index);

        return removeValue;
    }

    // false면 컬렉션과 범위 내 일치하는 것을 모두 제거, true면 일치하지 않는 것을 모두 제거
    boolean batchRemove(Collection<?> c, boolean complement,
                        final int from, final int end) {
        Objects.requireNonNull(c);
        final Object[] objects = array;
        int i;
        // 모두 complement와 같으면 삭제할 필요가 없기 때문에 false를 반환함
        // 끝까지 탐색하면서 다른 것이 있으면 탈출하고 삭제작업 시작
        for (i = from;; i++) {
            if (i == end)
                return false;
            if (c.contains(objects[i]) != complement)
                break;
        }
        // 처음으로 삭제할 값의 인덱스 저장, i는 그 다음부터 시작함
        int w = i++;
        try {
            for (Object e; i < end; i++)
                // 살아남아야 하는 값을 삭제할 위치로 옮김. (당김)
                if (c.contains(e = objects[i]) == complement)
                    objects[w++] = e;
        } catch (Throwable ex) {
            // 만약 에러가 발생하면 지금까지 한 작업을 보존함
            System.arraycopy(objects, i, objects, w, end - i);
            w += end - i;
            throw ex; //에러를 반환함
        } finally {
            // 앞으로 값들을 당긴만큼 뒤에 남은 쓸모 없는 값들을 모두 삭제함
            System.arraycopy(objects, end, objects, w, size - end);
            for (int to = size, j = (size -= end - w); i < to; i++)
                objects[j] = null;
        }
        return true;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        return batchRemove(c, false, 0, size);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return batchRemove(c, true, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    // 서브리스트 구현은 기존 Java와 다르게 커스터마이징 함.
    // 기존 자바의 구현은 무겁고 메모리 누수가 발생하기 때문에 아예 새로운 리스트를 반환하게 변경
    @SuppressWarnings("unchecked")
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        checkIndexRange(fromIndex);
        checkIndexRange(toIndex);
        List<E> list = new ArrayList<>();

        List<E> elements = (List<E>) Arrays.asList(Arrays.copyOfRange(array, fromIndex, toIndex));
        list.addAll(elements);

        return list;
    }

    // 내부 배열을 복사해서 반환함
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }


    // 내부 배열을 복사하고 매개 변수로 들어온 배열의 값을 수정한 배열을 반환함
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // a의 길이가 size보다 작을 경우 새로운 배열을 반환함
            // 주소값 불일치의 문제가 발생할 수 있음.
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        // 그게 아니면 기존 배열을 바꿈
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    // 배열리스트 복사
    @Override
    protected Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.array = Arrays.copyOf(array, size);
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    // ToString() 오버라이드
    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
    
    
}
