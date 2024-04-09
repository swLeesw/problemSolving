import java.io.*;
import java.util.*;

public class Main {
	
	static int t, n;
	static int sol = Integer.MAX_VALUE;
	static int arr[][] = new int[10][10];
	
	static int remainPaper[] = new int[6]; //0번 idx 안 씀
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int zone = 0;
		Arrays.fill(remainPaper, 5); //init paper
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) zone++;
			}
		}
		//색종이의 크기는 1 x 1, 2 x 2, 3 x 3, 4 x 4, 5 x 5 5가지 종류 5개
		
		dfs(0, 0, zone, 0);
		System.out.println(sol == Integer.MAX_VALUE ? -1 : sol);
		
	}
	
	static void dfs(int y, int x, int remain, int usedPaper) {
		if (remain == 0) { //종이 다 떨어짐	
			sol = Math.min(sol, usedPaper);
			return;
		}
		
		for (int i = y; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (arr[i][j] == 1) {
					for (int k = 1; k <= 5; k++) { //색종이의 종류 5
						if (remainPaper[k] > 0 && paperCheck(i, j, k)) {
							paperStatus(i, j, k, 0);
							remainPaper[k]--;
							dfs(y, x, remain - k * k, usedPaper + 1);
							paperStatus(i, j, k, 1);
							remainPaper[k]++;
						}
					}
					return;
				}
				
								
			}
		}
		
	}
	
	
	static boolean paperCheck(int y, int x, int size) {
		if (y + size - 1 >= 10 || x + size - 1 >= 10) return false;
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (arr[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	static void paperStatus(int y, int x, int size, int status) {
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				arr[i][j] = status;
			}
		}
	}
	
	
}