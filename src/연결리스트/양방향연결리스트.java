package 연결리스트;

public class 양방향연결리스트 {
    public static void main(String[] args) throws Exception {
        // 양방향 연결리스트 직접 구현
        // 지역 이너 클래스로 구현
    
        // O(1)인 왼쪽 추가, 오른쪽 추가 구현
        // 1. 노드 선언
        class Node {
            int data;
            Node left;
            Node right;

            Node(int data) {
                this.data = data;
            }        
        }

        // 2. 연결리스트 선언
        class LinkedList {
            Node head;
            Node tail;

            LinkedList() {
                this.head = null;
                this.tail = null;
            }

            // 2. 왼쪽 추가
            public void addLeft(int data) {
                Node newNode = new Node(data);

                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    head.left = newNode;
                    newNode.right = head;
                    head = newNode;
                }
            }

            // 3. 오른쪽 추가
            public void addRight(int data) {
                Node newNode = new Node(data);

                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.right = newNode;
                    newNode.left = tail;
                    tail = newNode;
                }
            }

            // 4. 출력
            public void print() {
                Node cur = head;

                while (cur != null) {
                    System.out.print(cur.data + " ");
                    cur = cur.right;
                }
                System.out.println();
            }

            // 5. 왼쪽 삭제
            public void removeLeft() {
                if (head == null) {
                    System.out.println("삭제할 노드가 없습니다.");
                } else {
                    head = head.right;
                    head.left = null;
                }
            }

            // 6. 오른쪽 삭제
            public void removeRight() {
                if (head == null) {
                    System.out.println("삭제할 노드가 없습니다.");
                } else {
                    tail = tail.left;
                    tail.right = null;
                }
            }   
        }
        System.out.println("양방향 연결리스트입니다.");

        // 7. 연결리스트 선언
        LinkedList list1 = new LinkedList();

        // 8. 값 추가
        list1.addLeft(100);
        list1.addLeft(200);
        list1.addRight(300);
        list1.addRight(400);

        // 9. 값 출력
        list1.print();

        // 10. 값 삭제
        list1.removeLeft();
        list1.removeRight();
        list1.print();
    }



}
