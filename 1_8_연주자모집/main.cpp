#include <cstdio>
#include <list>

using namespace std;

int Answer;
int M, N;
int S[20];
int P[24][20];
int C[24];

list<int> List;

void backtrack(int pos, int val, int cost) {
	int totalCost = cost;

	if (val == 1) {
		totalCost += S[pos];
	}

	if (pos == N - 1) {
		int covered = 0;

		for (int i = 0; i < M; i++) {
			C[i] = 0;
		}

		for (list<int>::iterator i = List.begin(); i != List.end(); i++) {
			for (int j = 0; j < M; j++) {
				if (P[j][*i] == 1 && C[j] == 0) {
					covered++;
					C[j] = 1;
				}
			}
		}

		if (covered == M && totalCost < Answer) {
			Answer = totalCost;
		}

		return;
	}

	if (totalCost > Answer) {
		return;
	}

	for (int i = 0; i < 2; i++) {
		if (i == 1) {
			List.push_back(pos + 1);
		}

		backtrack(pos + 1, i, totalCost);

		if (i == 1) {
			List.pop_back();
		}
	}
}


int main() {

	int test_case, T;

	freopen("sample_input.txt", "r", stdin);

	setbuf(stdout, NULL);

	scanf("%d", &T);

	for (test_case = 1; test_case <= T; ++test_case) {
		// 지원자의 수 N, 악기의 수 M
		scanf("%d %d", &N, &M);

		// 지원자의 월급
		for (int i = 0; i < N; i++) {
			scanf("%d", &S[i]);
		}

		// 지원자의 악기 연주 정보
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				scanf("%d", &P[i][j]);
			}
		}

		/*
		*
		* 이곳에 여러분의 코드를 작성합니다.
		* 테스트 케이스 개수 T만큼 각각의 결과가 Answer로 최종 출력 됩니다.
		*
		*/
		Answer = 99999999;
		List.clear();

		for (int i = 0; i < 2; i++) {
			if (i == 1) {
				List.push_back(0);
			}

			backtrack(0, i, 0);

			if (i == 1) {
				List.pop_back();
			}
		}


		/* 결과 값 출력 */
		printf("#%d %d\n", test_case, Answer);
	}

	return 0;
}

