import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static  ArrayList<Integer> arr;
    static int[] result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);

        dfs(0, 0);

    }

    static void dfs(int s, int cnt) {
        if(cnt == M) {
            for(int num : result) System.out.print(num + " ");
            System.out.println("");
            return;
        }
        if(s > N) return;

        for(int i = s; i < N; i++) {
                result[cnt] = arr.get(i);
                dfs(i, cnt + 1);
        }
    }
}
