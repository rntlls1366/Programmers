import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;
    static ArrayList<ArrayList<Integer>> arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        visited = new boolean[N+1];
        arr = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < N + 1; i++) {
            arr.add(new ArrayList<>());
        }
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        dfs(1);

        for(int i = 2; i <= N; i++) System.out.println(parents[i]);

    }

    static void dfs(int now) {
        visited[now] = true;
        for(int next : arr.get(now)) {
            if(!visited[next]) {
                parents[next] = now;
                dfs(next);
            }

        }
    }


}
