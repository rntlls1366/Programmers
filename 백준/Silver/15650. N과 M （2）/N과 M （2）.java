import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M];


        dfs(arr, 1, 0);


    }

    static void dfs(int[] arr, int n, int cnt) {
        if(cnt >= M) {
            for(int num : arr) System.out.print(num + " ");
            System.out.println("");
            return;
        }
        if(n > N) return;
        arr[cnt] = n;
        dfs(arr, n + 1, cnt + 1);
        arr[cnt] = 0;
        dfs(arr, n + 1, cnt);
    }
}