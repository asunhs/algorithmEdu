import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	
	static int tower[]       = new int[500000]; // 탑의 높이 정보
	static int next_search[] = new int[500000]; // 다음 검색할 탑
	static int to_receive[]  = new int[500000]; // 수신탑 정보 (답은 이 값에 1을 더한 것)
	
	public static void main(String args[]) throws Exception {

		int N; // 탑의 개수
		System.setIn(new FileInputStream("sample_input.txt"));
	
		Scanner sc = new Scanner(System.in);
	
		N = sc.nextInt();
		for (int n = 0; n < N; n++) {
			tower[n] = sc.nextInt();
		}

		for (int n = 0; n < N; n++) { // 현재 탑 : n
			next_search[n] = n - 1;
			to_receive[n] = -1;
			for (int i = n - 1; i >= 0; i = next_search[i]) {
				if (tower[i] >= tower[n]) {
					to_receive[n] = i;
					next_search[n] = i;
					break;
				}
			}
			if (to_receive[n] == -1) next_search[n] = -1;
		}

		for (int n = 0; n < N; n++) {
			System.out.print((to_receive[n]+1) + " ");
		}
		System.out.println();
	}
}
