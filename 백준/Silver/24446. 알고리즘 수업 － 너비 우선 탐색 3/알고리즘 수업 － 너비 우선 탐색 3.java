import java.io.*;
import java.util.*;

public class Main {

	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int N, M, R;
	private static List<Integer> []list;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		list=new ArrayList[N+1];
		for(int i=1;i<=N;i++) list[i]=new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			list[u].add(v);
			list[v].add(u);
		}
		bfs(R);
	}

	private static void bfs(int x) {
		Queue<Integer> queue=new LinkedList<>();
		queue.add(x);
		
		boolean []visited=new boolean[N+1];
		visited[x]=true;

		int dep=0;
		int []depth=new int[N+1];
		for(int i=1;i<=N;i++) depth[i]=-1;
		while(!queue.isEmpty()) {
			int size=queue.size();
			while(size-->0) {
				int q=queue.poll();
				
				depth[q]=dep;
				for(int i:list[q]) {
					if(!visited[i]) {
						visited[i]=true;
						queue.add(i);
					}
				}
			}
			dep++;
		}
		
		for(int i=1;i<=N;i++) System.out.println(depth[i]);
	}
}