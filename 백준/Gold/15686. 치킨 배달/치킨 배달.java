import java.util.*;
import java.io.*;

public class Main {

    static int n, m, arr[][], visited[][], chicken[][], com[];//y, x;
    static int chNum = 0, sol = 2100000000;
    static boolean cVisited[];

    static void solve() {
    	int sum = 0;//치킨거리의 합
    	
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (arr[i][j] == 1) {
    				int minDistance = 2100000000;
    				for (int k = 0; k < m; k++) {
    					//치킨 idx 최단치킨거리 찾기
    					int chX = chicken[com[k]][1];
    					int chY = chicken[com[k]][0];
    					int diff = Math.abs(chX - j) + Math.abs(chY - i);
    					if (minDistance > diff) {
    						minDistance = diff;
    					}
    				}
    				sum += minDistance;
    			}
    		}
    	}
    	
    	sol = Math.min(sol, sum);
    	
    }
    
    static void comb(int r, int c) {
    	if (c == m) {
    		solve();
            return;
        }

        for (int i = r; i < chNum; i++) {
            if (!cVisited[i]) {
                cVisited[i] = true;
                com[c] = i;
                comb(i + 1, c + 1);
                cVisited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    chNum++;
                    
                }
            }
        }
        
        
        chicken = new int[chNum][2];
        cVisited= new boolean[chNum];
        com = new int[m];
        int tm = 0;
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (arr[i][j] == 2) {
        			chicken[tm][0] = i;
        			chicken[tm][1] = j;
        			tm++;
        		}
        	}
        }
        
        comb(0, 0);

        System.out.println(sol);

    }
}