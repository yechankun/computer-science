package 큐;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 자바 컬렉션 프레임워크의 일부로
 * Queue를 상속해서 이를 양방향 큐(데크)로 확장하는 Deque 인터페이스다.
 */
public interface Deque<E> extends Queue<E> {
    /**
     * 특정 원소를 큐의 앞에 삽입한다. 
     * 
     * @param e 추가할 원소
     * @throws IllegalStateException 용량제한으로 이번에 원소를 추가하지 못하는 경우
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException null 원소를 삽입할 경우
     * @throws IllegalArgumentException 이 요소의 일부 속성이 잘못되었을 경우
     */
    void addFirst(E e);

    /**
     * 특정 원소를 큐의 뒤에 삽입한다. 
     * 
     * @param e 추가할 원소
     * @throws IllegalStateException 용량제한으로 이번에 원소를 추가하지 못하는 경우
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException null 원소를 삽입할 경우
     * @throws IllegalArgumentException 이 요소의 일부 속성이 잘못되었을 경우
     */
    void addLast(E e);

    /**
     * 특정 원소를 큐에 앞에 삽입한다. 
     * 대부분의 경우에 {@link #addFirst}보다 바람직함
     * @param e 추가할 원소
     * @return {@code true} 원소 추가가 성공시 true를 반환하고 실패하면 {@code false} 반환
     * @throws IllegalStateException 용량제한으로 이번에 원소를 추가하지 못하는 경우
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException null 원소를 삽입할 경우
     * @throws IllegalArgumentException 이 요소의 일부 속성이 잘못되었을 경우
     */
    boolean offerFirst(E e);

    /**
     * 특정 원소를 큐에 뒤에  삽입한다. 
     * 대부분의 경우에 {@link #addLast}보다 바람직함
     * @param e 추가할 원소
     * @return {@code true} 원소 추가가 성공시 true를 반환하고 실패하면 {@code false} 반환
     * @throws IllegalStateException 용량제한으로 이번에 원소를 추가하지 못하는 경우
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException null 원소를 삽입할 경우
     * @throws IllegalArgumentException 이 요소의 일부 속성이 잘못되었을 경우
     */
    boolean offerLast(E e);

    /**
     * 큐의 맨 앞 원소를 삭제하고 반환한다.
     * {@link #pollFirst}과 다르게 큐가 비어있으면 에러를 반환한다.
     *
     * @return 큐의 맨 앞 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E removeFirst();

    /**
     * 큐의 맨 뒤 원소를 삭제하고 반환한다.
     * {@link #pollLast}과 다르게 큐가 비어있으면 에러를 반환한다.
     *
     * @return 큐의 맨 앞 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E removeLast();

    /**
     * 큐의 맨 앞 원소를 삭제하고 반환한다.
     *
     * @return 큐의 맨 앞 원소, 비어있을 경우 {@code null} 반환
     */
    E pollFirst();

    /**
     * 큐의 맨 뒤 원소를 삭제하고 반환한다.
     *
     * @return 큐의 맨 뒤 원소, 비어있을 경우 {@code null} 반환
     */
    E pollLast();

    /**
     * 큐의 맨 앞 값을 반환한다.
     * {@link #peekFirst}와 다르게 큐가 비어있으면 에러를 반환한다.
     * 
     * @return 큐의 맨 앞 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E getFirst();

    /**
     * 큐의 맨 뒤 값을 반환한다.
     * {@link #peekLast}와 다르게 큐가 비어있으면 에러를 반환한다.
     * 
     * @return 큐의 맨 뒤 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E getLast();

    /**
     * 큐의 맨 앞 값을 반환한다.
     * 
     * @return 큐의 맨 앞 원소, 비어있을 경우 {@code null} 반환
     */
    E peekFirst();

    /**
     * 큐의 맨 뒤 값을 반환한다.
     * 
     * @return 큐의 맨 뒤 원소, 비어있을 경우 {@code null} 반환
     */
    E peekLast();


    /**
     * 입력된 오브젝트의 값과 동일한 값을 가진 노드를 탐색하여 처음 일치하는 노드를 제거
     * 
     * @param o 제거할 노드의 값
     * @return 삭제를 성공할 경우 {@code true}, 실패할 경우 {@code false} 반환
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException 매개변수가 null인 경우 경우
     */
    boolean removeFirstOccurrence(Object o);

    /**
     * 입력된 오브젝트의 값과 동일한 값을 가진 노드를 뒤에서부터 탐색하여 처음 일치하는 노드를 제거
     * 
     * @param o 제거할 노드의 값
     * @return 삭제를 성공할 경우 {@code true}, 실패할 경우 {@code false} 반환
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException 매개변수가 null인 경우 경우
     */
    boolean removeLastOccurrence(Object o);

    // *** 큐 메소드 ***

    /**
     * 특정 원소를 큐에 삽입한다.
     * 용량이 제한된 Deque를 사용할 경우 {@link #offer(Object)}를 사용하는 것이 좋음
     * 
     * @param e 추가할 원소
     * @return {@code true} {@link Collection#add}에 정의된 것처럼 true 반환
     * @throws IllegalStateException 용량제한으로 이번에 원소를 추가하지 못하는 경우
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException null 원소를 삽입할 경우
     * @throws IllegalArgumentException 지정된 원소의 일부 속성으로 인해 해당 원소가 이 deque에 추가되지 않는 경우
     */
    boolean add(E e);

