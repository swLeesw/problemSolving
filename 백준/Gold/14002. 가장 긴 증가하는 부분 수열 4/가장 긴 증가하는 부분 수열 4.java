import java.io.*;
import java.util.*;

public class Main {
	
	static int arr[], lis[], sol[], n;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        lis = new int[n];
        sol = new int[n];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int size = 1;
        lis[0] = arr[0];
        sol[0] = 0; //답 배열
        for (int i = 1; i < n; i++) {
        	int start = 0;
        	int end = size - 1;
        	int target = arr[i];
        	while (start <= end) {
        		int mid = (start + end) / 2;
        		
        		if (lis[mid] < target) {
        			start = mid + 1;
        		} else {
        			end = mid - 1;
        		}
        		
        	}
        	
        	if (size == start) {//새로운거 추가
        		lis[size++] = target;
        	} else {
        		lis[start] = target;
        	}
        	sol[i] = start;
        	
        	
        }
        
        
        int sIdx = size - 1;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
        	if (sIdx == sol[i]) {
        		stack.push(arr[i]);
        		sIdx--;
        	}
        }
        System.out.println(size);
        while (!stack.isEmpty()) {
        	System.out.print(stack.pop() + " ");
        }
    }
    
}