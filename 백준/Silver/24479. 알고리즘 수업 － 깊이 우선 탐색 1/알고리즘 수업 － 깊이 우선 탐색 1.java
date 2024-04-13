import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m, r;
	static int order;
	static List<Integer> graph[];
	static int visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		visited = new int[n + 1];
		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		for (int i = 1; i <= n; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(r);
		
		for (int i = 1; i <= n; i++) {
			System.out.println(visited[i]);
		}
		
	}
	
	
	static void dfs(int start) {
		visited[start] = ++order;
		
		for (int i = 0; i < graph[start].size(); i++) {
			int next = graph[start].get(i);
			if (visited[next] == 0) {
				dfs(next);
			}
		}
		
		
	}
	
}
