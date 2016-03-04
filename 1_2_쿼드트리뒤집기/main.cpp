#include <cstdio>
#include <cstring>

int length;
char in_string[1002];
char out_string[1002];
char buffer[1000];

// 리턴값은 실제 end되는 포인터
char *turnover(char *start, char *end) {
	char *index[4];
	char *i = start;
	int n = 0;

	// 4분할의 각 시작점을 캐치   0(start)------1------2------3------end
	while (n < 4) {
		index[n++] = i;
		if (*i == 'b' || *i == 'w') {
			i++;
		}
		else {
			i = turnover(i + 1, end);
		}
	}
	memcpy(buffer, index[2], index[3] - index[2]);
	memcpy(buffer + (index[3] - index[2]), index[3], i - index[3]);
	memcpy(buffer + (i - index[2]), index[0], index[1] - index[0]);
	memcpy(buffer + (i - index[2]) + (index[1] - index[0]), index[1], index[2] - index[1]);

	memcpy(index[0], buffer, i - index[0]);

	return i;

}

int main() {
	int test_case, C;

	freopen("sample_input.txt", "r", stdin);

	scanf("%d\n", &C);
	for (test_case = 1; test_case <= C; ++test_case) {
		// 입력
		fgets(in_string, 1000, stdin);
		length = strlen(in_string) - 1;
		in_string[length] = 0;

		// 처리
		strcpy(out_string, in_string);
		if (out_string[0] == 'x') {
			turnover(out_string + 1, out_string + length);
		}
		// 출력
		printf("%s\n", out_string);
	}
	return 0;
}