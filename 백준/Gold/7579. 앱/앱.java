import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] bytes = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            bytes[i] = Integer.parseInt(st.nextToken());
        }

        int[] costs = new int[N + 1];
        int max_cost = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            max_cost += costs[i];
        }

        int[][] dp = new int[N + 1][max_cost + 1];

        for(int i = 1; i <= N; i++) {
            int curr_byte = bytes[i];
            int curr_cost = costs[i];   //앱 하나의 정보 담음

            for(int j = 0; j <= max_cost; j++) {
                dp[i][j] = dp[i-1][j];
                if(curr_cost > j) continue;     //비용 1씩 늘려가며 해당 비용이 앱에 맞을때까지 스킵
                int remain = dp[i-1][j - curr_cost];     //반복중인 비용에서 현재 앱의 비용을 뺀 비용을 갖고 지금까지 계산한 dp배열 상에 몇 바이트를 처리가능한지 저장
                int result_byte = curr_byte + remain;       //현재 앱을 끔 + 남은 비용으로 현재까지 계산한 최적의 바이트 계싼

                if(result_byte > dp[i-1][j]) dp[i][j] = result_byte;       //위 계산이 여태까지 한 dp보다 이득이면 값을 갈아치움
            }
            //System.out.println(Arrays.toString(dp[i]));
        }

        for(int i = 0; i <= max_cost; i++) {
            if(dp[N][i] >= M) {
                System.out.println(i);
                return;
            }
        }

    }
}