package 스택;

import java.util.EmptyStackException;

import 배열리스트.ArrayList;

public class Stack<E> extends ArrayList<E>{
    // 기존 java.util.Stack은 Vector클래스를 상속하여 구현되어 있다.
    // Vector 클래스는 AbstractList 클래스를 구현하고 있고 List 인터페이스를 상속한다.
    // 즉 Vector 클래스는 이 둘을 분석해 따로 구현했던 배열리스트.ArrayList와 구현부가 거의 동일한다.

    public Stack(){
    }

    /**
     * item을 매개변수를 받아 Stack에 추가함
     * @param item 추가할 요소
     * @return 추가한 요소
     */
    public E push(E item){
        add(item);

        return item;
    }

    /**
     * 마지막에 추가된 요소를 삭제하고 반환함
     * @return 마지막에 추가된 요소
     * @throws EmptyStackException 스택이 빈 경우에 발생
     */
    public E pop(){
        E obj = peek();
        remove(size() - 1);

        return obj;
    }

    /**
     * 마지막에 추가된 요소를 반환함.
     * @return {@code ArrayList}의 마지막에 추가된 요소
     * @throws EmptyStackException 스택이 빈 경우에 발생
     */
    public E peek(){
        int length = size();

        if (length == 0)
            throw new EmptyStackException();
        return get(length - 1);
    }

    /**
     * 스택의 크기가 0인지 체크하고 반환함
     * @return 스택의 크기가 0이면 {@code true}, 아니면 {@code false} 반환
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 특정 요소의 위치를 검색해서 반환
     * @param o
     * @return 요소가 없다면 {@code -1}을 반환하고 있으면 해당 위치의 인덱스를 반환함
     */
    public int search(Object o){
        int index = lastIndexOf(o);

        if (index < 0)
            return -1;
        return size() - index;
    }
    
}
