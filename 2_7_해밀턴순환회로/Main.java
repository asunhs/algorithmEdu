import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 정올 1681 : 해밀턴 순환회로
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=954&sca=3030
 */
public class Main {
     
    private static int n;
    private static int minDist = Integer.MAX_VALUE;
    private static List<Integer> visit;
    private static int[][] mat;
     
    private static void process(int node, int dist) {
        visit.add(node);        // mark visit
         
        if(visit.size()==n && mat[node][0]>0) {		// 모두 방문하였고 현재위치에서 0으로 돌아갈수 있는경우
            dist += mat[node][0];
            if(dist<minDist) minDist = dist;			// 최종 거리 갱신
        } else {
            for(int i=1; i<n; i++) {
                if(!visit.contains(i) && mat[node][i]>0 && dist+mat[node][i]<minDist) {		// pruning
                	// 방문한 적이 없음 && 이동할 수 있음 && 현재의이동거리누적+이동할거리가 현재 최소값보다 작은지
                    process(i, dist+mat[node][i]);
                }
            }
        }
        visit.remove((Integer) node);
    }   
 
    public static void main(String[] args) throws Exception {
         
		// FIXME : test case
		System.setIn(new FileInputStream(Main.class.getResource("").getPath()+"sample_input.txt"));
         
        Scanner sc = new Scanner(System.in);
         
        n = sc.nextInt();
        mat = new int[n][n];
        
        // set data
        for(int i=0; i<n; i++) {			// mat[i][j] = i에서 j까지 거리
            for(int j=0; j<n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
         
        // solve
        visit = new ArrayList<Integer>();
        process(0, 0);
        System.out.println(minDist);
         
    }
}