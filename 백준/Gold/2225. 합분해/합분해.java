import java.io.*;
import java.util.*;

public class Main {
	
	
	
	static int n, k, arr[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); //숫자 개수(0 포함)
		k = Integer.parseInt(st.nextToken()); //몇개 뽑아서 쓰는지?
		
		arr = new int[n + 1][k + 1];
		
		for (int i = 1; i <= k; i++) {
			arr[0][i] = 1;
		}
		
		
		for (int i = 1; i <= n; i ++) {
			for (int j = 1; j <= k; j++) {
				arr[i][j] = (arr[i - 1][j] + arr[i][j - 1]) % 1000000000;
			}
		}
		
		System.out.println(arr[n][k]);
	}
	

}