    /**
     * 특정 원소를 큐에 뒤에  삽입한다. 
     * {@link #offerLast}와 동일한 기능을 수행함.
     * 
     * @param e 추가할 원소
     * @return {@code true} 원소 추가가 성공시 true를 반환하고 실패하면 {@code false} 반환
     * @throws IllegalStateException 용량제한으로 이번에 원소를 추가하지 못하는 경우
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException null 원소를 삽입할 경우
     * @throws IllegalArgumentException 지정된 원소의 일부 속성으로 인해 해당 원소가 이 deque에 추가되지 않는 경우
     */
    boolean offer(E e);


    /**
     * 큐의 맨 앞 원소를 삭제하고 반환한다.
     * {@link #removeFirst()}와 같은 의미를 가짐
     *
     * @return 큐의 맨 앞 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E remove();


    /**
     * 큐의 맨 앞 원소를 삭제하고 반환한다.
     * {@link #pollFirst()}와 같은 의미를 가짐
     *
     * @return 큐의 맨 앞 원소, 비어있을 경우 {@code null} 반환
     */
    E poll();


    /**
     * 큐의 맨 앞 값을 반환한다.
     * {@link #getFirst()}와 같은 의미를 가짐
     * 
     * @return 큐의 맨 앞 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E element();

    /**
     * 큐의 맨 앞 값을 반환한다.
     * {@link #peekFirst()}와 같은 의미를 가짐
     * 
     * @return 큐의 맨 앞 원소, 비어있을 경우 {@code null} 반환
     */
    E peek();

    /**
     * 이 deque의 마지막에 정의된 컬렉션의 모든 원소를 추가함, 
     * 컬렉션의 Iterator가 반환하는 목록순으로 삽입됨
     * 각각 {@link #addLast}를 호출하게 구현할 수 있음
     *
     * 용량이 제한된 Deque를 구현하는 경우
     * {@link #offer(Object) offer}를 사용하게 구현할 수도 있음
     *
     * 요소를 추가하는 동안 예외가 발생하면 
     * 관련 예외가 발생했을 때 일부 요소만 성공적으로 추가될 수 있음
     *
     * @param index 지정된 컬렉션의 첫 번째 요소를 삽입할 인덱스
     * @param c 이 목록에 추가할 요소가 포함된 컬렉션
     * @return {@code true} 호출의 결과로 이 목록이 변경된 경우 반환
     * @throws IllegalStateException 삽입 제한으로 인해 현재 모든 요소를 ​​추가할 수 없는 경우
     * @throws NullPointerException 지정된 컬렉션이 null인 경우
     * @throws ClassCastException 지정된 컬렉션의 요소 클래스로 인해 해당 요소가 이 deque에 추가되지 않는 경우
     * @throws IllegalArgumentException 지정된 컬렉션 요소의 일부 속성으로 인해 해당 요소가 이 deque에 추가되지 않는 경우
     */
    boolean addAll(Collection<? extends E> c);

    // *** 스택 메소드 ***

    /**
     * 용량 제한을 위반하지 않고 즉시 값 추가가 가능하면 
     * deque의 헤드에 요소를 푸시하고, 
     * 현재 사용 가능한 공간이 없으면 {@code IllegalStateException}을 발생시킴.
     *
     * {@link #addFirst} 메소드와 같은 의미를 가짐
     *
     * @param e 삽입할 원소
     * @throws IllegalStateException 용량 제한으로 인해 현재 원소를 추가할 수 없는 경우
     * @throws ClassCastException 지정된 원소의 클래스로 인해 해당 원소가 이 deque에 추가되지 않는 경우
     * @throws NullPointerException 지정된 원소가 null이고 이 deque가 null 원소를 허용하지 않는 경우
     * @throws IllegalArgumentException 지정된 원소의 일부 속성으로 인해 해당 원소가 이 deque에 추가되지 않는 경우
     */
    void push(E e);

    /**
     *  큐의 맨 앞 원소를 삭제하고 반환한다.
     *
     * {@link #removeFirst()} 메소드와 같은 의미를 가짐
     *
     * @return 큐의 맨 앞 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E pop();


    // *** 컬렉션 메소드 ***

    /**
     * 입력된 오브젝트의 값과 동일한 값을 가진 노드를 탐색하여 처음 일치하는 노드를 제거
     * 
     * @param o 제거할 노드의 값
     * @return 삭제를 성공할 경우 {@code true}, 실패할 경우 {@code false} 반환
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException 매개변수가 null인 경우 경우
     */
    boolean remove(Object o);

    /**
     * 이 큐에 지정한 원소가 포함되어 있으면 {@code true}를 반환함.
     * 
     * 더 정확히는 데크에 {@code Objects.equals(o, e)}가 {@code true}가 반환되는
     * 원소 {@code e}가 하나 이상 포함된 경우에만 {@code true}를 반환함.
     *
     * @param o 이 큐에에 존재 여부를 테스트할 원소
     * @return {@code true} 이 큐에 지정된 원소가 포함되어 있는 경우
     * @throws ClassCastException 지정된 원소의 클래스가 이 deque와 호환되지 않는 경우
     * @throws NullPointerException 지정된 원소가 null이고 이 deque가 null 원소를 허용하지 않는 경우
     */
    boolean contains(Object o);

    /**
     * 이 데크(큐)의 원소의 개수를 반환함
     *
     * @return 이 큐의 원소의 개수
     */
    int size();

    /**
     * 이 큐의 원소에 대한 Iterator를 적절한 순서로 반환합니다.
     * 원소는 처음(head)부터 마지막(tail)까지 순서대로 반환됩니다.
     *
     * @return 이 큐의 원소에 대한 Iterator
     */
    Iterator<E> iterator();

    /**
     * 이 큐의 원소에 대한 반복자를 역순으로 반환합니다.
     * 원소는 마지막(head)부터 처음(tail)까지 순서대로 반환됩니다.
     *
     * @return 이 큐의 원소에 대한 역순 Iterator
     */
    Iterator<E> descendingIterator();
}
