import java.util.*;
import java.io.*;

public class Main {

	static int arr[][], k, n, m, sol;
	static boolean visited[][][];
	static int dx[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int dy[] = { -2, -1, 1, 2, 2, 1, -1, -2 };

	static int sdx[] = { 0, 0, 1, -1 };
	static int sdy[] = { 1, -1, 0, 0 };

	static boolean isRange(int y, int x) {
		if (x >= 0 && y >= 0 && x < m && y < n && arr[y][x] == 0) {
			return true;
		}
		return false;
	}

	static int bfs() {
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] { 0, 0, 0, k });//y, x, lenWeight, kCnt
		visited[0][0][k] = true;
		while (!que.isEmpty()) {
			int cur[] = que.poll();
			if (cur[0] == n - 1 && cur[1] == m - 1) {//만나면
				return cur[2];
			}
			int nW = cur[2] + 1; //다음 길이
			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + sdy[i];
				int nx = cur[1] + sdx[i];
				
				if (isRange(ny, nx) && !visited[ny][nx][cur[3]]) {//범위 안 && 방문 안한 k
					visited[ny][nx][cur[3]] = true;
					que.offer(new int[] {ny, nx, nW, cur[3]});
				}
			}
			if (cur[3] > 0) {//kCnt가 0보다 크면
				for (int i = 0; i < 8; i++) {
					int ny = cur[0] + dy[i];
					int nx = cur[1] + dx[i];
					int nK = cur[3] - 1;
					
					if (isRange(ny, nx) && !visited[ny][nx][nK]) { //
						visited[ny][nx][nK] = true;
						que.offer(new int[] {ny, nx, nW, nK});
					}
					
				}
				
				
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		visited = new boolean[n][m][k + 1];
		

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());
	}
}