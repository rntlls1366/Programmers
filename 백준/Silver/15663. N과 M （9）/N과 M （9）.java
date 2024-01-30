import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<Integer> arr;
    static int[] result;
    static HashSet<String> hs = new HashSet<>();
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        result = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);

        dfs(0, 0);

    }

    static void dfs(int s, int cnt) {
        if (cnt == M) {
            StringBuilder sb = new StringBuilder();
            for (int num : result) sb.append(num).append(" ");
            if (!hs.contains(sb.toString())) {
                System.out.println(sb);
                hs.add(String.valueOf(sb));
            }
            return;
        }
        if (s > N) return;

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                result[cnt] = arr.get(i);
                visited[i] = true;
                dfs(i + 1, cnt + 1);
                visited[i] = false;
            }

        }
    }
}