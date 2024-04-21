import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int indegree[];
	static boolean visited[];
	static List<Integer> graph[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		indegree = new int[n + 1];
		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int u = Integer.parseInt(st.nextToken());
			int v = 0;
			while (st.hasMoreTokens()) {
				v = Integer.parseInt(st.nextToken());
				indegree[v] += 1;
				graph[u].add(v);
				u = v;
			}
		}
		solve();
		
	}
	
	static void solve() {
		int checked = 0;
		StringBuilder sb = new StringBuilder();
		visited = new boolean[n + 1];
		Queue<Integer> que = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				que.offer(i);
				visited[i] = true;
				sb.append(i + "\n");
				checked++;
			}
		}
		
		
		while (!que.isEmpty()) {
			int cur = que.poll();
			
			for (int next : graph[cur]) {
				indegree[next]--;
				if (!visited[next] && indegree[next] == 0) {
					que.offer(next);
					visited[next] = true;
					sb.append(next + "\n");
					checked++;
				}	
			}
		}
		if (checked == n) System.out.println(sb);
		else System.out.println(0);
	}
	
	
}
