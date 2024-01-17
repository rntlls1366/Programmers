import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static HashMap<Integer, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int[] Ai = new int[A];
        int[] backTracking = new int[A];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            Ai[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[A];
        int idx = 1;
        dp[0] = Ai[0];
        for (int i = 1; i < A; i++) {
            if (dp[idx - 1] < Ai[i]) {
                dp[idx] = Ai[i];
                backTracking[i] = idx;
                idx++;

            } else {
                int point = binarySearch(dp, idx - 1, Ai[i]);
                backTracking[i] = point;
                dp[point] = Ai[i];
            }

        }

        System.out.println(idx);
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        idx--;
        for(int i = A - 1; i >= 0; i--) {
            if(backTracking[i] == idx) {
                stack.push(Ai[i]);
                idx--;
            }
        }
        while(!stack.isEmpty()) {
            int now = stack.pop();
            sb.append(now).append(" ");
        }

        System.out.println(sb);
    }

    static int binarySearch(int[] dp, int e, int n) {
        int s = 0;
        int mid = (s + e) / 2;
        int answer = mid;
        while (s < e) {
            mid = (s + e) / 2;
            if (n <= dp[mid]) {
                answer = mid;
                e = mid;
            } else {
                s = mid + 1;
                answer = s;
            }
        }

        return answer;
    }
}