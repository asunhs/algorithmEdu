import java.io.FileInputStream;
import java.util.Scanner;


class Solution {

	static int N, M;
	static int[] A = new int[100];
	
	static int NC;
	
	// 메모이제이션 배열
	static int[][] f = new int[100][15625];
	
	// 솔루션 저장 배열
	static int[][] s = new int[100][15625];
	
	static int radix, num;
	
	static int Answer = 0;
	
	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이
		 * 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에
		 * 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래
		 * 주석을 지우고 이 메소드를 사용하셔도 좋습니다. 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석
		 * 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("eval_input02.txt"));
		
		Scanner sc = new Scanner(System.in);

		/*
		테스트 케이스의 수를 입력 받습니다.
		*/
		int T = sc.nextInt();

		/*
		T 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
			
			// 총 칸의 개수
			NC = (N - 1) * 4; 
			
			for(int i = 0; i < NC; i++) {
				A[i] = sc.nextInt();
			}
			
			// 진법(M이 2인 경우 3진법)
			radix = M + 1;
			
			// 남은 숫자 카드의 수를 하나의 10진수로 표현 
			num = (int)Math.pow(radix, 6) - 1;
			
			// 초기화
			for(int i = 0; i < NC + 6; i++) {
				for(int j = 0; j <= num; j++) {
					f[i][j] = -1;
					s[i][j] = 0;
				}
			}
			
			Answer = Integer.MAX_VALUE;
			f[0][0] = A[0];
			f[1][1] = A[1];
			s[1][1] = 1;
			
			int p = 0;

			// 시작지점을 지나쳐 갈 수 있는 범위의 값들 중 최저값을 저장
			for(int i = NC; i < NC + 6; i++) {
				if(Answer > dp(i, num)) {
					Answer = dp(i, num);
					p = i;
				}
			}
			
			// 솔루션 출력
			//printSolution(p, num - 1);
			//System.out.println();
			System.out.println("#" + test_case + " " + Answer);
		}
	}
	
	// 동적 계획법 구현 메소드
	public static int dp(int p, int m) {
		int tmp, sum;
		
		if(p == 0) {
			return 0;
		}
		
		// 이전에 계산된 값이 있는 경우
		if(f[p][m] != -1) {
			return f[p][m];
		}
		
		f[p][m] = 1000000;

		// 현재 위치에서 1 ~ 6 까지의 카드를 사용해 갈 수 있는 지점을 조사
		for(int k = 0; k < 6; k++) {
			tmp = (int)Math.pow(radix, k);
			
			// 남은 (k + 1) 카드의 수는 0보다 커야 하고
			// 보드의 위치값은 0 이상이어야 한다.
			if((m / tmp) % radix > 0 && p - (k + 1) >= 0) {
				sum = dp(p - (k + 1), m - tmp) + A[p % NC];
				
				// 하위 문제의 결과값으로 계산한 값보다 작으면 저장한다.
				if(f[p][m] > sum) {
					f[p][m] = sum;
					
					// 솔루션 저장
					s[p][m] = k + 1;
				}
			}
		}
		
		return f[p][m];
	}

	// 솔루션 출력 메소드
	public static void printSolution(int p, int m) {
		if(p - s[p][m] >= 0 && s[p][m] > 0) {
			printSolution(p - s[p][m], m - (int)Math.pow(radix, s[p][m] - 1));
		}
		
		if(s[p][m] != 0) {
			System.out.print(s[p][m] + " ");
		}
	}
}
