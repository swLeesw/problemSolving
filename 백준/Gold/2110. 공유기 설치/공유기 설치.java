import java.util.*;
import java.io.*;

public class Main {
	
	static int n, c, arr[], s, e, m, sol;
	
	static void binarySearch(int low, int high) {
		int mid;
		
		if (low <= high) {
			mid = (low + high) / 2;//중간값
			
			if (solve(mid)) {//설치 성공 했으면 늘려보자
				binarySearch(mid + 1, high);
			} else {//설치 실패했으면 줄여보자
				binarySearch(low, mid - 1);
			}
		}
	}
	
	static boolean solve(int mid) {
		int idx = 0;
		int cRemain = c - 1;//0에 설치는 무조건 함
		for (int i = 1; i < n; i++) {
			if (cRemain == 0) break;
			if (arr[i] - arr[idx] >= mid) {//둘의 차가 mid와 같거나 그 이상이면
				cRemain--;
				idx = i;
			}
		}
		
		if (cRemain == 0) {//설치 다 했으면 true
			sol = Math.max(sol, mid);
			return true;
		} else { //설치 못했으면 false
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		for (int i = 0; i  < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		//binarySearch(1, (arr[n - 1] + arr[0]) / c);
		binarySearch(1, arr[n - 1]);
		
		System.out.println(sol);
	}
	
}
