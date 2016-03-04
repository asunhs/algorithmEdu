#include <cstdio>

int main() {
	int Answer1, Answer2;
	int P[10] = { 1, 5, 10, 0, 7, 6 };
	int N = 6;
	int L_square = 0;
	int L_square_max = 0;

	for (int i = 0; i < N; i++) {
		if (P[i] == 0) continue;
		for (int j = 1; j < N; j++) {
			if (P[j] == 0) continue;
			L_square = (j - i)*(j - i) + (P[j] - P[i])*(P[j] - P[i]);
			printf("%d %d [%d]\n", i, j, L_square);
			if ( L_square > L_square_max) {
				Answer1 = i;
				Answer2 = j;
				L_square_max = L_square;
			}
		}
	}

	printf("=== %d %d [%d]\n", Answer1, Answer2, L_square_max);

	double d = 0.123456789;
	double sd = 0.0;
	for (int i = 0; i < 1000000; i++) {
		sd += d;
	}
	printf("%f %f %d\n", sd, d * 1000000, sd == d*1000000);
	return 0;
}