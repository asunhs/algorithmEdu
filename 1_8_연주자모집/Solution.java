import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
	static int Answer;
	static int M, N;
	static int[] S = new int[20];
	static int[][] P = new int[24][20];
	static int[] C = new int[24];
	static LinkedList<Integer> list = new LinkedList<Integer>();
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이
		 * 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에
		 * 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래
		 * 주석을 지우고 이 메소드를 사용하셔도 좋습니다. 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석
		 * 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("sample_input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);

		/*
		 * 테스트 케이스의 수를 입력 받습니다.
		 */
		int T = sc.nextInt();
		
		/*
		 * T 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */
		for(int test_case = 1; test_case <= T; ++test_case) {
			// 지원자의 수
			N = sc.nextInt();
			
			// 악기의 수
			M = sc.nextInt();
			
			// 지원자의 월급
			for(int i = 0; i < N; i++) {
				S[i] = sc.nextInt();
			}
			
			// 지원자의 악기 연주 정보
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					P[i][j] = sc.nextInt();
				}
			}
			
			/*
			* 
			* 이곳에 여러분의 코드를 작성합니다.
			* 테스트 케이스 개수 T만큼 각각의 결과가 Answer로 최종 출력 됩니다.
			* 
			*/
			Answer = Integer.MAX_VALUE;
			list.clear();
			
			for(int i = 0; i < 2; i++) {
				if(i == 1) {
					list.add(0);
				}
				
				backtrack(0, i, 0);
				
				if(i == 1) {
					list.removeLast();
				}
			}
			
			
			/* 결과 값 출력 */
			System.out.println("#" + test_case + " " + Answer);
		}
	}
	
	public static void backtrack(int pos, int val, int cost) {
		int totalCost = cost;
		
		if(val == 1) {
			totalCost += S[pos];
		}
		
		if(pos == N - 1) {
			int covered = 0;
			
			for(int i = 0; i < M; i++) {
				C[i] = 0;
			}
			
			for(int i : list) {
				for(int j = 0; j < M; j++) {
					if(P[j][i] == 1 && C[j] == 0) {
						covered ++;
						C[j] = 1;
					}
				}
			}
			
			if(covered == M && totalCost < Answer) {
				Answer = totalCost;
			}
			
			return;
		}
		
		if(totalCost > Answer) {
			return;
		}
		
		for(int i = 0; i < 2; i++) {
			if(i == 1) {
				list.add(pos + 1);
			}
			
			backtrack(pos + 1, i, totalCost);
			
			if(i == 1) {
				list.removeLast();
			}
		}
	}
}
