import java.io.FileInputStream;
import java.util.Scanner;

class Solution {

	static int N, K; // N : 사람의 명수, K : 부를 수 있는 수
	static int W[][] = new int[501][500]; // [N][J], [i][j] i번 사람이 j를 부를 수 있을 때, j를 부를 때까지 기다리는 시간
	static int Answer[] = new int[501]; // Answer[i] : i번 사람의 상태 - 0 : 아직 안부름  1 : 부름  -1 : 걸림
	
	public static void main(String args[]) throws Exception {

		int test_case, T;
		int tmpArr[] = new int[500];
		int itmp;
		int timemin;
		
		boolean flag;

		/*
		  아래의 호출은 표준 입력 대신 sample input 파일로부터 읽어오겠다는 의미입니다.
		  여러분이 작성한 코드를 테스트할 때 이 곳에 입력을 저장한 후 이 코드를 추가하여
		  이후 입력을 표준 입력 대신 이 파일로부터 받아올 수 있습니다. 
		  따라서 테스트 수행 시에는 이 코드를 사용하셔도 좋습니다.
		  단, 코드를 제출하실 때에는 반드시 이 코드를 지우거나 주석처리 하셔야 합니다.
		*/
		System.setIn(new FileInputStream("sample_input.txt"));
	
		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		*/
		Scanner sc = new Scanner(System.in);
	
		// 테스트 케이스의 수 T 
		T = sc.nextInt();

		for (test_case = 1; test_case <= T; ++test_case) {
			// 입력
			N = sc.nextInt();
			K = sc.nextInt();
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= K; j++) {
					W[i][j] = sc.nextInt();
				}
				Answer[i] = 0;
			}
			flag = false;

			// 처리
			for (int j = 1; j <= K; j++) {
				itmp = 0;
				timemin = 100; // 최대 60
				for (int i = 1; i <= N; i++) {
					if (Answer[i] == 1) continue; // 이미 부른 사람은 통과
					if (W[i][j] < timemin) {
						itmp = 1;
						tmpArr[0] = i; // i번 사람을 기록하고 나머지 삭제
						timemin = W[i][j];
					}
					else if (W[i][j] == timemin) {
						tmpArr[itmp++] = i; // 중복된 사람 기록
					}
				}
				// 이 시점에서 itmp 는 반드시 1 이상이어야 함.
				if (itmp == 1) {
					Answer[tmpArr[0]] = 1;
				}
				else { // itmp 가 2 이상인 경우
					for (int t = 0; t < itmp; t++) {
						Answer[tmpArr[t]] = -1;
					}
					flag = true;
					break;
				}
			}
			if(flag == false) {
				for (int i = 1; i <= N; i++) {
					if (Answer[i] == 0) Answer[i] = -1;
				}
			}
			// 출력
			for (int i = 1; i <= N; i++) {
				if (Answer[i] == -1) {
					System.out.print(i+" ");
				}
			}
			System.out.println();
		}
	}
}		
		
