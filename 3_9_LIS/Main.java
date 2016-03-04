package example.algospot.lis;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Algospot LIS : Longest Increasing Sequence
 * 
 * 
 * #LIS #Dynamic
 * 
 * @author jaechun.yoo
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// test file input
		 System.setIn(new FileInputStream(Main.class.getResource("").getPath()+"sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();		// 테스트 케이스 수
		
		int[] nums = new int[501];			// 수열 데이터 1 ~ 500
		int[] maxLen = new int[501];		// 해당 위치에서 수열끝까지의 부분수열에서의 LIS 길이
		int result;		// LIS 최대값
		
		int numLen;		// 수열의 길이
		for(int test_case=1; test_case<=T; test_case++) {
			numLen = sc.nextInt();
			
			// set data
			for(int i=1; i<=numLen; i++) {
				nums[i] = sc.nextInt();
				maxLen[i] = 0;
			}
			
			// init
			result = Integer.MIN_VALUE;
			
			// solve
			// k의 위치에서 LIS는 k+1 ~ END 까지 수열중에서 nums[k]보다 큰 위치에서의
			// LIS에 k를 연결한 것과 같다
			for(int i=numLen; i>=1; i--) {		// 뒤에서부터 cache 완성
				for(int j=numLen; j>i; j--) {
					if(nums[i]<nums[j]) {
						maxLen[i] = Math.max(maxLen[i], maxLen[j]);
					}
				}
				maxLen[i]++;
			}
			
			// print
			for(int i=1; i<=numLen; i++) {
				result = Math.max(result, maxLen[i]);
			}
			System.out.println(result);
		}
	}

}