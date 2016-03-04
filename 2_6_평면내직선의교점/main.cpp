#include <iostream>
#include <cstdio>

using namespace std;

/**
* 정올 1129 : 평면내 직선의 교점
*
* #Math
*
* @author jaechun.yoo
*
*/

int N;				// 선분의 갯수 2 ~ 20

// line[k][0]:x1, line[k][1]:y1, line[k][2]:x2, line[k][3]:y2
// line[k][4]:A, line[k][5]:B, line[k][6]:C ----> Ax + By + C = 0
int line[21][7];	// 선분 두점의 좌표 및 직선의 방정식 정보

// 두점의 좌표로부터 직선의 방정식 정보 구하기 Ax + By + C = 0
void findLineFunction() {
	// A = y1 - y2
	// B = x2 - x1
	// C = x1y2 - x2y1
	for (int i = 1; i <= N; i++) {
		line[i][4] = line[i][1] - line[i][3];
		line[i][5] = line[i][2] - line[i][0];
		line[i][6] = line[i][0] * line[i][3] - line[i][2] * line[i][1];
	}
}

// 직선의 방정식 쌍으로 부터 교점의 좌표를 구하기
int findIntersectionPoint() {
	// 교점 x = (b1c2 - b2c1) / (a1b2 - a2b1)
	// 교점 y = (a1c2 - a2c1) / (a2b1 - a1b2)
	// 단, a1b2 - a2b1 = 0인 경우는 두 직선의 기울기가 같은 경우로(겹치거나 평행)
	// 겹치는 경우가 존재하지 않음은 문제조건으로 주어져있고 평행하는 경우는 교점이 존재할수 없음
	// 교점의 좌표가 선분의 범위에 들어오는지 확인이 필요한데 앞서구한 교점 (x, y) 좌표는
	// 실수형으로 나오게되면 부동소수점에 의한 precision 문제가 발생할 수 있으므로
	// 분모의 제곱을 부등식에 곱하여 비교함

	// 교점 x좌표가 범위에 들어오는지 체크
	int A1, B1, C1, A2, B2, C2;
	int X1, Y1, X2, Y2, X3, Y3, X4, Y4;
	bool isInteriorX1, isInteriorY1, isInteriorX2, isInteriorY2;
	int intersectionPointCnt = 0;			// 교점의 갯수

	for (int i = 1; i <= N - 1; i++) {
		for (int j = i + 1; j <= N; j++) {
			A1 = line[i][4]; B1 = line[i][5]; C1 = line[i][6];
			A2 = line[j][4]; B2 = line[j][5]; C2 = line[j][6];
			X1 = line[i][0]; Y1 = line[i][1]; X2 = line[i][2]; Y2 = line[i][3];
			X3 = line[j][0]; Y3 = line[j][1]; X4 = line[j][2]; Y4 = line[j][3];
			isInteriorX1 = false; isInteriorY1 = false; isInteriorX2 = false; isInteriorY2 = false;

			// 교점 x좌표가 범위에 들어오는지 체크
			if (X1<X2) {
				if ((B1*C2 - B2*C1)*(A1*B2 - A2*B1)>X1*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)
					&& (B1*C2 - B2*C1)*(A1*B2 - A2*B1)<X2*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)) {
					isInteriorX1 = true;
				}
			}
			else if (X1>X2) {
				if ((B1*C2 - B2*C1)*(A1*B2 - A2*B1)>X2*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)
					&& (B1*C2 - B2*C1)*(A1*B2 - A2*B1)<X1*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)) {
					isInteriorX1 = true;
				}
			}
			else {		// X1==X2
				if ((B1*C2 - B2*C1)*(A1*B2 - A2*B1) == X1*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)) {
					isInteriorX1 = true;
				}
			}

			if (X3<X4) {
				if ((B1*C2 - B2*C1)*(A1*B2 - A2*B1)>X3*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)
					&& (B1*C2 - B2*C1)*(A1*B2 - A2*B1)<X4*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)) {
					isInteriorX2 = true;
				}
			}
			else if (X3>X4) {
				if ((B1*C2 - B2*C1)*(A1*B2 - A2*B1)>X4*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)
					&& (B1*C2 - B2*C1)*(A1*B2 - A2*B1)<X3*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)) {
					isInteriorX2 = true;
				}
			}
			else {		// X3==X4
				if ((B1*C2 - B2*C1)*(A1*B2 - A2*B1) == X3*(A1*B2 - A2*B1)*(A1*B2 - A2*B1)) {
					isInteriorX2 = true;
				}
			}

			// 교점 y좌표가 범위에 들어오는지 체크
			if (Y1<Y2) {
				if ((A1*C2 - A2*C1)*(A2*B1 - A1*B2)>Y1*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)
					&& (A1*C2 - A2*C1)*(A2*B1 - A1*B2)<Y2*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)) {
					isInteriorY1 = true;
				}
			}
			else if (Y1>Y2) {
				if ((A1*C2 - A2*C1)*(A2*B1 - A1*B2)>Y2*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)
					&& (A1*C2 - A2*C1)*(A2*B1 - A1*B2)<Y1*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)) {
					isInteriorY1 = true;
				}
			}
			else {		// Y1==Y2
				if ((A1*C2 - A2*C1)*(A2*B1 - A1*B2) == Y1*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)) {
					isInteriorY1 = true;
				}
			}

			if (Y3<Y4) {
				if ((A1*C2 - A2*C1)*(A2*B1 - A1*B2)>Y3*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)
					&& (A1*C2 - A2*C1)*(A2*B1 - A1*B2)<Y4*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)) {
					isInteriorY2 = true;
				}
			}
			else if (Y3>Y4) {
				if ((A1*C2 - A2*C1)*(A2*B1 - A1*B2)>Y4*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)
					&& (A1*C2 - A2*C1)*(A2*B1 - A1*B2)<Y3*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)) {
					isInteriorY2 = true;
				}
			}
			else {		// Y3==Y4
				if ((A1*C2 - A2*C1)*(A2*B1 - A1*B2) == Y3*(A2*B1 - A1*B2)*(A2*B1 - A1*B2)) {
					isInteriorY2 = true;
				}
			}

			// 내부의 교점이라면 count+1
			if (isInteriorX1 && isInteriorY1 && isInteriorX2 && isInteriorY2) {
				intersectionPointCnt++;
			}

		}
	}

	return intersectionPointCnt;
}

int main() {

	// for test file input
	freopen("sample_input.txt", "r", stdin);

	cin >> N;

	// set data
	for (int i = 1; i <= N; i++) {
		for (int j = 0; j<4; j++) {
			cin >> line[i][j];
		}
	}

	findLineFunction();				// 직선의 방정식 찾기

	cout << findIntersectionPoint() << endl;		// 교점 찾기

	return 0;
}