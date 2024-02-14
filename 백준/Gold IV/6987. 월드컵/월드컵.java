import java.util.*;
import java.io.*;

public class Main {
	
	static int arr[][] = new int[6][3];
	static boolean flag;
	static int left[] = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};//15가지 경우
	static int right[] = {1, 2 ,3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	
	
	
	static boolean solve(int game) {
		if (game == 15) return true; //끝까지 갔다는 것은 살아 남았다는 것.
		//판별
		//승 0--, 2--, 무승 1--, 1--, 패 2--, 0--
		int lTeam = left[game];
		int rTeam = right[game];
		//승
		if (arr[lTeam][0] - 1 >= 0 && arr[rTeam][2] - 1 >= 0) {
			arr[lTeam][0]--;
			arr[rTeam][2]--;
			if (solve(game + 1)) return true;
			arr[lTeam][0]++;
			arr[rTeam][2]++;
		}
		//무승부
		if (arr[lTeam][1] - 1 >= 0 && arr[rTeam][1] - 1 >= 0) {
			arr[lTeam][1]--;
			arr[rTeam][1]--;
			if (solve(game + 1)) return true;
			arr[lTeam][1]++;
			arr[rTeam][1]++;
			
		}
		//패
		if (arr[lTeam][2] - 1 >= 0 && arr[rTeam][0] - 1 >= 0) {
			arr[lTeam][2]--;
			arr[rTeam][0]--;		
			if (solve(game + 1)) return true;			
			arr[lTeam][2]++;
			arr[rTeam][0]++;
			
		}
		
		
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int k = 0;  k < 4; k++) {
			flag = false;
			st = new StringTokenizer(br.readLine());
			int cnt = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					cnt += arr[i][j];
				}
			}
			
			
			
			if (cnt == 30) {
				flag = solve(0);
			}
			if (flag) {
				System.out.print(1 + " ");
			} else {
				System.out.print(0 + " ");
			}
		}
		
	}
	
	
}
