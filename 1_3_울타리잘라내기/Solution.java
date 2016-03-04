import java.io.*;
import java.util.*;

class Solution {

	static int mBars[];

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cNum = sc.nextInt();
		//int cNum = Integer.parseInt(br.readLine().trim());
		
		for (int i = 0; i < cNum; i++) {
			int N = sc.nextInt();
			//int N = Integer.parseInt(br.readLine());
			//StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			mBars = new int[N];

			for (int j = 0; j < N; j++) {
				mBars[j] = sc.nextInt();
				//mBars[j] = Integer.parseInt(tokenizer.nextToken());
			}
			System.out.println(getLocalMax(0, N - 1));
		}
		sc.close();
		//br.close();
	}

	public static int getLocalMax(int left, int right) {
		if (left == right)
			return mBars[left];

		int mid = (left + right) / 2;

		int ret = Math.max(getLocalMax(left, mid), getLocalMax(mid + 1, right));

		int lo = mid, hi = mid + 1;
		int height = Math.min(mBars[lo], mBars[hi]);

		ret = Math.max(ret, height * 2);

		while (left < lo || hi < right) {

			if (hi < right && (lo == left || mBars[lo - 1] < mBars[hi + 1])) {
				++hi;
				height = Math.min(height, mBars[hi]);
			} else {
				--lo;
				height = Math.min(height, mBars[lo]);
			}

			ret = Math.max(ret, height * (hi - lo + 1));
		}

		return ret;
	}
}