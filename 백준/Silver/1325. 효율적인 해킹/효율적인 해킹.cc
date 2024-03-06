#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m, ms;
vector<int> graph[10001];
int maxCnt[10001];
bool visited[10001];

void init() {
	for (int i = 1; i <= n; i++) {
		visited[i] = false;
	}
}

void bfs(int s) {
	queue<int> que;
	que.push(s);
	visited[s] = true;

	while (!que.empty()) {
		int cur = que.front();
		que.pop();
		for (int i = 0; i < graph[cur].size(); i++) {
			int next = graph[cur][i];
			if (!visited[next]) {
				visited[next] = true;
				maxCnt[next]++;
				que.push(next);
			}
		}
	}
}


int main(void) {
	cin >> n >> m;
	int u, v;
	for (int i = 0; i < m; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
	}

	for (int i = 1; i <= n; i++) {
		init();
		bfs(i);
	}

	for (int i = 1; i <= n; i++) {
		if (ms < maxCnt[i]) {
			ms = maxCnt[i];
		}
	}

	for (int i = 1; i <= n; i++) {
		if (ms == maxCnt[i]) {
			cout << i << " ";
		}
	}


	return 0;
}