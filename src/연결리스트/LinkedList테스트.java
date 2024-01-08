package 연결리스트;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class LinkedList테스트 {
    public static void main(String[] args) throws Exception {
        System.out.println("연결리스트입니다.");

        // 자바 연결리스트
        
        // 1. 선언
        List<Integer> list1 = new LinkedList<>();

        // 2. 값 추가
        list1.add(100);
        list1.add(200);

        // 3. 값 출력
        System.out.println(list1.get(0));
        System.out.println(list1.get(1));

        // 4. 값 삭제
        list1.remove(0);
        System.out.println(list1.get(0));

        // 5. 값 수정
        list1.set(0, 1000);
        System.out.println(list1.get(0));

        // 6. 값의 유무
        System.out.println(list1.contains(1000));
        System.out.println(list1.contains(100));

        // 7. 값의 개수
        System.out.println(list1.size());

        // 8. 값의 초기화
        list1.clear();
        System.out.println(list1.size());

        // 9. 연결리스트의 값 존재여부
        System.out.println(list1.isEmpty());

        // 10. 값의 정렬
        list1.add(100);
        list1.add(200);
        Collections.sort(list1);
        System.out.println(list1);

        // 11. 값의 역정렬
        Collections.sort(list1, Collections.reverseOrder());
        System.out.println(list1);

        // 12. 값의 반복문 출력1
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }

        // 13. 값의 반복문 출력2
        for (int i : list1) {
            System.out.println(i);
        }

        // 14. 값의 반복문 출력3
        Iterator<Integer> iter = list1.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        // 15. 값의 반복문 출력4
        list1.forEach(System.out::println);

        // 16. 값의 반복문 출력5
        list1.forEach(i -> System.out.println(i));

        // 17. 값의 반복문 출력6
        list1.forEach(i -> {
            System.out.println(i);
        });        

        // 18. 배열의 연결리스트 간단하게 변환
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        System.out.println(list2);

        // 19. 배열의 연결리스트 좀 더 간단하게 변환
        List<Integer> list3 = List.of(1, 2, 3, 4);
        System.out.println(list3);

        // 20. 연결리스트를 배열로 변환
        Integer[] arr1 = list3.toArray(new Integer[list3.size()]);
        System.out.println(Arrays.toString(arr1));

        // 21. 연결리스트를 배열로 변환2
        Integer[] arr2 = list3.toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr2));

        // 연결리스트의 특징
        // 1. 연결리스트는 크기가 자유롭다.
        // 2. 연결리스트는 여러가지 타입을 저장할 수 있다.
        // 3. 연결리스트는 인덱스가 0부터 시작한다.
        // 4. 연결리스트는 for문과 함께 사용하면 불편하다.
    }
}
