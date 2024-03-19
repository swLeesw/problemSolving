import java.util.*;
import java.io.*;

public class Main {

	static int n, s, e, arr[];
	static int sol = 2100000000;
	static int solS, solE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		s = 0;
		e = n - 1;
		
		while (s < e) {
			int sum = arr[s] + arr[e];
			if (sum == 0) { //0이면 그만 탐색
				solS = s;
				solE = e;
				break;
			}
			if (sol > Math.abs(sum)) { //절댓값이 더 작으면
				solS = s;
				solE = e;
				sol = Math.abs(sum);
			}
			
			if (sum > 0) { //0보다 크면 값 줄이기
				e--;
			} else if (sum < 0) { //0보다 작으면 값 늘리기
				s++;
			}
		}
		
		System.out.println(arr[solS] + " " + arr[solE]);
	}
		
}
