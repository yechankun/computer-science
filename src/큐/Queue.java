package 큐;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * 큐는 컬렉션 인터페이스를 상속한 인터페이스다.
 * 컬렉션의 함수들을 구현하는 클래스라면 있다면 큐 인터페이스와 호환이 가능하다.
 */
public interface Queue<E> extends Collection<E> {
    
    /**
     * 특정 원소를 큐에 삽입한다. 
     * 
     * @param e 추가할 원소
     * @return {@code true} {@link Collection#add}에 정의된 것처럼 true 반환
     * @throws IllegalStateException 용량제한으로 이번에 원소를 추가하지 못하는 경우
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException null 원소를 삽입할 경우
     * @throws IllegalArgumentException 이 요소의 일부 속성이 잘못되었을 경우
     */
    boolean add(E e);

    /**
     * 특정 원소를 큐에 삽입한다. 
     * 코드의 의미상 {@link #add}보다 바람직함
     * @param e 추가할 원소
     * @return {@code true} {@link Collection#add}에 정의된 것처럼 true 반환
     * @throws IllegalStateException 용량제한으로 이번에 원소를 추가하지 못하는 경우
     * @throws ClassCastException 클래스가 호환되지 않는 경우
     * @throws NullPointerException null 원소를 삽입할 경우
     * @throws IllegalArgumentException 이 요소의 일부 속성이 잘못되었을 경우
     */
    boolean offer(E e);

    /**
     * 큐의 head 원소를 삭제하고 반환한다.
     * {@link #poll}과 다르게 큐가 비어있으면 에러를 반환한다.
     *
     * @return 큐의 head 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E remove();

    /**
     * 큐의 head 원소를 삭제하고 반환한다.
     *
     * @return 큐의 head 원소
     */
    E poll();

    /**
     * 큐의 head 값을 반환한다.
     * {@link #peek}과 다르게 큐가 비어있으면 에러를 반환한다.
     * 
     * @return 큐의 head 원소
     * @throws NoSuchElementException 큐가 비어있을 경우
     */
    E element();

    /**
     * 큐의 head 값을 반환한다.
     * 
     * @return 큐의 head 원소
     */
    E peek();
}