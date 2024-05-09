
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

    static int n, m, arr[][], connected[][], point, sol;
    static int dy[] = {1, -1, 0, 0};
    static int dx[] = {0, 0, 1, -1};

    static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && x < m && y < n;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        connected = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && connected[i][j] == 0) {
                    point++;
                    bfs(i, j);
                }
            }
        }
        System.out.println(point);
        System.out.println(sol);

    }

    static void bfs(int sy, int sx) {
        int sum = 1;
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(sy, sx));
        connected[sy][sx] = point;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (isRange(ny, nx) && arr[ny][nx] == 1 && connected[ny][nx] == 0) {
                    connected[ny][nx] = point;
                    queue.offer(new Pos(ny, nx));
                    sum++;
                }

            }

        }

        sol = Math.max(sol, sum);
    }

}

