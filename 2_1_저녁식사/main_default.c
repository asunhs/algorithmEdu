#include <stdio.h>
#include <stdlib.h>

int T;
int ans[200000];
int D[200000][3];  // [0] 번호(1번부터) , [1] 아버지집과의 거리 , [2] 딸집과의 거리


int main()
{
	int test_case;
	int i, j, k, ansi;
	/*
	freopen function below opens sample_input2.txt file in read only mode, and afterward,
	the program will read from sample_input2.txt file instead of standard(keyboard) input.
	To test your program, you may save input data in sample_input2.txt file,
	and use freopen function to read from the file when using scanf function.
	You may remove the comment symbols(//) in the below statement and use it.
	But before submission, you must remove the freopen function or rewrite comment symbols(//).
	*/
	freopen("input.txt", "r", stdin);

	/*
	If you remove the statement below, your program's output may not be recorded
	when your program is aborted due to the time limit.
	For safety, please use setbuf(stdout, NULL); statement.
	*/
	setbuf(stdout, NULL);

	scanf("%d", &T);
	for (test_case = 1; test_case <= T; test_case++) {

		////////////////////////////////////////////////////////////////////////////////////////////
		/*
		Please, implement your algorithm from this section.
		Your solution is to be stored in a variable called 'ans'.
		*/

		/////////////////////////////////////////////////////////////////////////////////////////////
		// Print the answer to standard output(screen). 
		printf("#%d", test_case);
		for (i = 0; i<ansi; i++) printf(" %d", ans[i]);
		printf(" \n");
	}

	return 0; // Your program should return 0 on normal termination.
}
