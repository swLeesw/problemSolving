import java.util.*;
import java.io.*;

public class Main {
	
	static class Node {
		int y, x, depth;

		public Node(int y, int x, int depth) {
			super();
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}
	
	static int t, n, sy, sx, ey, ex, sol;
	static boolean visited[][];
	static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	static void bfs() {
		visited[sy][sx] = true;
		Queue<Node> que = new ArrayDeque<>();
		que.offer(new Node(sy, sx, 0)); //y, x, depth
		
		while(!que.isEmpty()) {
			Node cur = que.poll();
			if (cur.x == ex && cur.y == ey) {
				System.out.println(cur.depth);
				return;
			}
			for (int i = 0; i < 8; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[ny][nx]) {
					que.offer(new Node(ny, nx, cur.depth + 1));
					visited[ny][nx] = true;
				}
				
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		t = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			
			sol = Integer.MAX_VALUE;
			visited = new boolean[n][n];
			bfs();
		}
	}
	
}