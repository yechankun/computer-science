package 트리;

import java.util.*;

public class 완전이진트리 {
    public static void main(String[] args){
        // 노드
        class Node{
            int data;
            Node left;
            Node right;

            Node(int data){
                this.data = data;
                this.left = null;
                this.right = null;
            }
        }

        // 완전 이진트리
        class CompleteBinaryTree {
            Node root;
            int size;

            CompleteBinaryTree(){
                this.root = null;
                this.size = 0;
            }

            void addData(int data){
                if (root == null){
                    root = new Node(data);
                }else{
                    Queue<Node> queue = new LinkedList<>();
                    queue.add(root);
                    Node dataNode = new Node(data);
                    while (queue != null){
                        Node currentNode = queue.poll();
                        if(currentNode.left == null){
                            currentNode.left = dataNode;
                            break;
                        }else{
                            queue.add(currentNode.left);
                        }

                        if(currentNode.right == null){
                            currentNode.right = dataNode;
                            break;
                        }else{
                            queue.add(currentNode.right);
                        }
                    }
                }
                size++;
            }

            void popData(){
                if (size == 1){
                    System.out.println(root.data);
                    root = null;
                    size = 0;
                }
                else if (size > 0){
                    Stack<Node> stack = new Stack<>();
                    stack.add(root);
                    while(!stack.empty()){
                        Node currentNode = stack.peek();
                        if(currentNode.left != null){
                            stack.push(currentNode.left);
                        }else{
                            System.out.println(currentNode.data);
                            while(!stack.empty()){
                                Node last = stack.pop();
                                if (last.left == currentNode){
                                    last.left = null;
                                }else if(last.right == currentNode){
                                    last.right = null;
                                }
                            }
                        }

                        if(currentNode.right != null){
                            stack.push(currentNode.right);
                        }
                    }
                    size--;
                }else{
                    System.out.println("트리에 데이터가 없습니다.");
                }
            }
            
        }

        System.out.println("트리입니다");
        CompleteBinaryTree tree = new CompleteBinaryTree();
        tree.addData(1);
        tree.addData(2);
        tree.addData(3);

        tree.popData();
        tree.popData();
        tree.addData(4);
        tree.popData();
        tree.popData();
    }
    
}


