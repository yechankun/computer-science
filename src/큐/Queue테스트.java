package 큐;

import 연결리스트.LinkedList;

public class Queue테스트 {
    
    public static void main(String[] args){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.peek());

        System.out.println(queue);

        System.out.println(queue.poll());

        System.out.println(queue);

        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        System.out.println(deque);
        System.out.println(deque.pollLast());
        System.out.println(deque);
        System.out.println(deque.pollFirst());
        System.out.println(deque);

    }
}

