import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static int[] arr;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];

        solution(0);

        System.out.println(result);

    }

    static void solution(int col) {
        if(col == N) {
            result++;
            return;
        }

        for(int i = 0; i < N; i++) {
            //col번째 행의 i번째 열에 놓았다.
            arr[col] = i;
            if(getPosiibility(col)) {
                solution(col + 1);
            }
        }
    }

    static boolean getPosiibility(int col) {
        for(int i = 0; i < col; i++) {
            //자신보다 앞쪽 행이 자신과 같은 열에 두었다면? 안됨
            if(arr[col] == arr[i]) return false;


            else if(Math.abs(col - i) == Math.abs(arr[col] - arr[i])) return false;
        }

        return true;

    }

}