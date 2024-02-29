import java.util.*;
import java.io.*;



public class Main {
	
	static class Pos {
		public int y, x, depth;
		
		public Pos(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}
	
	static int n, arr[][], bx, by, visited[][], sol;
	static int babyWeight = 2, exp;
	//처음 아기상어 크기는 2  크기 작으면 먹기 같으면 그냥 지나가기 크면 못지나감(벽)
	static int dx[] = {0, -1, 1, 0}; //위 왼 오 아
	static int dy[] = {-1, 0, 0, 1};
	
	static boolean isRange(int y, int x) {
		if (x >= 0 && y >= 0 && x < n && y < n && arr[y][x] <= babyWeight) {
			return true;
		}
		return false;
	}
	
	static Pos find() { //가장 가까운 고기물 찾는 함수. 못찾으면 -1 반환
		visited = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		Queue<Pos> que = new ArrayDeque<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {//y, x, depth
			if (o1[2] != o2[2]) {
				return o1[2] - o2[2];
			}
			if (o1[0] != o2[0]) {
				return o1[0] - o2[0];
			}
			return o1[1] - o2[1];
		});
		que.offer(new Pos(by, bx, 0));
		visited[by][bx] = 0;
		while (!que.isEmpty()) {
			Pos cur = que.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (isRange(ny, nx) && visited[cur.y][cur.x] + 1 < visited[ny][nx]) { //현재노드 + 1이 다음 노드보다 작으면
					visited[ny][nx] = visited[cur.y][cur.x] + 1; //다음 노드 갱신
					if (arr[ny][nx] != 0 &&arr[ny][nx] < babyWeight) {//먹을 수 있으면
						pq.offer(new int[] {ny, nx, visited[cur.y][cur.x] + 1});
					}
					que.offer(new Pos(ny, nx, cur.depth + 1)); //큐에 넣기
				}
			}
		}
		if (pq.isEmpty()) return null;
		int[] tmp = pq.poll();
		return new Pos(tmp[0], tmp[1], tmp[2]);
	}
	
	static void solve() {	
		while (true) {
			Pos gogi = find();
			if (gogi == null) break;
			exp++;//경치!
			if (exp == babyWeight) {//경치 업 되면 레벨업
				babyWeight++;
				exp = 0;
			}
			arr[by][bx] = 0; //있던 자리 초기화
			by = gogi.y; //이동
			bx = gogi.x;
			sol += gogi.depth;//이동거리 추가
		}	
		System.out.println(sol);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());		
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					bx = j;
					by = i;
				}
			}
		}		
		solve();
	}
}
