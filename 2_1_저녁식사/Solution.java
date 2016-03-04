import java.util.Scanner;
import java.io.FileInputStream; // Method needed to use System.setIn
import java.util.*;

/*
  The class name has to be Solution, so we recomment using Solution.java.
  You can test your program with the command java Solution.
*/
class Solution {	
	///////////////////////////////////////////
	
	static int [] ans = new int[200005];
	static long [] arr = new long[200005];

	public static void main(String args[]) throws Exception {
		/*
		 The method call below opens input.txt in read only mode and 
	           sets your standard input System.in to work with the opened file. 
	           When you test your code with the sample data, you can use the function
	           below to read in from the sample data file instead of the standard input.
	           So. you can uncomment the following line for your local test. But you
	           have to comment the following line when you submit for your scores.
		*/
	    // System.setIn(new FileInputStream("input.txt"));

		/*
		   Makes a Scanner from standard input System.in and reads data.
		*/
		Scanner sc = new Scanner(System.in);
		
		int i, j;
		int T;
		
		T = sc.nextInt();
		for(int test_case = 1; test_case <= T; ++test_case){
			int cnt = 0;
			///////////////////////////////////////////////////////////////////////////////////////
			/*
			Your algorithm is implemented here.
		          It is assumed that you store your answer in the variable Answer.
			*/

			int n = sc.nextInt();
			
			Map<Long, Integer> map = new HashMap<>();

			for(i=0; i<200005; i++) arr[i] = 9223372036854775807L;

			for(i=0; i<n; i++){
				long x = sc.nextInt(), y = sc.nextInt();
				map.put(x*1000000001 + y, i+1);
				arr[i] = x*1000000001 + y;
			}

			Arrays.sort(arr);

			long min = 2000000000;
			for(i=0; i<n; i++){
				long x = arr[i] / 1000000001;
				long y = arr[i] % 1000000001;
				if(min > y){
					min = y; 
					ans[cnt] = map.get(arr[i]);
					cnt++;
				}
			}

			///////////////////////////////////////////////////////////////////////////////////////

			// Prints out the answer to standard output.
			System.out.print("#" + test_case + " ");
			for(i=0; i<cnt; i++) System.out.print(ans[i] + " ");
			System.out.println("");
		}
	}
}
