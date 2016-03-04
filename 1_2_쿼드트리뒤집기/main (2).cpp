#include <cstdio>
//#include <cstring>
#include <string>

using namespace std;

int length;
char in_string[1002];
int idx;

string reverse()
{
	char ch = in_string[idx];
	idx++;

	if (ch == 'b') {
		return "b";
	}

	if (ch == 'w') {
		return "w";
	}

	string q1 = reverse();
	string q2 = reverse();
	string q3 = reverse();
	string q4 = reverse();

	return "x" + q3 + q4 + q1 + q2;
}

int main() {
	int test_case, C;

	//freopen("C:\\sample_input.txt", "r", stdin);

	scanf("%d\n", &C);
	for (test_case = 1; test_case <= C; ++test_case) {
		// 입력
		fgets(in_string, 1000, stdin);
		length = strlen(in_string) - 1;
		in_string[length] = 0;

		// 처리
		idx = 0;
		
		// 출력
		printf("%s\n", reverse().c_str());
	}
	return 0;
}