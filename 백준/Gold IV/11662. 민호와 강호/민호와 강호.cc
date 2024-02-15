#include <iostream>
#include <cmath>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cout << fixed;
	cout.precision(10);
	double ax, ay, bx, by, cx, cy, dx, dy;
	double mid1, mid2;
	int T = 100;
	cin >> ax >> ay >> bx >> by >> cx >> cy >> dx >> dy;
	
	while (T--) {
		//a, b의 3분탐색 지점
		double mMid1X = (2 * ax + bx) / 3;
		double mMid1Y = (2 * ay + by) / 3;
		double mMid2X = (ax + 2 * bx) / 3;
		double mMid2Y = (ay + 2 * by) / 3;
		
		//c, d의 3분탐색 지점
		double gMid1X = (2 * cx + dx) / 3;
		double gMid1Y = (2 * cy + dy) / 3;
		double gMid2X = (cx + 2 * dx) / 3;
		double gMid2Y = (cy + 2 * dy) / 3;

		//mid1, mid2 거리
		mid1 = sqrt(pow(gMid1X - mMid1X, 2) + pow(gMid1Y - mMid1Y, 2));
		mid2 = sqrt(pow(gMid2X - mMid2X, 2) + pow(gMid2Y - mMid2Y, 2));
		
		if (mid1 > mid2) {//mid1(왼쪽에 있는 지점)이 더 크면
			ax = mMid1X;
			ay = mMid1Y;
			cx = gMid1X;
			cy = gMid1Y;
		}
		else {
			bx = mMid2X;
			by = mMid2Y;
			dx = gMid2X;
			dy = gMid2Y;
		}
	}

	
	if (mid1 > mid2) {
		cout << mid2;
	}
	else {
		cout << mid1;
	}
	return 0;
}


//(2l + r) / 3