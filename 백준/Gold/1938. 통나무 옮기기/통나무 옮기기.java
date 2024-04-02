import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] stage;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        stage = new int[N][N];
        visited = new boolean[N][N][2];     //중심이되는 x좌표, y좌표, 통나무 : 0이면 통나무가 가로, 1이면 세로

        int[][] start = new int[3][2];
        int start_idx = 0;

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                char c = str.charAt(j);

                switch (c) {
                    case 'B' :
                        start[start_idx][0] = i;
                        start[start_idx++][1] = j;
                        break;
                    case '0' :
                        stage[i][j] = 0;
                        break;
                    case '1' :
                        stage[i][j] = 1;
                        break;
                    case 'E' :
                        stage[i][j] = 3;
                        break;
                }
            }
        }

        Queue<Next> q = new ArrayDeque<>();

        if(start[0][0] == start[1][0]) {
            q.add(new Next(start[1][0], start[1][1], 0));
            visited[start[1][0]][start[1][1]][0] = true;
        }
        else {
            q.add(new Next(start[1][0], start[1][1], 1));
            visited[start[1][0]][start[1][1]][1] = true;
        }

        int result = 0;
        boolean success = false;

        Loop1 :
        while(!q.isEmpty()) {

            int size = q.size();

            while(size-- > 0) {
                Next now = q.poll();

                if(getCorrect(now)) {
                    success = true;
                    //System.out.println("x : " + now.x + "y : " + now.y + " wood : " + now.wood);
                    break Loop1;
                }

                for(int i = 0; i < 4; i++) {
                    Next next = new Next(now.x + dx[i], now.y + dy[i], now.wood);
                    if(getPossibility(next)) {
                        if(isThereWood(next) && !visited[next.x][next.y][next.wood]) {
                            q.add(next);
                            visited[next.x][next.y][next.wood] = true;
                        }
                    }
                }

                if(now.wood == 0) {
                    now.wood = 1;
                    if(getPossibility(now) && !visited[now.x][now.y][now.wood]) {
                        if(canTurn(now)) {
                            q.add(now);
                            visited[now.x][now.y][now.wood] = true;
                        }
                    }
                }
                else if(now.wood == 1) {
                    now.wood = 0;
                    if(getPossibility(now) && !visited[now.x][now.y][now.wood]) {
                        if(canTurn(now)) {
                            q.add(now);
                            visited[now.x][now.y][now.wood] = true;
                        }
                    }
                }
            }

            result++;

        }

        if(success) System.out.println(result);
        else System.out.println(0);
    }

    static boolean canTurn(Next next) {
        int x = next.x - 1;
        int y = next.y - 1;

        for(int i =  0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int x2 = x + i;
                int y2 = y + j;
                if(x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
                    if(stage[x2][y2] == 1) return false;
                }
            }
        }
        return true;
    }

    static boolean getCorrect(Next next) {
        if(next.wood == 0) {
            if(stage[next.x][next.y-1] == 3 && stage[next.x][next.y] == 3 && stage[next.x][next.y+1] == 3) {
                return true;
            }
        }
        if(next.wood == 1) {
            if(stage[next.x-1][next.y] == 3 && stage[next.x][next.y] == 3 && stage[next.x+1][next.y] == 3) {
                return true;
            }
        }
        return false;
    }

    static boolean isThereWood(Next next) {
        if(next.wood == 0) {
            if(stage[next.x][next.y-1] == 1) return false;
            if(stage[next.x][next.y] == 1) return false;
            if(stage[next.x][next.y+1] == 1) return false;
        }
        if(next.wood == 1) {
            if(stage[next.x-1][next.y] == 1) return false;
            if(stage[next.x][next.y] == 1) return false;
            if(stage[next.x+1][next.y] == 1) return false;
        }
        return true;
    }

    static boolean getPossibility(Next next) {
        if(next.wood == 0) {
            if(next.x < 0 || next.x > N - 1) return false;
            if(next.y < 1 || next.y > N - 2) return false;

        }
        if(next.wood == 1) {
            if(next.x < 1 || next.x > N - 2) return false;
            if(next.y < 0 || next.y > N - 1) return false;
        }
        return true;
    }
}

class Next{
    int x;
    int y;
    int wood;

    public Next(int x, int y, int wood) {
        this.x = x;
        this.y = y;
        this.wood = wood;
    }
}