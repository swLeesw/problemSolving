import java.util.*;
import java.io.*;

public class Main {
	
	static int arr[][], n;
	static long dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		dp = new long[n][n];
		dp[0][0] = 1;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dp[i][j] != 0 && arr[i][j] != 0) {
					if (i + arr[i][j] < n) dp[i + arr[i][j]][j] += dp[i][j];
					if (j + arr[i][j] < n) dp[i][j + arr[i][j]] += dp[i][j];
				}
			}
		}
		
		System.out.println(dp[n - 1][n - 1]);
		
	}
	
	
}
