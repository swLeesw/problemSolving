#include <iostream>
#include <vector>

using namespace std;

int arr[100001];
int n, s;
int sol = 100001;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	cin >> n >> s;

	for (int i = 1; i <= n; i++) {
		cin >> arr[i];
		arr[i] += arr[i - 1];
	}
	int start = 0, end = 1;

	while (start < end && end <= n) {
		int sum = arr[end] - arr[start];

		if (sum < s) { //합이 더 작으면
			end++;
			continue;
		}
		//더 크거나 같으면

		sol = min(sol, end - start);
		start++;
	}

	if (sol == 100001) {
		cout << 0;
	}
	else {
		cout << sol;
	}
	
	return 0;
}