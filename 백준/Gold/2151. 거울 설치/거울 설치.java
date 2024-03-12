import java.io.*;
import java.util.*;


public class Main {
	
	static class Pos {
		int y, x, dir, depth;

		public Pos(int y, int x, int dir, int depth) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.depth = depth;
		}
		
		
	}
	
	static boolean isRange(int y, int x) {
		if (x >= 0 && y >= 0 && x < n && y < n) return true;
		else return false;
	}
	
	static void bfs() {
		Queue<Pos> que = new ArrayDeque<>();
		
		for (int i = 0; i < 4; i++) {
			visited[sy][sx][i] = 0;
			que.offer(new Pos(sy, sx, i, 0));//
		}
		
		while (!que.isEmpty()) {
			Pos cur = que.poll();
			int ny = cur.y;
			int nx = cur.x;
			while (true) {//한 방향으로 직진
				ny += dy[cur.dir];
				nx += dx[cur.dir];
				if (!isRange(ny, nx)) break;//범위
				if (arr[ny][nx] == 1) break;//벽
				if (visited[ny][nx][cur.dir] <= cur.depth) break;//깊이
				
				if (arr[ny][nx] == 3) {
					if (cur.dir == 0) {//31
						que.offer(new Pos(ny, nx, 3, cur.depth + 1));
						que.offer(new Pos(ny, nx, 1, cur.depth + 1));
					} else if (cur.dir == 1) {//02
						que.offer(new Pos(ny, nx, 0, cur.depth + 1));
						que.offer(new Pos(ny, nx, 2, cur.depth + 1));
						
					} else if (cur.dir == 2) {//13
						que.offer(new Pos(ny, nx, 1, cur.depth + 1));
						que.offer(new Pos(ny, nx, 3, cur.depth + 1));
						
					} else if (cur.dir == 3) {//02
						que.offer(new Pos(ny, nx, 0, cur.depth + 1));
						que.offer(new Pos(ny, nx, 2, cur.depth + 1));
					}
				}
				visited[ny][nx][cur.dir] = cur.depth;
			}
		}
				
				
				
			
			
		
		
	}
	
	
	static int arr[][], n, visited[][][]; //4방향 저장
	static int sy = -1, sx = - 1, ey, ex;
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		visited = new int[n][n][4];
		
		for (int i = 0;  i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < 4; k++) {
					visited[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		
		for (int i = 0 ; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				char c = str.charAt(j);
				if (c == '*') {// 벽 1
					arr[i][j] = 1;
				} else if (c == '#') {//문 2
					if (sy == -1) {
						sy = i;
						sx = j;
					} else {
						ey = i;
						ex = j;
					}
					arr[i][j] = 2;
				} else if (c == '!') {//거울 설치 지역					
					arr[i][j] = 3;
				}	
			}
		}
		
		
		bfs();
		int sol = Integer.MAX_VALUE;
		
		for (int i = 0; i < 4; i++) {
			sol = Math.min(sol, visited[ey][ex][i]);
		}
		System.out.println(sol);
	}
	
}
