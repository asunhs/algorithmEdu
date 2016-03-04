import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;



public class Solution {

	static int[] x = new int[3];
	static int[] y = new int[3];
	static int Answer;
	
	static int[] dir = new int[3];
	static int[] a = new int[3];
	static int[] b = new int[3];
	static int[] c = new int[3];
	
	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이
		 * 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에
		 * 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래
		 * 주석을 지우고 이 메소드를 사용하셔도 좋습니다. 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석
		 * 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("sample_input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int minX, maxX;
		
		for(int test_case = 1; test_case <= T; test_case ++) {
			minX = Integer.MAX_VALUE;
			maxX = Integer.MIN_VALUE;
			
			for(int i = 0; i < 3; i++) {
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
				
				if(x[i] < minX) {
					minX = x[i];
				}
				
				if(x[i] > maxX) {
					maxX = x[i];
				}
			}
			
			// 삼각형의 세 직선의 방정식을 계산
			int cnt = 0;
			for(int i = 0; i < 2; i++) {
				for(int j = 1; j < 3; j++) {
					if(i == j) {
						continue;
					}
					
					calc(i, j, cnt++);
				}
			}
			
			double[] py = new double[3];
			Integer maxY, minY;
			Answer = 0;
			
			for(int px = minX + 1; px < maxX; px++) {
				// x = px 와 삼각형의 세 직선이 만나는 지점의 y 좌표를 계산
				for(int i = 0; i < 3; i++) {
					if(b[i] == 0) {
						// y축에 평행한 직선
						py[i] = Double.MAX_VALUE;
					} else {
						// py값 계산(오차 발생 가능)
						py[i] = ((double) - a[i] / (double) b[i]) * px - ((double) c[i] / (double) b[i]);
					}
				}
				
				// y축 기준으로 정렬
				Arrays.sort(py);
				
				for(int i = 0; i < 2; i++) {
					// y축과 평행한 경우 건너 뜀
					if(py[i + 1] == Double.MAX_VALUE) {
						continue;
					}
					
					// 중앙점이 삼각형 내부에 있는 지 판단
					if(check(px, (py[i] + py[i + 1]) / 2d) == true) {
						// x = px 와 만나는 직선 상의 삼각형 내부에 있는
						// 가장 큰 y값과 가장 작은 y값을 탐색
						maxY = getMaxY(px, (int)Math.max(py[i], py[i + 1]));
						minY = getMinY(px, (int)Math.min(py[i], py[i + 1]));
						
						if(maxY != null && minY != null) {
							Answer += maxY - minY + 1;
						}
						break;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + Answer);
		}
	}
	
	// px 지점에서 삼각형 내부에 있는 가장 큰 y값을 찾음
	public static Integer getMaxY(int px, int py) {
		int max = py - 1;
		boolean found = false;
		
		for(int i = -2; i <= 2; i++) {
			if(checki(px, py + i) == true) {
				max = py + i;
				found = true;
			}
		}
		
		if(found) {
			return Integer.valueOf(max);
		} else {
			return null;
		}
	}
	
	// px 지점에서 삼각형 내부에 있는 가장 작은 y값을 찾음
	public static Integer getMinY(int px, int py) {
		int min = py + 1;
		boolean found = false;
		
		for(int i = 2; i >= -2; i--) {
			if(checki(px, py + i) == true) {
				min = py + i;
				found = true;
			}
		}
		
		if(found) {
			return Integer.valueOf(min);
		} else {
			return null;
		}
	}
	
	// (px, py) 가 삼각형 내부에 있는지 판단
	public static boolean checki(int px, int py) {
		boolean result = true;
		
		for(int i = 0; i < 3; i++) {
			if((a[i] * px + b[i] * py + c[i]) * dir[i] <= 0) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	// (px, py) 가 삼각형 내부에 있는지 판단
	public static boolean check(double px, double py) {
		boolean result = true;
		
		for(int i = 0; i < 3; i++) {
			if((a[i] * px + b[i] * py + c[i]) * dir[i] <= 0) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	// 직선의 방점식 계산
	public static void calc(int p1, int p2, int idx) {
		a[idx] = y[p1] - y[p2];
		b[idx] = x[p2] - x[p1];
		c[idx] = y[p2] * x[p1] - y[p1] * x[p2];
		
		int pIdx = 3 - (p1 + p2);
		
		// 삼각형 내부의 방향 계산
		dir[idx] = a[idx] * x[pIdx] + b[idx] * y[pIdx] + c[idx] > 0 ? 1 : -1;
	}
}
