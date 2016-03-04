import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 정올 1824 : 스도쿠
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1097&sca=3030
 */
public class Main {
	
	static int[][] mat;
	static List<Integer> nums;
	static boolean isProcessFinished = false;
	static List<Integer> validNums;		// 위치에 놓일 수 있는 가능한 숫자 후보들
	
	public static void print(int[][] matrix) {
		for(int i=1; i<=9; i++) {
			for(int j=1; j<=9; j++) {
				System.out.print(matrix[i][j]);
				if(j!=9) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	public static void process(int row, int col, int[][] matrix) {
		
		if(!isProcessFinished) {
			if(row>9) {
				isProcessFinished = true;
				print(matrix);
			} else {
				if(matrix[row][col] == 0) {
					validNums = check(row, col);
					for(Integer num : validNums) {
						matrix[row][col] = num;			// insert num to position
						
						// move next position
						if(col==9) {
							process(row+1, 1, matrix);
							
						} else {
							process(row, col+1, matrix);
						}
						
						matrix[row][col] = 0;		// rollback to 0
					}
				} else {
					// move next position
					if(col==9) {
						process(row+1, 1, matrix);
						
					} else {
						process(row, col+1, matrix);
					}
				}
			}
		}
	}
	
	public static List<Integer> check(int row, int col) {
		boolean isFinished = false;
		
		nums = new ArrayList<Integer>();
		for(int i=1; i<=9; i++) {
			nums.add(i);
		}
		
		// check horizontal row
		if(!isFinished) {
			for(int i=1; i<=9; i++) {
				if(mat[row][i] == 0) {
					continue;
				} else {
					nums.remove((Integer)mat[row][i]);
				}
				if(nums.size()==0) {
					isFinished = true;
					break;
				}
			}
		}
		
		// check vertical col
		if(!isFinished) {
			for(int i=1; i<=9; i++) {
				if(mat[i][col] == 0) {
					continue;
				} else {
					nums.remove((Integer)mat[i][col]);
				}
				if(nums.size()==0) {
					isFinished = true;
					break;
				}
			}
		}
		
		// check square zone
		if(!isFinished) {
			for(int i=1; i<=3; i++) {
				for(int j=1; j<=3; j++) {
					if(mat[(row-1)/3*3+i][(col-1)/3*3+j] == 0) {
						continue;
					} else {
						nums.remove((Integer)mat[(row-1)/3*3+i][(col-1)/3*3+j]);
					}
					if(nums.size()==0) {
						isFinished = true;
						break;
					}
				}
				if(nums.size()==0) {
					isFinished = true;
					break;
				}
			}
		}
		
		return nums;
	}
	
	public static void main(String args[]) throws Exception {
		
		// FIXME : test case
		System.setIn(new FileInputStream(Main.class.getResource("").getPath()+"sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		mat = new int[10][10];
		
		for(int i=1; i<=9; i++) {
			for(int j=1; j<=9; j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		
		process(1, 1, mat);
		
	}
	
}