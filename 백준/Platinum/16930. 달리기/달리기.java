import java.io.*;
import java.util.*;

public class Main {
	
	static class Pos implements Comparable<Pos>{
		int y, x, dir, depth, k; //방향, 깊이, 남은 k의 수
		
		public Pos(int y, int x, int dir, int depth, int k) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.depth = depth;
			this.k = k;
		}

		public int compareTo(Pos o) {
			return this.depth - o.depth;
		}
		
		
		
	}
	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < m) return true;
		else return false;
	}
	
	static void bfs() {
		PriorityQueue<Pos> que = new PriorityQueue<>();
		
		for (int i = 0; i < 4; i++) {
			visited[sy][sx][i] = 0;
		}
		que.offer(new Pos(sy ,sx, -100, 0, k - 1));
		
		while(!que.isEmpty()) {
			Pos cur = que.poll();
			
			if (cur.y == ey && cur.x == ex) break;
			
			for (int i = 0; i < 4; i++) { //3방향 검사
				if ((cur.dir + 2) % 4 == i) continue; //반대 방향
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (!isRange(ny, nx)) continue; //범위
				if (arr[ny][nx] == 1) continue; //벽
				
				if (cur.dir == i) { //같은 방향
					int nd, nk;//다음 초, 다음 남은 k
					//k고려하자
					if (cur.k == 0) { //남은 k가 없으면 초 증가, k 초기화
						nd = cur.depth + 1;
						nk = k - 1;
					} else { //남은 초가 있으면 초 그대로, k -1
						nd = cur.depth;
						nk = cur.k - 1;
					}
					
					if (visited[ny][nx][i] > nd) { //새로운 초가 더 작거나 같아야 함
						que.offer(new Pos(ny, nx, i, nd, nk));
						visited[ny][nx][i] = nd;
					}
					
				} else { //다른 방향
					//무조건 k 초기화 and depth + 1
					int nd = cur.depth + 1;
					if (visited[ny][nx][i] > nd) { //더 작거나 같은 경우
						que.offer(new Pos(ny, nx, i, nd, k - 1));
						visited[ny][nx][i] = nd;
					}
				}
			}
			
			
		}
		
		
		
	}
	
	static int n, m, k, arr[][], visited[][][], sy, sx, ey, ex;
	
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new int[n][m][4];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < 4; k++) {
					visited[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				char c = str.charAt(j);
				if (c == '#') {
					arr[i][j] = 1;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken()) - 1;
		sx = Integer.parseInt(st.nextToken()) - 1;
		ey = Integer.parseInt(st.nextToken()) - 1;
		ex = Integer.parseInt(st.nextToken()) - 1;
		bfs();
		
		int sol = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			sol = Math.min(sol, visited[ey][ex][i]);
		}
		if (sol == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(sol);
		}
	}
	
}
