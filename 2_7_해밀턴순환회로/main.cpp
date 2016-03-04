#include <stdio.h>

int M[12][12]; // 인접 배열
int N; // 배달 장소 수

int Answer;
int visited[12], nvisited;

void process(int start, int dist) {
	visited[start] = 1;
	nvisited++;
	if (nvisited == N) {
		if (dist + M[start][0] < Answer) {
			Answer = dist + M[start][0];
		}
	}
	else {
		for (int i = 1; i < N; i++) {
			if (visited[i] == 1) continue;
			if (dist + M[start][i] >= Answer) continue;
			process(i, dist + M[start][i]);
		}
	}
	visited[start] = 0;
	nvisited--;
}

int main() {
	freopen("sample_input.txt", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &M[i][j]);
			if (M[i][j] == 0) M[i][j] = 10000;
		}
		visited[i] = 0;
	}
	nvisited = 0;

	//for (int i = 0; i < N; i++) {
	//	for (int j = 0; j < N; j++) {
	//		printf("%d\t", M[i][j]);
	//	}
	//	printf("\n");
	//}

	//// 알고리즘 구현
	Answer = 10000;

	process(0, 0);

	printf("%d\n", Answer);
	return 0;
}