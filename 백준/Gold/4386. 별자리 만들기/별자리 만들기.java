import java.io.*;
import java.util.*;

public class Main {
	
	
	static class Pos {
		double y, x;

		public Pos(double y, double x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Edge {
		int u, v;
		double w;
		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	
	static int n;
	static double sol;
	static Pos nodeList[];
	static int node[];
	public static void main(String[] args) throws IOException {
		PriorityQueue<Edge> que = new PriorityQueue<>((o1, o2) -> {
			if (o1.w - o2.w < 0) {
				return -1;
			} else {
				return 1;
			}
		});
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		node = new int[n];
		for (int i = 0; i < n; i++) {
			node[i] = i;
		}
		nodeList = new Pos[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double y = Double.parseDouble(st.nextToken());
			double x = Double.parseDouble(st.nextToken());
			nodeList[i] = new Pos(y, x);
		}
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				double beforeDist = Math.pow(Math.abs(nodeList[i].y - nodeList[j].y),2) + Math.pow(Math.abs(nodeList[i].x - nodeList[j].x),2);
				double dist = Math.sqrt(beforeDist);
				
				que.offer(new Edge(i, j, dist));
				que.offer(new Edge(j, i, dist));
			}
		}
		int size = 0;
		
		while (!que.isEmpty()) {
			Edge cur = que.poll();
			
			if (union(cur.u, cur.v)) {
				sol += cur.w;
				if (++size == n - 1) {
					break;
				}
			}
		}
		System.out.println(sol);

	}

	static int find(int x) {
		if (node[x] == x) return x;
		else return node[x] = find(node[x]);
	}
	
	static boolean union(int x, int y) {
		int X = find(x);
		int Y = find(y);
		
		if (X == Y) return false;
		
		node[X] = Y;
		
		return true;
	}
	
}
