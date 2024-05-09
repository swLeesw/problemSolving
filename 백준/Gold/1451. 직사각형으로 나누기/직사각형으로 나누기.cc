#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstdio>
#include <algorithm>

using namespace std;

int square[51][51];
int n, m;
long long maxValue = -1;
long long getSum(int startY, int endY, int startX, int endX) {
	long long sum = 0;

	for (int i = startY; i <= endY; i++) {
		for (int j = startX; j <= endX; j++) {
			sum += square[i][j];
		}
	}
	return sum;
}

//range is 

long long solution() {
	long long first = 0;
	long long second = 0;
	long long third = 0;//구간별 합을 저장.
	//(0, i - 1), (i, j - 1), (j, n - 1) 
	//1번
	for (int i = 1; i <= n - 2; i++) { //i ~ n - 3까지가 범위
		for (int j = i + 1; j <= n - 1; j++) {
			first = getSum(0, i - 1, 0, m - 1);
			second = getSum(i, j - 1, 0, m - 1);
			third = getSum(j, n - 1, 0, m - 1);
			maxValue = max(maxValue, first * second * third);
		}
	}


	//2번
	for (int i = 1; i <= m - 2; i++) {
		for (int j = i + 1; j <= m - 1; j++) {
			first = getSum(0, n - 1, 0, i - 1);
			second = getSum(0, n - 1, i, j - 1);
			third = getSum(0, n - 1, j, m - 1);
			maxValue = max(maxValue, first * second * third);
		}
	}



	//3번
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
			first = getSum(0, n - 1, 0, i - 1);
			second = getSum(0, j - 1, i, m - 1);
			third = getSum(j, n - 1, i, m - 1);
			maxValue = max(maxValue, first * second * third);
		}
	}
	

	//4번
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
			first = getSum(0, n - 1, i, m - 1);
			second = getSum(0, j - 1, 0, i - 1);
			third = getSum(j, n - 1, 0, i - 1);
			maxValue = max(maxValue, first * second * third);
		}
	}


	//5번
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n - 1; j++) {
			first = getSum(0, j - 1, 0, m - 1);
			second = getSum(j, n - 1, 0, i - 1);
			third = getSum(j, n - 1, i, m - 1);
			maxValue = max(maxValue, first * second * third);
		}
	}


	//6번
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n - 1; j++) {
			first = getSum(j, n - 1, 0, m - 1);
			second = getSum(0, j - 1, 0, i - 1);
			third = getSum(0, j - 1, i, m - 1);
			maxValue = max(maxValue, first * second * third);
		}
	}


	return maxValue;
}



int main(void) {
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			char c;
			cin >> c;
			square[i][j] = c - '0';
		}
	}

	cout <<	solution();


	return 0;
}