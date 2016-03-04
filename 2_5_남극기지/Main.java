import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

	static int N;
	static double[][] point = new double[100][2];
	
	static double[][] dist = new double[100][100];
	static double[] distArr = new double[10000];
	
	static int[] visited = new int[100];
	static double selected;
	static int vcnt, dcnt;
	static double Answer;
	
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int T = Integer.parseInt(br.readLine().trim());
		double min, minMax;
		int maxSelIdx = 0;
		String[] tmp;
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			N = Integer.parseInt(br.readLine().trim());
			
			for(int i = 0; i < N; i++) {
				tmp = br.readLine().trim().split(" ");
				point[i][0] = Double.parseDouble(tmp[0]);
				point[i][1] = Double.parseDouble(tmp[1]);
			}
			
			minMax = -1;
			for(int i = 0; i < N; i++) {
				min = Double.MAX_VALUE;
				
				for(int j = 0; j < N; j++) {
					if(i == j) {
						dist[i][j] = Double.MAX_VALUE;
					} else {
						dist[i][j] = Math.sqrt(Math.pow(point[i][0] - point[j][0], 2)
								+ Math.pow(point[i][1] - point[j][1], 2)); 
					}
					
					distArr[i * N + j] = dist[i][j];
					if(min > dist[i][j]) {
						min = dist[i][j];
					}
				}
				
				if(min > minMax) {
					minMax = min;
				}
			}
			
			Arrays.sort(distArr, 0, N * N);
			dcnt = 1;
			
			for(int i = 1; i < N * N; i++) {
				if(distArr[i - 1] != distArr[i]) {
					distArr[dcnt] = distArr[i];
					
					if(distArr[i] == minMax) {
						maxSelIdx = dcnt;
					}
					
					dcnt++;
				}
			}
			
			Answer = Double.MAX_VALUE;
			bsearch(maxSelIdx, dcnt - 1);			
			System.out.printf("%.2f\n", Answer);
		}
	}
	
	public static void bsearch(int start, int end) {
		int index = (start + end) / 2;
		
		initVisited();
		selected = distArr[index];
		
		vcnt = 0;
		dfs(0);
		
		if(vcnt == N) {
			if(selected < Answer) {
				Answer = selected;
			}
			
			if(index - 1 > 0) {
				bsearch(start, index - 1);
			}
		} else {
			if(index + 1 <= end) {
				bsearch(index + 1, end);
			}
		}
	}
	
	public static void initVisited() {
		for(int i = 0; i < N; i++) {
			visited[i] = 0;
		}
	}
	
	public static void dfs(int node) {
		visited[node] = 1;
		vcnt ++;
		
		for(int i = 0; i < N; i++) {
			if(dist[node][i] - selected <= 0d && visited[i] == 0) {
				dfs(i);
			}
		}
	}
}
