import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] stage;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        stage = new int[N][M];
        visited = new boolean[N][M][2];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                int n = Character.getNumericValue(str.charAt(j));
                stage[i][j] = n;
            }
        }

        Queue<Next> q = new ArrayDeque<>();
        q.add(new Next(0, 0, 0));
        visited[0][0][0] = true;

        int cnt = 0;
        boolean success = false;

        Loop1 :
        while(!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                Next now = q.poll();

                if(now.x == N-1 && now.y == M-1) {
                    success = true;
                    cnt++;
                    break Loop1;
                }

                for(int i = 0; i < 4; i++) {
                    int x = now.x +  dx[i];
                    int y = now.y + dy[i];

                    if(getPosiibility(x,y)) {
                        if(stage[x][y] == 0 && !visited[x][y][now.punch]) {
                            q.add(new Next(x, y, now.punch));
                            visited[x][y][now.punch] = true;
                        }
                        else if(now.punch == 0) {
                            q.add(new Next(x, y, 1));
                            visited[x][y][1] = true;
                        }
                    }
                }
            }

            cnt++;
        }

        if(success) System.out.println(cnt);
        else System.out.println(-1);

    }

    static boolean getPosiibility(int x, int y) {
        if(x < 0 || x >= N) return false;
        if(y < 0 || y >= M) return false;
        return true;
    }
}

class Next {
    int x;
    int y;
    int punch;

    public Next(int x, int y, int punch) {
        this.x = x;
        this.y = y;
        this.punch = punch;
    }
}
