import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] up_dp = new int[N][M];
        int[][] down_dp = new int[N][M];
        int[][] stage = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                stage[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        up_dp[N-1][0] = stage[N-1][0];      //좌측 하단부터 우측 상단까지 올라가는 dp
        for(int i = N - 2; i >= 0; i--) {
            up_dp[i][0] = stage[i][0] + up_dp[i+1][0];      //맨 왼쪽 줄 계산
        }

        for(int i = 1; i < M; i++) {
            up_dp[N-1][i] = stage[N-1][i] + up_dp[N-1][i-1];      //맨 아랫 줄 계산
        }

        for(int i = N - 2; i >= 0; i--) {
            for(int j = 1; j < M; j++) {
                up_dp[i][j] = up_dp[i+1][j] > up_dp[i][j-1] ? up_dp[i+1][j] + stage[i][j] : up_dp[i][j-1] + stage[i][j];
            }
        }

        down_dp[N-1][M-1] = stage[N-1][M-1];    //우측 하단부터 좌측 상단까지 올라가는 dp
        for(int i = N - 2; i >= 0; i--) {
            down_dp[i][M-1] = stage[i][M-1] + down_dp[i+1][M-1];      //맨 오른쪽 줄 계산
        }

        for(int i = M - 2; i >= 0; i--) {
            down_dp[N-1][i] = stage[N-1][i] + down_dp[N-1][i+1];      //맨 아랫 줄 계산
        }

        for(int i = N - 2; i >= 0; i--) {
            for(int j = M - 2; j >= 0; j--) {
                down_dp[i][j] = down_dp[i+1][j] > down_dp[i][j+1] ? down_dp[i+1][j] + stage[i][j] : down_dp[i][j+1] + stage[i][j];
            }
        }

        long result = Long.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                long sum = up_dp[i][j] + down_dp[i][j];
                result = Math.max(result, sum);
            }
        }

        System.out.println(result);


    }

}
