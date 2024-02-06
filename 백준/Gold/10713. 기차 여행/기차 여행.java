import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nextCity = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            nextCity[i] = Integer.parseInt(st.nextToken());
        }

        int[] tickets = new int[N];
        int[] cards = new int[N];
        int[] chips = new int[N];

        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            tickets[i] = Integer.parseInt(st.nextToken());
            cards[i] = Integer.parseInt(st.nextToken());
            chips[i] = Integer.parseInt(st.nextToken());
        }

        int[] start = new int[N + 1];
        int[] end = new int[N + 1];

        for(int i = 0; i < M - 1; i++) {
            int min = Math.min(nextCity[i], nextCity[i + 1]);
            int max = Math.max(nextCity[i], nextCity[i + 1]);
            start[min]++;
            end[max]++;
        }

        int cnt = 0;
        long result = 0;
        for(int i = 1; i < N; i++) {
            cnt += start[i];
            cnt -= end[i];
            if(tickets[i] * cnt > (cards[i] * cnt) + chips[i]) {
                result += (long) cards[i] * cnt + chips[i];
            }
            else {
                result += (long) tickets[i] * cnt;
            }
        }

        System.out.println(result);


    }
}
