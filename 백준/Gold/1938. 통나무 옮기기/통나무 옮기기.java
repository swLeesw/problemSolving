import java.io.*;
import java.util.*;

public class Main {
	
	static class Gicha {
		Pos mid;
		int dir;
		public int cnt;
		public Gicha(Pos mid, int dir, int cnt) {
			this.mid = mid;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
	
	static class Pos {
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	
	static int n;
	static int arr[][];
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	static int dy8[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int dx8[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static Gicha gicha, arv;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		Pos tg[] = new Pos[3];
		Pos ta[] = new Pos[3];
		int g = 0;
		int a = 0;
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				char c = str.charAt(j);
				
				if (c == '1') {
					arr[i][j] = 1;
				} else if  (c == 'B') {
					tg[g++] = new Pos(i, j);
				} else if (c == 'E') {
					ta[a++] = new Pos(i, j);					
				}
			}
		}
		
		//기차 생성 0은 가로, 1은 세로로 하자
		if (tg[0].y == tg[1].y) {
			gicha = new Gicha(tg[1], 0, 0); //가로
		} else {
			gicha = new Gicha(tg[1], 1, 0); //세로			
		}
		
		//도착지점 생성
		if (ta[0].y == ta[1].y) {
			arv = new Gicha(ta[1], 0, 0);
		} else {
			arv = new Gicha(ta[1], 1, 0);			
		}
		

		
		//bfs를 이용해서 기차의 최단거리를 찾아보자.
		System.out.println(bfs());
		
		
	}
	
	static boolean isRange(int y, int x) {
		return y >= 0 && x >= 0 && y < n && x < n;
	}
	
	
	//0은 가로, 1은 세로
	static int bfs() {
		boolean visited[][][] = new boolean[n][n][2]; //가로, 세로를 고려한 3차원 배열
		PriorityQueue<Gicha> que = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
		que.offer(gicha);
		visited[gicha.mid.y][gicha.mid.x][gicha.dir] = true;
		
		while (!que.isEmpty()) {
			Gicha cur = que.poll();
			if (cur.mid.y == arv.mid.y && cur.mid.x == arv.mid.x && cur.dir == arv.dir) {
				return cur.cnt;
			}
			
			//턴 체크
			boolean tCheck = true;
			
			for (int i = 0; i < 8; i++) {
				int ny = cur.mid.y + dy8[i];
				int nx = cur.mid.x + dx8[i];
				
				if (!isRange(ny, nx) || arr[ny][nx] == 1) {
					tCheck = false;
					break;
				}
			}
			
			int nt = cur.dir == 0 ? 1 : 0;
			
			if (tCheck && !visited[cur.mid.y][cur.mid.x][nt]) {
				que.offer(new Gicha(cur.mid, nt, cur.cnt + 1));
				visited[cur.mid.y][cur.mid.x][nt] = true;
			}
			
			
			//이동 체크
			for (int i = 0; i < 4; i++) {
				int ny = cur.mid.y + dy[i];
				int nx = cur.mid.x + dx[i];
				boolean mCheck = true;
				
				
				if (!isRange(ny, nx)) continue;
				
				if (cur.dir == 0) { //가로
					for (int j = -1; j < 2; j++) {
						if (!isRange(ny, nx + j) || arr[ny][nx + j] == 1) {
							mCheck = false;
							break;
						}
					}					
				} else {
					for (int j = -1; j < 2; j++) {
						if (!isRange(ny + j, nx) || arr[ny + j][nx] == 1) {
							mCheck = false;
							break;
						}						
					}
				}
				
				if (mCheck && !visited[ny][nx][cur.dir]) {
					que.offer(new Gicha(new Pos(ny, nx), cur.dir, cur.cnt + 1));
					visited[ny][nx][cur.dir] = true;
				}
			}
		}
		return 0;
	}
	
}
