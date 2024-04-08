import java.util.*;
import java.io.*;

public class Main {

	static int n, arr[], path[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n + 1];
		path =  new int[n + 1];
		
		String str = "";
		
		arr[1] = 0;
		
		for (int i = 2; i <= n; i++) {
			arr[i] = arr[i - 1] + 1;// -1부분
			path[i] = i - 1;
			if (i % 3 == 0 && arr[i / 3] + 1 < arr[i]) {//나눌 수 있음, 이전 나눈값 + 1
				arr[i] = arr[i / 3] + 1;
				path[i] = i / 3;
			}
			if (i % 2 == 0 && arr[i / 2] + 1 < arr[i]) {
				arr[i] = arr[i / 2] + 1;
				path[i] = i / 2;
			}
		}
		System.out.println(arr[n]);
		
		while (n > 0) {
			System.out.print(n + " ");
			n = path[n];
		}
		
	}
}
