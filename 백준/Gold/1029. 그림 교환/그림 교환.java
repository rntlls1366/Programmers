import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int fullBit;
    static int[][] matrix;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        fullBit = (1 << N) - 1;

        matrix = new int[N][N];
        dp = new int[N][fullBit][10];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < fullBit; j++) Arrays.fill(dp[i][j], -1);
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j = 0; j < N; j++) {
                int n = Character.getNumericValue(s.charAt(j));
                matrix[i][j] = n;
            }
        }

        System.out.println(dfs(0, 1, 0));

    }

    static int dfs(int now, int check, int price) {

        if(check == fullBit) {
            return Integer.bitCount(check);
        }

        if(dp[now][check][price] != -1) return dp[now][check][price];

        dp[now][check][price] = Integer.bitCount(check);


        for(int i = 0; i < N; i++) {
            int next = check | 1 << i;
            if(matrix[now][i] >= price && (check & (1 << i)) == 0) {
                dp[now][check][price] = Math.max(dp[now][check][price], dfs(i, next, matrix[now][i]));
            }
        }


        return dp[now][check][price];
    }

}
