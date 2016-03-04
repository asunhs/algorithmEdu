#include <cstdio>

int N;       // 외계 우주선 수
int M;       // 미사일 종류의 수
int B;       // 외계 우주선의 방어막 세기
int p[1000]; // 종류별 미사일의 위력
int q[1000]; // 종류별 미사일의 보유량

int Answer;

int main() {

	int test_case, T;
	int i;

	/*
	   아래의 freopen 함수는 sample_input.txt 를 read only 형식으로 연 후,
	   앞으로 표준 입력(키보드) 대신 sample_input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
	   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 sample_input.txt에 입력을 저장한 후,
	   freopen 함수를 이용하면 이후 scanf 를 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
	   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 함수를 사용하셔도 좋습니다.
	   freopen 함수를 사용하기 위해서는 #include <cstdio>, 혹은 #include <stdio.h> 가 필요합니다.
	   단, 채점을 위해 코드를 제출하실 때에는 반드시 freopen 함수를 지우거나 주석 처리 하셔야 합니다.
	*/
	//freopen("C:\\sample_input.txt", "r", stdin);

	/*
	아래의 setbuf 함수는 본 프로그램의 버퍼를 unbuffered 스트림으로 설정합니다.
	본 프로그램이 제한 시간 초과로 등의 이유로 종료되는 시점에서
	버퍼에 저장된 데이터가 전부 출력되지 않을 수 있으므로, 아래의 코드를 삭제하지 마시기 바랍니다.
	*/
	setbuf(stdout, NULL);

	scanf("%d", &T);

	for (test_case = 1; test_case <= T; ++test_case) {

		/* 입력 */
		scanf("%d %d %d", &N, &M, &B);
		for (i = 0; i < M; ++i) {
			scanf("%d %d", &p[i], &q[i]);
		}

		/******************************************************/
		// 이 곳에 알고리즘을 구현합니다.
		// 정답을 Answer 에 저장하는 것을 가정합니다.
		/******************************************************/
		Answer = 0;
		
		
		

		printf("#%d %d\n", test_case, Answer);
	}
	return 0;
}
