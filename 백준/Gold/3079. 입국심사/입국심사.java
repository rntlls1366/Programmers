import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> times = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            times.add(Integer.parseInt(st.nextToken()));
        }

        //여기까지 데이터 input

        Solution solution = new Solution(times, M);

        solution.run();

    }
}

class Solution {
    ArrayList<Integer> times;
    int M;

    Solution(ArrayList<Integer> times, int M) {
        this.times =  times;
        this.M = M;
    }

    void run() {
        long s = 0;
        long e = (long) Math.pow(10, 18);
        long mid = 0;
        while(s < e) {
            mid = (s + e) / 2;

            if(check(mid)) {
                e = mid;
            }
            else {
                s = mid + 1;
            }
        }
        System.out.println(s);
    }

    boolean check(long time) {
        long sum = 0;

        for(int num : times) {
            sum += time / num;
            if(sum >= M) return true;
        }

        return false;

    }

}