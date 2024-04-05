import java.util.*;
import java.io.*;

public class Main {
	
	
	static int n, m;
	static int indegree[];
	static int outCnt[];
	static boolean visited[];
	static List<Integer> graph[];
	static int sol;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int u = 0, v = 0;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		indegree = new int[n + 1];
		outCnt = new int[n + 1];
		graph = new ArrayList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
		}
		
		for (int i = 1; i <= n; i++) {
			bfs(i);
		}
		
		for (int i = 1;  i<= n; i++) {
			if (n - 1 == (indegree[i] + outCnt[i])) {
				sol++;
			}
		}
		
//		for (int i = 1; i <= n; i++) {
//			System.out.print(indegree[i] + " ");
//		}
//		System.out.println();
//		
//		for (int i = 1; i <= n; i++) {
//			System.out.print(outCnt[i] + " ");
//		}
//		System.out.println();
		
		System.out.println(sol);
	}
	
	static void bfs(int s) {
		int size = 0;
		visited = new boolean[n + 1];
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(s);
		visited[s] = true;
		
		while (!que.isEmpty()) {
			int cur = que.poll();
			
			for (int next : graph[cur]) {
				if (!visited[next]) {
					size++;
					indegree[next]++;
					que.offer(next);
					visited[next] = true;
				}
			}
			
		}
		outCnt[s] = size;
		
	}
	

	
}