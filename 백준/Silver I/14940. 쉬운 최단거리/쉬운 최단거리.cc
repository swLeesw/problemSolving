#include <iostream>
#include <queue>
#include <vector>
#define MAX 100000000

using namespace std;

int arr[1000][1000];
int dist[1000][1000];
int n, m;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1};

bool isRange(int y, int x) {
	if (x >= 0 && y >= 0 && x < m && y < n) {
		return true;
	}
	return false;
}

void bfs(int sy, int sx) {
	queue<pair<int, int>> que;
	
	que.push({ sy, sx });

	while (!que.empty()) {
		int y = que.front().first;
		int x = que.front().second;
		que.pop();
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (isRange(ny, nx) && arr[ny][nx] != 0 && dist[ny][nx] > arr[ny][nx] + dist[y][x]) {
				dist[ny][nx] = arr[ny][nx] + dist[y][x];
				que.push({ ny, nx });
			}
		}
	}


}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int sy = 0, sx = 0;

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			dist[i][j] = MAX;
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int a;
			cin >> a;
			if (a == 2) {
				sy = i;
				sx = j;
				dist[i][j] = 0;
			}
			arr[i][j] = a;
		}
	}

	bfs(sy, sx);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (arr[i][j] == 0) {
				cout << 0 << ' ';
			}
			else if (arr[i][j] == 1 && dist[i][j] == MAX) {
				cout << -1 << ' ';
			}
			else {
				cout << dist[i][j] << ' ';
			}
		}
		cout << '\n';
	}

	return 0;
}