import java.io.*;
import java.util.*;

public class Main {
	
	
	
	
	static void dfs(int nu, int idx) {
		
		if (idx == n) {
			maxNum = Math.max(maxNum, nu);
			minNum = Math.min(minNum, nu);
		}
		
		for (int i = 0; i < 4; i++) {
			
			if (op[i] == 0) continue;
			
			op[i] -= 1;
			
			switch(i) {
			
			case 0 : dfs(nu + num[idx], idx + 1);
			break;			
			case 1 : dfs(nu - num[idx], idx + 1);
			break;
			case 2 : dfs(nu * num[idx], idx + 1);
			break;			
			case 3 : dfs(nu / num[idx], idx + 1);
			break;
			}
			
			op[i] += 1;
		}
		
		
	}
	
	static int num[], op[], n;
	static int maxNum = Integer.MIN_VALUE;
	static int minNum = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		num = new int[n];
		op = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		dfs(num[0], 1);
		System.out.println(maxNum);
		System.out.println(minNum);
	}
}