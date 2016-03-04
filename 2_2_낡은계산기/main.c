#include <stdio.h>

int N;
int A[100];
int Answer;

int main() {
	int test_case, C;
	int tmp, tmpi;


	scanf("%d", &C);
	for (test_case = 1; test_case <= C; ++test_case) {
		scanf("%d", &N);
		for (int i = 0; i < N; i++) {
			scanf("%d", &A[i]);
		}
		Answer = 0;

		while (N >= 2) {
			tmp = A[0]; tmpi = 0;
			for (int i = 1; i < N; i++) {
				if (A[i] < tmp) {
					tmp = A[i];
					tmpi = i;
				}
			}
			tmp = A[0];
			A[0] = A[tmpi];
			A[tmpi] = tmp;

			tmp = A[1]; tmpi = 1;
			for (int i = 2; i < N; i++) {
				if (A[i] < tmp) {
					tmp = A[i];
					tmpi = i;
				}
			}
			tmp = A[1];
			A[1] = A[tmpi];
			A[tmpi] = tmp;

			tmp = A[0] + A[1];
			Answer += tmp;
			A[0] = tmp;
			A[1] = A[N - 1];
			N--;
		}

		printf("#%d %d\n", test_case, Answer);

	}
	return 0;
}