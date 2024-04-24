import java.io.*;
import java.util.*;

public class Main {
	
	
	
	static int n, m, arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			arr[i] = i;
		}
		
		//합집합 0, 같은 집합 확인 1
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (c == 0) {
				union(a, b);
			} else {
				int A = find(a);
				int B = find(b);
				if (A == B) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
			
			
		}
		
	}
	
	static int find(int x) {
		if (arr[x] == x) return x;
		else return arr[x] = find(arr[x]);
	}
	
	static void union(int x, int y) {
		int X = find(x);
		int Y = find(y);
		
		if (X == Y) return;
		
		if (X > Y) {
			arr[Y] = X;
		} else {
			arr[X] = Y;
		}
	}
	
}
