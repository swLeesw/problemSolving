import java.util.*;
import java.io.*;

public class Main {
	
	static class Pos {
		int y, x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}		
	}
	
	static class Bam { //뱀 객체
		int y,  x; //뱀 머리
		int dir;
		Queue<Pos> tail; //뱀 꼬리 저장
		
		public Bam() {
			y = 0;
			x = 0;
			dir = 1;
			tail = new ArrayDeque<>();
		}
	}

	static int n, k, l, arr[][], sol;
	static int dx[] = {0, 1, 0, -1}; //위 오 아 왼
	static int dy[] = {-1, 0, 1, 0};
	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < n) return true;
		else return false;
	}
	
	static boolean move(Bam bam, int time) {
		boolean flag = false;
		while (time-- > 0) {
			sol++; //답
			bam.tail.offer(new Pos(bam.y, bam.x));//꼬리 저장
			bam.y += dy[bam.dir];//앞으로 가기
			bam.x += dx[bam.dir];
			//종료조건(범위 밖, 자기자신)
			if (!isRange(bam.y, bam.x)) {
				flag = true;
				break;
			} else if (arr[bam.y][bam.x] == 1) {
				flag = true;
				break;
			}
			if (arr[bam.y][bam.x] == 0) {//아무것도 없으면 꼬리 뺴기
				Pos tailPos = bam.tail.poll();
				arr[tailPos.y][tailPos.x] = 0;
			}
			arr[bam.y][bam.x] = 1; //머리 옮기기
		}
		return flag;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int a, b;
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		boolean flag = false; //종료조건
		Bam bam = new Bam();
		arr[0][0] = 1; //뱀은 1
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			arr[a][b] = 2; //사과는 2
		}
		l = Integer.parseInt(br.readLine());
		
		for (int cas = 0; cas < l; cas++) { //l만큼
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken()) - sol;
			
			char command = st.nextToken().charAt(0);
			flag = move(bam, time);

			if (flag) {
				break;
			}
			//change dir
			if (command == 'D') {//오
				bam.dir = (bam.dir + 1) % 4;
			} else if (command == 'L') { //왼
				bam.dir -= 1;
				if (bam.dir < 0) {
					bam.dir = 3;
				}
			}
			
		}
		//안끝났으면
		if (!flag) {
			move(bam, 210000000);			
		}
		System.out.println(sol);
	}
}

