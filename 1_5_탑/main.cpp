#include <cstdio>

int tower[500000];      // 탑의 높이 정보
int next_search[500000];// 다음 검색할 탑
int to_receive[500000]; // 수신탑 정보 (답은 이 값에 1을 더한 것)

int main() {
	int N; // 탑의 개수
	freopen("sample_input.txt", "r", stdin);

	scanf("%d", &N);
	for (int n = 0; n < N; n++) {
		scanf("%d", &tower[n]);
	}

	for (int n = 0; n < N; n++) { // 현재 탑 : n
		next_search[n] = n - 1;
		to_receive[n] = -1;
		for (int i = n - 1; i >= 0; i = next_search[i]) {
			if (tower[i] >= tower[n]) {
				to_receive[n] = i;
				next_search[n] = i;
				break;
			}
		}
		if (to_receive[n] == -1) next_search[n] = -1;
	}

	for (int n = 0; n < N; n++) {
		printf("%d ", to_receive[n]+1);
	}
	putchar('\n');

	return 0;
}
