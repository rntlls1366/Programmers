import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int MAX = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] houses = new int[N][3];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            houses[i][0] = Integer.parseInt(st.nextToken());
            houses[i][1] = Integer.parseInt(st.nextToken());
            houses[i][2] = Integer.parseInt(st.nextToken());
        }

        int min = MAX;

        for(int s = 0; s < 3; s++) {
            for(int e = 0; e <3; e++) {     //시작점과 끝을 정해두는 경우의 수 돌리기
                if(s == e) continue;

                int[][] dp = new int[N][3];
                dp[0][s] = houses[0][s];    //시작점만 미리 넣어둠. 이후 0이면 채택하지 않을 것

                for(int i = 1; i < N - 1; i++) {
                    for(int j = 0; j < 3; j++) {
                        int min_before = MAX;
                        for(int k = 0; k < 3; k++) {
                            if(k == j || dp[i-1][k] == 0) continue;     //자신과 같은 색이거나, 0인 녀석은 채택할 수 없는 경우임.
                            min_before = Math.min( dp[i-1][k], min_before );
                        }
                        if(min_before == MAX) continue;
                        dp[i][j] = min_before + houses[i][j];
                    }
                    //System.out.println(Arrays.toString(dp[i]));
                }

                int min_last = MAX;
                for(int l = 0; l < 3; l++) {        //마지막 열의 연산은 따로, e와 다르면서 0이 아닌 최소값과 합치기
                    if(l == e || dp[N-2][l] == 0) continue;
                    int sum = houses[N-1][e] + dp[N-2][l];
                    min_last = Math.min(min_last, sum);
                }

                min = Math.min(min, min_last);
            }
        }
        System.out.println(min);

    }
}