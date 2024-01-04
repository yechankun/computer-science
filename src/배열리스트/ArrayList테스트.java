package 배열리스트;

import java.util.Iterator;

public class ArrayList테스트 {
    public static void main(String[] args){
        ArrayList<Integer> test = new ArrayList<>();

        test.add(1);
        test.add(2);
        test.add(3);

        Iterator<Integer> iterator = test.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(test.subList(1, 2));

        System.out.println(test);

    }
}

