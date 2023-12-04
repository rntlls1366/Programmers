import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        //여기까지 데이터 input

        Solution solution = new Solution(arr, M);
        solution.run(max);
    }
}

class Solution {
    int[] arr;
    int M;

    Solution(int[] arr, int M) {
        this.arr = arr;
        this.M = M;
    }

    void run(int max) {
        int s = 0;
        int e = max;
        int mid = 0;
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

    boolean check(int mid) {
        int cnt = 1;
        int max = arr[0];
        int min = arr[0];

        for(int num : arr) {
            if(max < num) max = num;
            if(min > num) min = num;
            if(max - min > mid) {
                cnt++;
                max = num;
                min = num;
            }
        }
        return M >= cnt;
    }


}