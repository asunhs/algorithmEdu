#include <stdio.h>
#include <stdlib.h>

int T;
int ans[200000];
int D[200000][3];  // [0] 번호(1번부터) , [1] 아버지집과의 거리 , [2] 딸집과의 거리

int cmp_1(const void *i1, const void *i2) {
	const int *a = (const int *)i1;
	const int *b = (const int *)i2;
	if (a[1] < b[1]) return -1;
	else if (a[1] == b[1]) return 0;
	return 1;
}

int cmp_2(const void *i1, const void *i2) {
	const int *a = (const int *)i1, *b = (const int *)i2;
	if (a[2] < b[2]) return -1;
	else if (a[2] == b[2]) return 0;
	return 1;
}

int main()
{
	int test_case, cnt;
	int i, j, k, ansi;
	int x, y, index;
	/*
	freopen function below opens sample_input2.txt file in read only mode, and afterward,
	the program will read from sample_input2.txt file instead of standard(keyboard) input.
	To test your program, you may save input data in sample_input2.txt file,
	and use freopen function to read from the file when using scanf function.
	You may remove the comment symbols(//) in the below statement and use it.
	But before submission, you must remove the freopen function or rewrite comment symbols(//).
	*/
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	/*
	If you remove the statement below, your program's output may not be recorded
	when your program is aborted due to the time limit.
	For safety, please use setbuf(stdout, NULL); statement.
	*/
	setbuf(stdout, NULL);

	scanf("%d", &T);
	for (test_case = 1; test_case <= T; test_case++) {

		////////////////////////////////////////////////////////////////////////////////////////////
		/*
		Please, implement your algorithm from this section.
		Your solution is to be stored in a variable called 'ans'.
		*/
		scanf("%d", &cnt);
		for (i = 0; i<cnt; i++) {
			scanf("%d %d", &D[i][1], &D[i][2]);
			D[i][0] = i + 1;
		}

		//		for(i=0; i<cnt; i++) {
		//			printf("[%d][%d][%d]\n", D[i][0], D[i][1], D[i][2]);
		//		}
		//		printf("\n");
		qsort(D, cnt, sizeof(int) * 3, cmp_2);
		qsort(D, cnt, sizeof(int) * 3, cmp_1);

		//		for(i=0; i<cnt; i++) {
		//			printf("[%d][%d][%d]\n", D[i][0], D[i][1], D[i][2]);
		//		}
		x = 0;
		y = 1000000001;
		index = 0;
		ansi = 0;
		for (i = 0; i<cnt; i++) {
			if (D[i][1] > x) { // x 값이 변하면
				if (index != 0) ans[ansi++] = index;
				x = D[i][1];
				index = 0;
			}
			if (D[i][2] < y) {
				index = D[i][0];
				y = D[i][2];
			}
		}
		if (index != 0) ans[ansi++] = index;

		//		qsort(ans, ansi, sizeof(int), cmpa);

		/////////////////////////////////////////////////////////////////////////////////////////////
		// Print the answer to standard output(screen). 
		printf("#%d", test_case);
		for (i = 0; i<ansi; i++) printf(" %d", ans[i]);
		printf(" \n");
	}

	return 0; // Your program should return 0 on normal termination.
}
