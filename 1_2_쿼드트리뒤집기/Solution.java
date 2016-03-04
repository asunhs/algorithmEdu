import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {
	
	static char[] quad;
	static int idx;
	
	static String reverse() {
		StringBuilder sb = new StringBuilder();
		char c = quad[idx++];
		if(c == 'x') {
			String s1 = reverse();
			String s2 = reverse();
			String s3 = reverse();
			String s4 = reverse();
			sb.append("x").append(s3).append(s4).append(s1).append(s2);
		}
		else {
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int num = Integer.valueOf(br.readLine().trim());

		while(num-- > 0){
			String line = br.readLine();
			if(line.length() == 1) {
				System.out.println(line);
			} else {
				quad = line.toCharArray();
				idx = 0;
				System.out.println(reverse());
			}
		}
		
		br.close();
	}
}
