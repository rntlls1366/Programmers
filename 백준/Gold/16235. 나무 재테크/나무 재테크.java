import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] foods;
    static int[][] additional_foods;
    static int[][] trees;
    static HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        foods = new int[N+1][N+1];
        trees = new int[N+1][N+1];
        additional_foods = new int[N+1][N+1];

        int idx = 0;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                additional_foods[i][j] = Integer.parseInt(st.nextToken());
                foods[i][j] = 5;
                hm.put(idx, new ArrayList<>());
                trees[i][j] = idx++;
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            hm.get(trees[x][y]).add(year);
        }

        for(int i = 0; i < K; i++) {
            //System.out.println(i + "년차 --------------");
            do_SpringAndSummer();
            do_fall();
            do_winter();
        }

        System.out.println(get_trees());

    }

    static void do_SpringAndSummer() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                ArrayList<Integer> next = new ArrayList<>();

                ArrayList<Integer> now = hm.get(trees[i][j]);
                now.sort(Collections.reverseOrder());
                while(!now.isEmpty()) {
                    int now_tree = now.get(now.size() - 1);
                    //System.out.println(i + " / " + j + " 에서 " + now_tree + "년산 나무가 성장함");
                    if(foods[i][j] >= now_tree) {
                        foods[i][j] -= now_tree;
                        next.add(now_tree + 1);
                        now.remove(now.size() - 1);
                    }
                    else break;
                }

                hm.put(trees[i][j], next);

                for(int dead : now) {
                    //System.out.println(i + " / " + j + " 에서 " + dead + "년산 나무가 뒤짐");
                    foods[i][j] += (dead / 2);
                }
            }
        }
    }

    static void do_fall() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                ArrayList<Integer> now = hm.get(trees[i][j]);

                for(int n : now) {
                    if(n % 5 == 0) {
                        //System.out.println(i + " / " + j + " 에서 나무가 증식함");
                        for(int k = 0; k < 8; k++) {
                            if(getPossibility(i + dx[k], j + dy[k])) {
                                hm.get(trees[i + dx[k]][j + dy[k]]).add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    static void do_winter() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                foods[i][j] += additional_foods[i][j];
            }
        }
    }

    static int get_trees() {
        int sum = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                sum += hm.get(trees[i][j]).size();
            }
        }

        return sum;
    }

    static boolean getPossibility(int x, int y) {
        if(x <= 0) return false;
        if(x > N) return false;
        if(y <= 0) return false;
        if(y > N) return false;
        return true;
    }
}
