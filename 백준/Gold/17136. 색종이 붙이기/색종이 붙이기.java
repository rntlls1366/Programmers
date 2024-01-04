import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] inven = {0, 5, 5, 5, 5, 5};
    static int[][] paper = new int[10][10];
    static int[][] visited = new int[10][10];
    static int min = 27;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(min == 27 ? -1 : min);

    }

    static void dfs(int x, int y, int cnt) {
        if(x >= 9 && y > 9) {
            min = Math.min(min, cnt);
            return;
        }
        if(cnt >= min) return;
        if(y > 9) {
            dfs(x + 1, 0, cnt);
            return;
        }


        if(paper[x][y] == 1 && visited[x][y] == 0) {
            int size = 5;
            while(size > 0) {
                if(inven[size] > 0 && isAttach(x, y, size)) {
                    int[][] save  = stick(x, y, size);
                    inven[size]--;
                    dfs(x, y + 1, cnt + 1 );
                    visited = save;
                    inven[size]++;
                }
                size--;
            }
        }
        else {
            dfs(x, y + 1, cnt);
        }
    }

    static boolean compare() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(paper[i][j] != visited[i][j]) return false;
            }
        }
        return true;
    }


    static int[][] stick(int x, int y, int size) {
        int[][] before = new int[10][10];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                before[i][j] = visited[i][j];
            }
        }
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                visited[i][j] = 1;
            }
        }

        return before;
    }

    public static boolean isAttach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return false;
                }

                if (paper[i][j] != 1 || visited[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }




}