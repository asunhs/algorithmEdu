//#include <stdio.h>    // for C
#include <cstdio>     // for C++

#define CH(ch)  ((ch)-'a')

char A[20];
char B[20];
long long Answer;

long long factorial(int n) {
	long long retval = 1;
	if (n == 0) return 1;
	for (; n > 1; --n) {
		retval *= n;
	}
	return retval;
}

int main() {
	int test_case, T;
	int N;
	int rest[18];
	long long a, b;

	int i, j, base;

	freopen("sample_input.txt", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &T);

	for (test_case = 1; test_case <= T; test_case++) {
		scanf("%d %s %s", &N, A, B);

		// A의 사전순서 검색
		a = 0;
		for (i = 0; i < N; i++) rest[i] = 1;

		for (i = 0; i < N; i++) {
			base = 0;
			for (j = 0; j < N; j++) {
				if (!rest[j]) continue;
				if (CH(A[i]) == j) {
					rest[j] = 0;
					a += (base * factorial(N - i - 1));
					break;
				}
				base++;
			}
		}
		// B의 사전순서 검색
		b = 0;
		for (i = 0; i < N; i++) rest[i] = 1;

		for (i = 0; i < N; i++) {
			base = 0;
			for (j = 0; j < N; j++) {
				if (!rest[j]) continue;
				if (CH(B[i]) == j) {
					rest[j] = 0;
					b += (base * factorial(N - i - 1));
					break;
				}
				base++;
			}
		}

		Answer = a < b ? b - a - 1 : a - b - 1;

		//		printf("%lld %lld\n", a, b);

		printf("#%d %lld\n", test_case, Answer);
	}
	//printf("%lld\n",factorial(18));
	return 0;
}