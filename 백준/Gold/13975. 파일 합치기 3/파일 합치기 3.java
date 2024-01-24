import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            getSum(pq);
        }
    }

    static void getSum(PriorityQueue<Long> pq) {
        long result = 0;
        while(pq.size() > 1) {
            long a = pq.poll();
            long b = pq.poll();
            long sum = a + b;
            result += sum;
            pq.add(sum);
        }
        System.out.println(result);
    }
}
