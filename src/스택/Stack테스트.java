package 스택;

public class Stack테스트 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);

        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);

        while(!stack.isEmpty()){
            stack.pop();
        }
        System.out.println(stack);
    }
}
