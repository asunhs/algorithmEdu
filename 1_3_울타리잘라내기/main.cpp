#include <cstdio>
#include <algorithm>

using namespace std;

int N; // 판자 개수
int height[20000]; // 각 판자의 높이
int Answer;

int DandC(int start, int end) { // 폐폐. 즉 [start,end]

	if (start == end) return height[start];

	int mid = (start + end) / 2;
	int left, right;
	int center, c_height_limit, c_l_limit, c_r_limit;

	// left : start ~ mid, right : mid+1 ~ end, center : including mid, mid+1

	left = DandC(start, mid);
	right = DandC(mid + 1, end);

	c_height_limit = min(height[mid], height[mid + 1]);
	c_l_limit = mid;
	c_r_limit = mid + 1;
	center = c_height_limit * 2;
	bool modeR;
	for (int w = 3; w <= end - start + 1; ++w) {
		if (c_l_limit == start) modeR = true;
		else if (c_r_limit == end) modeR = false;
		else if (height[c_l_limit - 1] > height[c_r_limit + 1]) modeR = false;
		else modeR = true;

		if (modeR) {
			c_r_limit++;
			c_height_limit = min(c_height_limit, height[c_r_limit]);
		}
		else {
			c_l_limit--;
			c_height_limit = min(c_height_limit, height[c_l_limit]);
		}
		center = max(center, c_height_limit * w);
	}


	if (left > center) center = left;
	if (right > center) center = right;
	return center;
}

int main() {
	int test_case, C;

	freopen("sample_input.txt", "r", stdin);

	scanf("%d", &C);

	for (test_case = 1; test_case <= C; ++test_case) {

		// 입력
		scanf("%d", &N);
		for (int i = 0; i < N; ++i) {
			scanf("%d", &height[i]);
		}
		Answer = 0;

		// 처리
		Answer = DandC(0, N - 1);

		// 출력
		printf("%d\n", Answer);

	}

	return 0;
}