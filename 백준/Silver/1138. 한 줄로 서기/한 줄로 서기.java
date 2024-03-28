import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer> result = new ArrayList<>();
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i < N + 1; i++) arr[i] = Integer.parseInt(st.nextToken());

        for(int i = N; i >= 1; i--) {
            result.add(arr[i], i);
        }

        for(int n : result) System.out.print(n + " ");
    }
}
