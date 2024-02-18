
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
	static int arr[][], visited[][], n, t;
	static final int MAX = 2100000000;
	static int dx[]  = {1, 0, -1, 0};
	static int dy[]  = {0, 1, 0, -1};	
	
	static boolean isRange(int y, int x) {
		if (x >= 0 && y >= 0 && x < n && y < n) {
			return true;
		}
		return false;
	}
	
	static void bfs(int sy, int sx) {
		Queue<int[]> queue = new ArrayDeque<>(); //x, y 저장
		queue.offer(new int[] {sy, sx});
		
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				
				if (isRange(ny, nx) && visited[ny][nx] > visited[cur[0]][cur[1]] + arr[ny][nx]) {
					visited[ny][nx] = visited[cur[0]][cur[1]] + arr[ny][nx];
					queue.offer(new int[] {ny, nx});
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			visited = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}
			for (int i = 0; i < n; i++) {
				Arrays.fill(visited[i], MAX);
			}
			visited[0][0] = 0;
			//입력 및 초기화
			bfs(0, 0);
			
			System.out.println("#" + tc + " " + visited[n - 1][n - 1]);
			
		}
		
		
		
	}
	
}
