import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
		// 자식 노드
		Map<Character, Node> chiledNode = new HashMap<>();
		boolean isLast;
	}
	
	static class Trie {
		Node root = new Node();
		
		//check 함수 없이 insert만으로 판별
		boolean insert(String number) {
			Node thisNode = root;
			
			for (int i = 0; i < number.length(); i++) {
				char c = number.charAt(i);
				
				if (thisNode.chiledNode.get(c) == null) { //문자가 없으면
					thisNode.chiledNode.put(c, new Node());//새로 만듬
				}
				
				thisNode = thisNode.chiledNode.get(c); //만들었거나 이미 있는 곳으로 이동
				if (thisNode.isLast) { //문자열이 마지막이라면 일관성 없다는 것
					return false;
				}
			}
			
			if (thisNode.chiledNode.size() != 0) {//자식 노드가 존재 = 일관성 x(91125 이후 911을 입력한 경우를 생각)
				return false;
			}
			//다 통과했다면, 일관성이 있다는 것
			thisNode.isLast = true;
			return true;
		}
	}
	
	
	static int t, n;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			boolean flag = true; //단어 존재 확인
			Trie trie = new Trie();
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				if (!trie.insert(str)) { //일관성 조사
					flag = false;
				}
				
			}
	
			if (flag) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		
	}
	
	
}