import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
		
	static int n, arr[][];
	
	static void solve(int y, int x, int size) {
		int c = arr[y][x];
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (c != arr[i][j]) {
					System.out.print("(");
					solve(y, x, size / 2);
					solve(y, x + size / 2, size / 2);
					solve(y + size / 2, x, size / 2);
					solve(y + size / 2, x + size / 2, size / 2);
					System.out.print(")");
					return;
				}
			}
		}
		System.out.print(c);
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		solve(0, 0, n);
	}	
		
}
