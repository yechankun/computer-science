package 배열;

public class 배열 {
    public static void main(String[] args) throws Exception {
        System.out.println("배열입니다.");

        // 배열 선언
        int[] arr1 = new int[10];

        // 배열 초기화1
        int[] arr2 = new int[] { 1, 2, 3, 4, 5 };
        int[] arr3 = { 1, 2, 3, 4, 5 };


        // 배열 출력
        System.out.println("배열1");
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
        System.out.println("배열2");
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
        System.out.println("배열3");
        for (int i = 0; i < arr3.length; i++) {
            System.out.println(arr3[i]);
        }

        // 자바 배열의 특징
        // 1. 배열은 한번 크기가 정해지면 변경할 수 없다.
        // 2. 배열은 한가지 타입만 저장할 수 있다.
        // 3. 배열은 인덱스가 0부터 시작한다.
        // 4. 배열은 for문과 함께 사용하면 편리하다.        
    }
}
