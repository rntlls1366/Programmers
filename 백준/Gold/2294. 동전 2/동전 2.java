import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Integer> coins = new ArrayList<>();
        int[] dp = new int[k+1];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            coins.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(coins);

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < k+1; j++) {
                int coin = coins.get(i);
                if(j % coin == 0) {
                    int thisCase = j / coin;
                    if(dp[j] == 0 || dp[j] > thisCase) dp[j] = thisCase;
                }
                else {
                    int cnt = 1;
                    while(j > coin * cnt) {
                        int remain = j - coin * cnt;
                        int thisCase = cnt + dp[remain];
                        cnt++;
                        if(dp[remain] == 0) continue;
                        if(dp[j] == 0 || dp[j] > thisCase) dp[j] = thisCase;
                    }

                }
            }
            //System.out.println(Arrays.toString(dp));
        }

        System.out.println((dp[k] == 0 ? -1 : dp[k]));

    }
}