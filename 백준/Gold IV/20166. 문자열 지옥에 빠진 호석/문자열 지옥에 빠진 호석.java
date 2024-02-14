import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static char[][] stage;
    static String str;
    static int limit;

    static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};

    static HashMap<String, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stage = new char[N][M];
        for(int i = 0; i < N; i++) {
            str = br.readLine();
            for(int j = 0; j < M; j++) {
                stage[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < K; i++) {
            str = br.readLine();
            limit = str.length();

            if(hm.containsKey(str)) {
                System.out.println(hm.get(str));
                continue;
            }

            Queue<Next> q = new LinkedList<>();

            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    if(getPossibility(j, k, 0)) q.add(new Next(j, k, 1));
                }
            }

            int result = 0;

            while(!q.isEmpty()) {
                Next now = q.poll();

                for(int j = 0; j < 8; j++) {
                    int x = now.x + dx[j];
                    int y = now.y + dy[j];
                    int[] xy = correct(x, y);
                    x = xy[0];
                    y = xy[1];

                    if(getPossibility(x, y, now.level)) {
                        if(now.level + 1 == limit) {
                            result++;
                            continue;
                        }
                        q.add(new Next(x, y, now.level + 1));
                    }
                }

            }

            hm.put(str, result);
            System.out.println(result);
        }
    }

    static boolean getPossibility(int x, int y, int level) {
        if(level >= limit) return false;
        return stage[x][y] == str.charAt(level);
    }

    static int[] correct(int x, int y) {
        int[] result = new int[2];

        if(x < 0) x = N - 1;
        if(x >= N) x = 0;
        if(y < 0) y = M - 1;
        if(y >= M) y = 0;

        result[0] = x;
        result[1] = y;
        return result;
    }
}

class Next {
    int x;
    int y;
    int level;

    public Next(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }
}
