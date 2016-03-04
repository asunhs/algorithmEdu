import java.io.FileInputStream;
import java.util.Scanner;

class Solution {

	static int N;                   // 외계 우주선 수
	static int M;                   // 미사일 종류의 수
	static int B;                   // 외계 우주선의 방어막 세기
	static int p[] = new int[1000]; // 종류별 미사일의 위력
	static int q[] = new int[1000]; // 종류별 미사일의 보유량

	static int Answer;
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이
		 * 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에
		 * 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래
		 * 주석을 지우고 이 메소드를 사용하셔도 좋습니다. 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석
		 * 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("C:\\sample_input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; ++test_case) {

			/* 입력 */
			N = sc.nextInt();
			M = sc.nextInt();
			B = sc.nextInt();
			for (int i = 0; i < M; ++i) {
				p[i] = sc.nextInt();
				q[i] = sc.nextInt();
			}

			/******************************************************/
			// 이 곳에 알고리즘을 구현합니다.
			// 정답을 Answer 에 저장하는 것을 가정합니다.
			/******************************************************/
			Answer = 0;
		
			
			
			/* 결과 값 출력 */
			System.out.println("#" + test_case + " " + Answer);
		}
	}
}
