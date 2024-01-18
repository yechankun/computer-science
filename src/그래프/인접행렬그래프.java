package 그래프;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 인접행렬그래프 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int e = Integer.parseInt(st.nextToken()); // 간선의 개수
	
		
		int[][] matrix = new int[n + 1][n + 1];
		
		for(int i =0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
			int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            matrix[fr][to] = 1;
            matrix[to][fr] = 1;
		}
		
        //1번노드부터 출력
		for(int i = 1 ; i <= n ; i++) {
			for(int j = 1 ; j <= n ; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
