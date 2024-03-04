import java.util.*;
import java.io.*;

public class Main {
	
	static int n, arr[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2) -> {
			if (o1[1] != o2[1]) {
				return o1[1] - o2[1];
			} 
			return o1[0] - o2[0];
		});
		
		int sol = 1;
		int e = arr[0][1];
		
		for (int i = 1; i < n; i++) {
			if (arr[i][0] >= e) {
				e = arr[i][1];
				sol++;
			}
		}
		
		System.out.println(sol);
	}
	
}