import java.util.*;
import java.io.*;

public class Main {
	
	static int n, person[][], com[], outCnt, score, ru[], sol;// 이닝 수, person [이닝][0 ~ 8번 선수가 칠 타구]
	static int idx;//순서(첫 순서는 0)
	static boolean visited[], cVisited[];
	
	static int doIning(int ining) {
		outCnt = 0;
		int iningScore = 0;//이닝 스코어 초기화
		Arrays.fill(ru, -1);//루수에 아무도 없으면 -1
		
		while (outCnt < 3) {//3아웃이면 종료
			int tajaNum = com[idx];//현재 타자번호 정보
			if (visited[tajaNum]) { //타자가 루에 나가 있으면 다음 타자로
				idx = (idx + 1) % 9;//9번 타자 다음 다시 1번 타자로(8 -> 0)
				continue;
			}
			//person[ining][tajaNum] => 이닝별 타자가 치는 타수
			if (person[ining][tajaNum] == 0) {//아웃!!!
				outCnt++;
			} else if (person[ining][tajaNum] == 1) {//1루타!
				if (ru[2] != -1) { //3루에 사람 있으면?
					visited[ru[2]] = false;//3루 사람 빼기
					iningScore++;
				}
				ru[2] = ru[1];//옆으로 한칸씩
				ru[1] = ru[0];
				ru[0] = tajaNum;//1루에 안타 친 얘 넣자
				visited[tajaNum] = true; //안타친 얘 나가기 처리
			} else if (person[ining][tajaNum] == 2) {//2루타!
				if (ru[2] != -1) {//3루 사람 있음 
					visited[ru[2]] = false;
					iningScore++;
				}
				if (ru[1] != -1) {
					visited[ru[1]] = false;
					iningScore++;
				}
				ru[2] = ru[0]; //1루수 3루로 가기
				ru[1] = tajaNum;//2루수는 친 사람
				ru[0] = -1;//1루수는 없음
				visited[tajaNum] = true;//친 사람 처리
			} else if (person[ining][tajaNum] == 3) {//3루타!
				for (int i = 0; i < 3; i++) {
					if (ru[i] != -1) {//루에 사람 있음
						visited[ru[i]] = false;//사람 나기기
						ru[i] = -1;//루 비우기
						iningScore++;//점수 업
					}
				}
				ru[2] = tajaNum;//3루에 타자
				visited[tajaNum] = true;//타나 자가기 처리
			} else if (person[ining][tajaNum] == 4) {//홈러언!!
				iningScore++;//일단 친사람 점수 올리기
				for (int i = 0; i < 3; i++) {
					if (ru[i] != -1) {//루수에 사람 있으면?
						visited[ru[i]] = false;//사람 나가기 처리
						ru[i] = -1;
						iningScore++;//스코어 늘리기
					}
				}
			}
			
			idx = (idx + 1) % 9;//쳤으니 다음 선수 탐색!!
		}
		return iningScore;//이닝 스코어 리턴
	}
	
	static void solve() {
		int score = 0;//전체 스코어 초기화
		idx = 0; //순서 가리키는 인덱스 초기화
		for (int i = 0; i < n; i++) {//n이닝 반복
			visited = new boolean[9];//타자 나가있는지 확인하는 방문배열 초기화
			ru = new int[3]; //루에 있는 사람 초기화
			score += doIning(i);//이닝별 점수 얻기(매개변수는 이닝)
		}
		sol = Math.max(score, sol);
	}
	
	static void permu(int cnt) {//경우의 수 구하기
		if (cnt == 9) {
			solve();
			return;
		}
		
		if (cnt == 3) { //4번 타자는 고정
			com[cnt] = 0;
			permu(cnt + 1);
		} else {
			for (int i = 0; i < 9; i++) {//0번(1) 선수 제외(4번타자(idx = 3)으로 쓸거임
				if (i == 0) continue;
				if (!cVisited[i]) {
					cVisited[i] = true;
					com[cnt] = i;
					permu(cnt + 1);
					cVisited[i] = false;
				}
			}			
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		person = new int[n][9];//사람들 이닝마다 칠 수 있는 거
		com = new int[9]; //순열 구하는 배열(타자 순서)

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				person[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//4번(idx3) 타자는 항상 1
		cVisited = new boolean[9];
		permu(0);
		System.out.println(sol);
	}
	
}
