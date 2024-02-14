import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c, arr[][], visited[][], sol;
	static int dx[] = {1, 1, 1}; //↗→↘
	static int dy[] = {-1, 0, 1};	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < r && x < c && arr[y][x] == 0) {
			return true;
		}
		return false;
	}
	
	static boolean dfs(int sy, int sx) {
		visited[sy][sx] = 1;
		if (sx == c - 1) {
			return true;
		}
		for (int i = 0; i < 3; i++) {
			int ny = dy[i] + sy;
			int nx = dx[i] + sx;
			if (isRange(ny, nx) && visited[ny][nx] == 0) {//범위 안 && 방문x
				if (dfs(ny, nx)) {
					return true;
				}
			}
		}
		
		return false;
		//visited[sy][sx] = 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new int[r][c];
		visited = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				if (str.charAt(j) == 'x') {
					arr[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < r; i++) {
			if (arr[i][0] == 0) {
				if (dfs(i, 0)) {
					sol++;
				}
			}
		}
		
		System.out.println(sol);
		
	}
	
	
}
