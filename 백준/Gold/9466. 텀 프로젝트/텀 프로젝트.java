import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int result;
    static boolean[] visited;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            arr = new int[n+1];

            visited = new boolean[n+1];
            checked = new boolean[n+1];

            result = 0;

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < n + 1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i < n + 1; i++) {
                dfs(i);
            }

            System.out.println(n - result);
        }
    }

    static void dfs(int n) {

        if(visited[n]) return;

        visited[n] = true;

        if(!visited[arr[n]]) dfs(arr[n]);
        else {
            if(!checked[arr[n]]) {
                result++;
                for(int i = arr[n]; i != n; i = arr[i]) result++;
            }
        }

        checked[n] = true;

    }

}
