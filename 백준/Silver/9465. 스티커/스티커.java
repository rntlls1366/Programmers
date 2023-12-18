import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] stickers = new int[2][n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                stickers[0][j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                stickers[1][j] = Integer.parseInt(st.nextToken());
            }
            sol(stickers);
        }
    }

    static void sol(int[][] stickers) {
        int k = stickers[0].length;

        int[][] dp =  new int[2][k];

        dp[0][0] = stickers[0][0];
        dp[1][0] = stickers[1][0];
        if(k > 1) {
            dp[0][1] = dp[1][0] + stickers[0][1];
            dp[1][1] = dp[0][0] + stickers[1][1];
        }

        for(int i = 2; i < k; i++) {
            dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + stickers[0][i];
            dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + stickers[1][i];
        }

        System.out.println(Math.max(dp[0][k-1], dp[1][k-1]));

    }
}