import java.io.*;
import java.util.*;

public class Main {
	
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}		
	}
	
	static int connectedCnt;
	static int n, m, arr[][], connected[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {-1, 0, 1, 0};
	static Set<Integer> set;
	static Map<Integer, Integer> map = new HashMap<>();//각 그룹별 개수
	
	
	static boolean isRange(int y, int x) {
		return (y >= 0 && x >= 0 && y < n && x < m);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		connected = new int[n][m];
		
		//입력
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
			
		//연결요소 + sum 구하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0 && connected[i][j] == 0) {
					map.put(++connectedCnt, moveCnt(i, j));
				}
			}
		}
		
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j ++) {
				sb.append(getSum(i, j));
			}
			sb.append("\n");
		}
//		System.out.println("---------");
//		
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j ++) {
//				System.out.print(connected[i][j]);
//			}
//			System.out.println();
//		}
//		
//		System.out.println("----------");
//		for (Integer key : map.keySet()) {
//			System.out.println(map.get(key));
//		}
		System.out.println(sb.toString());
	}
	
	static int getSum(int sy, int sx) {
		if (arr[sy][sx] == 0) return 0;
		int sum = 1;
		set = new HashSet<>();
		
		for (int i = 0; i < 4; i++) {
			int ny = sy + dy[i];
			int nx = sx + dx[i];
			
			if (isRange(ny, nx) && arr[ny][nx] != 1) {
				set.add(connected[ny][nx]);
			}
			
		}
		
		for (Integer i : set) {
			sum += map.get(i);
		}
		
		
		return sum % 10;
	}
	
	
	static int moveCnt(int sy, int sx) {
		int cnt = 1;
		Queue<Pos> que = new LinkedList<>();
		que.offer(new Pos(sy, sx));
		connected[sy][sx] = connectedCnt;
		
		while (!que.isEmpty()) {
			Pos cur = que.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (!isRange(ny, nx)) continue;
				
				if (arr[ny][nx] == 0 && connected[ny][nx] == 0) {
					connected[ny][nx] = connectedCnt;
					que.offer(new Pos(ny, nx));
					cnt++;
				}
			}
			
		}
		
		
		
		return cnt;
	}
	
}
