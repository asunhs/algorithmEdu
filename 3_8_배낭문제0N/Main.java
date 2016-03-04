import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 정올 1077 : 배낭채우기
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=357&sca=3050
 */
public class Main {
	
	public static void main(String args[]) throws Exception {
		
		// FIXME : test case
		System.setIn(new FileInputStream(Main.class.getResource("").getPath()+"sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();		// 보석의 가짓 수
		int w = sc.nextInt();		// 배낭의 용량
		
		int[][] mat = new int[n+1][w+1];
        
		int[] weight = new int[n+1];
		int[] value = new int[n+1];
		
        for(int i=1; i<=n; i++) {
        	weight[i] = sc.nextInt();
        	value[i] = sc.nextInt();
        }
         
        // dynamic algorithm
        for(int i=1; i<=n; i++) {
        	for(int j=1; j<=w; j++) {
                if(i==1) {                  // 가장 윗줄일 경우
                    if(weight[i]<=j) {	// 물건의 무게가 최대무게보다 가볍거나 같으면
                    	// 최대한 넣었을 때 값으로 갱신
                        mat[i][j] = value[i] * (j/weight[i]);
                    } else {			// 물건이 최대무게보다 무거우면
                        mat[i][j] = 0;
                    }
                } else {
                    if(weight[i]<=j) {		// 물건의 무게가 최대무게보다 가볍거나 같으면
                    	mat[i][j] = mat[i-1][j];					// i번째 물건을 선택하지 않는 경우
                    	
                    	// i번째 물건을 1개부터 Math.floor(weight of pocket / weight of jewel) 만큼 넣는 경우의 최대값으로 갱신
                    	for(int k=1; k<=j/weight[i]; k++) {			// i번째 물건을 k개 선택할 경우
                    		int pick = value[i]*k + mat[i-1][j-weight[i]*k];
                    		if(mat[i][j] < pick) mat[i][j] = pick;		// 최대값으로 갱신
                    	}
                    } else  {       		// 물건이 최대무게보다 무거우면
                        mat[i][j] = mat[i-1][j];
                    }
                }
            }
        }
         
		// (n, w) 값이 최대값
		System.out.println(mat[n][w]);
    }
}