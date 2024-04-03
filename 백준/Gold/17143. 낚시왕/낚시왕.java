import java.io.*;
import java.util.*;

public class Main {
    
    static class Shark {
        int y, x, speed, dir, size;//위치, 속도, 방향, 크기
        
        public Shark(int y, int x, int speed, int dir, int size) {
            this.y = y;
            this.x = x;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
    
    static int r, c, m, sol;
    static Shark arr[][];
    static int dy[] = {-1, 0, 1, 0}; //위 오 아 왼
    static int dx[] = {0, 1, 0, -1};
    static Stack<Shark> stack = new Stack<>();
    
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tr, tc, ts, td, tz;
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
    	
		arr = new Shark[r][c];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			tr = Integer.parseInt(st.nextToken()) - 1;
			tc = Integer.parseInt(st.nextToken()) - 1;
			ts = Integer.parseInt(st.nextToken());
			td = Integer.parseInt(st.nextToken());
			tz = Integer.parseInt(st.nextToken());
			if (td == 1) {//위
				td = 0;
			} else if (td == 2) { //아
				td = 2;
			} else if (td == 3) { //오
				td = 1;
			} else if (td == 4) { //왼
				td = 3;
			}
			if (td % 2 == 0) { //위 아
				ts = ts % ((r - 1) * 2);
			} else { //오 왼
				ts = ts % ((c - 1) * 2);				
			}
			
			arr[tr][tc] = new Shark(tr, tc, ts, td, tz);
		}
		//input end
		
		
		for (int q = 0; q < c; q++) {
			//상어 잡기
			
			for (int j = 0; j < r; j++) {
				if (arr[j][q] != null) {
					sol += arr[j][q].size;
					arr[j][q] = null;
					break;
				}
			}
			
			if (q == c - 1) break;
			
			//상어 이동
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (arr[i][j] != null) {
						pushToStack(i, j); //상어 스택으로 이동
					}
				}
			}
			
			//상어 풀기
			
			while (!stack.isEmpty()) {
				Shark shark = stack.pop();
				
				if (arr[shark.y][shark.x] != null) { //이씅면
					//비교
					if (shark.size > arr[shark.y][shark.x].size) { //사이즈가 더 크면 교체, 아니면 그대로
						arr[shark.y][shark.x] = shark;
					}
				} else { //없으면
					arr[shark.y][shark.x] = shark;
				}	
			}
		}
		
		
		System.out.println(sol);
    	
	}
    
    static void pushToStack(int y, int x) {
    	
    	Shark shark = arr[y][x];
    	int ny = shark.y;
    	int nx = shark.x;
    	int ndir = shark.dir;
    	int remain = shark.speed;
    	if (shark.dir % 2 == 0) { //상하
    		while (remain-- > 0) {
    			if (ny + dy[ndir] < 0 || ny + dy[ndir] >= r) {
    				ndir = (ndir + 2) % 4;
    			}
    			ny += dy[ndir];
    		}	 
    		
    	} else { //좌우
    		while (remain-- > 0) {
    			if (nx + dx[ndir] < 0 || nx + dx[ndir] >= c) {
    				ndir = (ndir + 2) % 4;
    			}
    			nx += dx[ndir];
    		}
    		
    	}
    		
    	stack.push(new Shark(ny, nx, shark.speed, ndir, shark.size)); 	
    	
    	arr[y][x] = null;
    }
    
    
}