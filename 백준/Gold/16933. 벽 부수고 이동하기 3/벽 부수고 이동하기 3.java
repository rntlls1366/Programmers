import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] stage;
    static boolean[][][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stage = new int[N][M];
        visited = new boolean[N][M][K+1][2];        //x, y, 벽파괴, 낮밤

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                stage[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        Queue<Next> q = new ArrayDeque<>();

        if(stage[0][0] == 1) {
            q.add(new Next(0, 0, 1, K - 1));
            visited[0][0][K - 1][0] = true;
        }
        else {
            q.add(new Next(0, 0, 1, K));
            visited[0][0][K][0] = true;
        }

        while(!q.isEmpty()) {
            Next now = q.poll();

            if(now.x == N - 1 && now.y == M - 1) {
                System.out.println(now.count);
                return;
            }

            for(int i = 0; i < 4; i++) {
                int x = now.x + dx[i];
                int y = now.y + dy[i];
                int day = now.count % 2;
                if(getPossibility(x, y)) {
                    if(stage[x][y] == 0) {
                        if(!visited[x][y][now.punch][day]) {
                            q.add(new Next(x, y, now.count + 1, now.punch));
                            visited[x][y][now.punch][day] = true;
                        }
                    }
                    else {
                        if(!visited[x][y][now.punch][day] && day == 1 && now.punch > 0) {
                            q.add(new Next(x, y, now.count + 1, now.punch - 1));
                            visited[x][y][now.punch][day] = true;
                        }
                        else if(!visited[x][y][now.punch][day] && day == 0 && now.punch > 0) {
                            q.add(new Next(now.x, now.y, now.count + 1, now.punch));
                            visited[x][y][now.punch][day] = true;
                        }
                    }
                }
            }
        }

        System.out.println(-1);


    }

    static boolean getPossibility(int x, int y) {
        if(x < 0 || y < 0) return false;
        if(x >= N || y >= M) return false;
        return true;
    }
}

class Next {
    int x;
    int y;
    int count;
    int punch;

    public Next(int x, int y, int count, int punch) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.punch = punch;
    }
}