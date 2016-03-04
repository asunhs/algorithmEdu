import java.io.FileInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;


class Solution {

	static int N;
	static int Answer;
	static int[] A = new int[100];
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
	public static void main(String args[]) throws Exception {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("eval_input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);

		/* 테스트 케이스의 수 T */
		int T = sc.nextInt();
		
		/*
		   테스트 케이스 각각을 처리합니다.
		 */
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			
			pq.clear();
			for(int i = 0; i < N; i++) {
				//A[i] = sc.nextInt();
				pq.add(sc.nextInt());
			}
			
			Answer = 0;
			while(pq.isEmpty() == false) {
				int a = pq.poll();
				int b = pq.poll();
				
				Answer += a + b;
				if(pq.isEmpty() == false) {
					pq.add(a + b);
				}
			}
			
			System.out.println("#" + test_case + " " + Answer);
		}
	}
}
