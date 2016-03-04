#include <cstdio>

#define EQN(X, Y, i) (a[i]*(X)+b[i]*(Y)+c[i])

int x[3], y[3];
int Answer;

int main() {

	int test_case, T;

	int a[3], b[3], c[3];
	int sign[3]; // 음수:< , 0:== , 양수:>

	//freopen("input.txt", "r", stdin);

	scanf("%d", &T);
	for (test_case = 1; test_case <= T; ++test_case) {
		// 입력
		scanf("%d %d %d %d %d %d", &x[0], &y[0], &x[1], &y[1], &x[2], &y[2]);

		// x 좌표 작은 순서대로 재정렬
		int tmp;
		if (x[1] < x[0]) {
			tmp = x[0]; x[0] = x[1]; x[1] = tmp;
			tmp = y[0]; y[0] = y[1]; y[1] = tmp;
		}
		if (x[2] < x[1]) {
			if (x[2] < x[0]) {
				tmp = x[2]; x[2] = x[1]; x[1] = x[0]; x[0] = tmp;
				tmp = y[2]; y[2] = y[1]; y[1] = y[0]; y[0] = tmp;
			}
			else {
				tmp = x[2]; x[2] = x[1]; x[1] = tmp;
				tmp = y[2]; y[2] = y[1]; y[1] = tmp;
			}
		}
		

		// 처리 : ax + by + c = 0  
		Answer = 0ll;
		for (int i=0, j=0, k=0; i < 3; i++) {
			j = (i + 1) % 3;
			k = (i + 2) % 3;
			a[i] = y[i] - y[j];
			b[i] = x[j] - x[i];
			c[i] = x[i] * y[j] - x[j] * y[i];
			int tmpsign = a[i] * x[k] + b[i] * y[k] + c[i];
			sign[i] = tmpsign;
		}
		a[2] = -a[2]; b[2] = -b[2]; c[2] = -c[2]; // b를 항상 0 또는 양수로 만든다.

		if (sign[0] == 0 || sign[1] == 0 || sign[2] == 0) goto PRINTANS; // 세 점이 일직선상에 있을 경우
		int min, max, xindex, yindex;
		min = max = yindex = y[0];
		// b>=0 . 즉 선분 위에 점이 위치하면 방정식은 양수.
//		printf("%d %d / %d %d / %d %d\n", x[0], y[0], x[1], y[1], x[2], y[2]);
		for (xindex = x[0]+1; xindex < x[1]; xindex++) {
			if (sign[0] < 0) { // 0번식이 2번식 위에 있으면
				while (EQN(xindex, min, 2) <= 0) min++;
				while (EQN(xindex, min - 1, 2) > 0) min--;
				while (EQN(xindex, max, 0) >= 0) max--;
				while (EQN(xindex, max + 1, 0) < 0) max++;
			}
			else {
				while (EQN(xindex, min, 0) <= 0) min++;
				while (EQN(xindex, min - 1, 0) > 0) min--;
				while (EQN(xindex, max, 2) >= 0) max--;
				while (EQN(xindex, max + 1, 2) < 0) max++;
			}
			if (max >= min) Answer += (max - min + 1);
		}
		for (int aa=0; xindex < x[2]; xindex++, aa++) {
			if (xindex == x[0]) continue;
			if (sign[1] < 0) { // 1번식이 2번식 위에 있으면
				while (EQN(xindex, min, 2) <= 0) min++;
				while (EQN(xindex, min - 1, 2) > 0) min--;
				while (EQN(xindex, max, 1) >= 0) max--;
				while (EQN(xindex, max + 1, 1) < 0) max++;
			}
			else {
				while (EQN(xindex, min, 1) <= 0) min++;
				while (EQN(xindex, min - 1, 1) > 0) min--;
				while (EQN(xindex, max, 2) >= 0) max--;
				while (EQN(xindex, max + 1, 2) < 0) max++;
			}
			if (max >= min) Answer += (max - min + 1);
		}

		// 출력
	PRINTANS:
		printf("#%d %d\n", test_case, Answer);

	}

	return 0;
}
