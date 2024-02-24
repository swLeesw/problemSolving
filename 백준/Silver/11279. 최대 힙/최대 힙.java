import java.io.*;
import java.util.*;

public class Main {
	
	static int n, size;
	static int arr[];
	
	static int parent(int node) {
		if (node == 1) {
			return 1;
		}
		return node / 2;
	}
	
	static int left(int node) {
		return node * 2;
	}
	
	static int right(int node) {
		return node * 2 + 1;
	}
	
	
	static void insert(int value) {
		if (size == 0) {
			arr[++size] = value;
			return;
		}
		arr[++size] = value;
		int cur = size;
		while (cur > 1 && arr[cur] > arr[parent(cur)]) {//아래가 더 크면
			int par = parent(cur);
			int tmp = arr[cur];
			arr[cur] = arr[par];
			arr[par] = tmp;
			cur = par;
		}	
	}
	
	static int remove() {
		if (size == 0) return 0;
		int maxValue = arr[1];
		arr[1] = arr[size--]; //맨아래꺼 가져오기
		down(1);
		return maxValue;
	}
	
	static void down(int node) {
		int left = left(node);
		int right = right(node);
		int lar = node;
		
		if (left <= size && arr[left] > arr[node]) {
			lar = left;
		}
		if (right <= size && arr[right] > arr[lar]) {
			lar = right;
		}
		if (lar != node) {//가장 큰게 현재 노드가 아니면 아래로
			int tmp = arr[node];
			arr[node] = arr[lar];
			arr[lar] = tmp;
			down(lar);
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		arr = new int[n + 1];
		
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			if (a == 0) {
				System.out.println(remove());				
			} else {
				insert(a);
			}
		}
		
	}
	
}
