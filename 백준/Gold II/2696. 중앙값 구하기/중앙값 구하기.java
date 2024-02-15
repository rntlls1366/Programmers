import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int i = 0; i < T; i++) {
            int M = Integer.parseInt(br.readLine());

            System.out.println((M / 2) + 1);

            PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> right = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            int mid = Integer.parseInt(st.nextToken());
            System.out.print(mid + " ");
            for(int j = 1; j < M; j++) {
                if(j % 10 == 0) st = new StringTokenizer(br.readLine());
                if(j % 20 == 0) System.out.println("");
                int num = Integer.parseInt(st.nextToken());
                if(num <= mid) left.add(num);
                else right.add(num);

                if(j % 2 == 0) {
                    if(left.size() > right.size()) {
                        right.add(mid);
                        mid = left.poll();
                    }
                    else if(right.size() > left.size()) {
                        left.add(mid);
                        mid = right.poll();
                    }
                    System.out.print(mid + " ");
                }
            }

            System.out.println("");
        }
    }
}
