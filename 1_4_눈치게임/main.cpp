#include <cstdio>

int N, K; // N : 사람의 명수, K : 부를 수 있는 수
int W[501][500]; // [N][K], [i][j] i번 사람이 j를 부를 수 있을 때, j를 부를 때까지 기다리는 시간
int Answer[501]; // Answer[i] : i번 사람의 상태 - 0 : 아직 안부름  1 : 부름  -1 : 걸림

int main() {
	int test_case, T;

	int tmpArr[500], itmp;
	int timemin;
	freopen("sample_input.txt", "r", stdin);
	scanf("%d", &T);

	for (test_case = 1; test_case <= T; ++test_case) {
		// 입력
		scanf("%d %d", &N, &K);
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				scanf("%d", &W[i][j]);
			}
			Answer[i] = 0;
		}

		// 처리
		for (int j = 1; j <= K; j++) {
			itmp = 0;
			timemin = 100; // 최대 60
			for (int i = 1; i <= N; i++) {
				if (Answer[i] == 1) continue; // 이미 부른 사람은 통과
				if (W[i][j] < timemin) {
					itmp = 1;
					tmpArr[0] = i; // i번 사람을 기록하고 나머지 삭제
					timemin = W[i][j];
				}
				else if (W[i][j] == timemin) {
					tmpArr[itmp++] = i; // 중복된 사람 기록
				}
			}
			// 이 시점에서 itmp 는 반드시 1 이상이어야 함.
			if (itmp == 1) {
				Answer[tmpArr[0]] = 1;
			}
			else { // itmp 가 2 이상인 경우
				for (int t = 0; t < itmp; t++) {
					Answer[tmpArr[t]] = -1;
				}
				goto ANS;
			}
		}
		for (int i = 1; i <= N; i++) {
			if (Answer[i] == 0) Answer[i] = -1;
		}

		// 출력
	ANS:
		//printf("#%d", test_case);
		for (int i = 1; i <= N; i++) {
			if (Answer[i] == -1) printf("%d ", i);
		}
		putchar('\n');
	}

	return 0;
}