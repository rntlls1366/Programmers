import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] chu;
    static boolean[][] dp;
    static int MAX = 400001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        chu = new int[N+1];
        dp = new boolean[N+1][MAX];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
        }
        dp(0, 0);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            int curr_ball = Integer.parseInt(st.nextToken());
            if(dp[N][curr_ball]) sb.append("Y ");
            else sb.append("N ");
        }

        System.out.println(sb);

    }

    static void dp(int idx, int weight) {
        if(dp[idx][weight]) return;
        dp[idx][weight] = true;
        if(idx>=N) return;

        dp(idx+1, weight);
        dp(idx+1, weight + chu[idx+1]);
        dp(idx+1, Math.abs(weight - chu[idx+1]));
    }
}