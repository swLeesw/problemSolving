import java.util.*;
import java.io.*;

public class Main {

	static class Info {
		int num;
		String str;
		public Info(int num, String str) {
			this.num = num;
			this.str = str;
		}
		
	}
	
	//D : (2 * n) % 10000
	//S : (n - 1) == 0 ? 9999 : n - 1
	//L : n *= 10, n = (n + n / 10000) % 10000 
	//R : tmp = n % 10, n = n / 10 + tmp
	
	static int t, a, b;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		t = Integer.parseInt(br.readLine());
		
		while (t-- > 0) {
			visited = new boolean[10001];
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			System.out.println(BFS());
			
		}
		
	}
	
	static String BFS() {
		String str = "";
		Queue<Info> que = new ArrayDeque<>();
		que.offer(new Info(a, ""));
		visited[a] = true;
		while (!que.isEmpty()) {
			Info cur = que.poll();
			
			if (cur.num == b) {
				return cur.str;
			}
			if (!visited[D(cur.num)]) {
				que.offer(new Info(D(cur.num), cur.str+"D"));
				visited[D(cur.num)] = true;
			}
			if (!visited[S(cur.num)]) {
				que.offer(new Info(S(cur.num), cur.str+"S"));
				visited[S(cur.num)] = true;
			}
			if (!visited[L(cur.num)]) {
				que.offer(new Info(L(cur.num), cur.str+"L"));
				visited[L(cur.num)] = true;
			}
			if (!visited[R(cur.num)]) {
				que.offer(new Info(R(cur.num), cur.str+"R"));
				visited[R(cur.num)] = true;
			}
			
		}
		
		
		return str;
	}
	
	
	static int D(int num) {
		return (2 * num) % 10000;
	}
	
	static int S(int num) {
		return (num - 1) == -1 ? 9999 : num - 1;
	}
	
	static int L(int num) {
		num *= 10;
		return (num + num / 10000) % 10000;
	}
	
	static int R(int num) {
		int tmp = num % 10;
		return (num / 10) + tmp * 1000;
	}
	
}
