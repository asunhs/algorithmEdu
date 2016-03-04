import java.io.FileInputStream;
import java.util.Scanner;

class Solution {

	static int N;
	static String A, B;
	static long Answer;
	
	static int[] visited = new int[100];
	
	public static void main(String args[]) throws Exception {
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
		int T = sc.nextInt();
		
		
		// 테스트 케이스 각각을 처리합니다.
		 
		for(int test_case = 1; test_case <= T; test_case++) {
			/******************************************************/
			// 이 곳에 알고리즘을 구현합니다.
			/******************************************************/
			N = sc.nextInt();
			A = sc.next();
			B = sc.next();
			
			Answer = Math.abs(getIndex(A) - getIndex(B)) - 1;
			System.out.println("#" + test_case + " " + Answer);
		}
	}
	
	public static long getIndex(String perm) {
		long result = 1;
		
		for(int i = 0; i < N; i++) {
			visited[i] = 0;
		}
		
		int tmp, cnt;
		for(int i = 0; i < N; i++) {
			tmp = (int) (perm.charAt(i) - 'a');
			cnt = countLessThan(tmp);
			
			result += cnt * fact(N - i -1);
			
			visited[tmp] = 1;
		}
		
		return result;
	}
	
	public static int countLessThan(int value) {
		int result = 0;
		
		for(int i = 0; i < value; i++) {
			if(visited[i] == 0) {
				result ++;
			}
		}
		
		return result;
	}
	
	public static long fact(long value) {
		if(value == 0) {
			return 1;
		}
		
		return value * fact(value - 1);
	}
}


