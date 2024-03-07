import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, d, c;
    static final int INF = 10000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            int[] distances = new int[n+1];
            Arrays.fill(distances, INF);
            distances[c] = 0;

            ArrayList<ArrayList<Computer>> adj = new ArrayList<ArrayList<Computer>>();
            for(int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
            }

            for(int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int speed = Integer.parseInt(st.nextToken());

                adj.get(b).add(new Computer(a, speed));
            }

            PriorityQueue<Computer> pq = new PriorityQueue<>();
            pq.add(new Computer(c, 0));

            while(!pq.isEmpty()) {
                Computer now = pq.poll();

                for(Computer computer : adj.get(now.next)) {
                    if(distances[computer.next] > distances[now.next] + computer.second) {
                        distances[computer.next] = distances[now.next] + computer.second;
                        pq.add(computer);
                    }
                }
            }

            int cnt = 0, max = 0;

            for(int i = 1; i <= n; i++) {
                if(distances[i] < INF) {
                    cnt++;
                    max = Math.max(max, distances[i]);
                }
            }

            System.out.println(cnt + " " + max);
        }
    }
}

class Computer implements Comparable<Computer> {
    int next;
    int second;

    public Computer(int next, int second) {
        this.next = next;
        this.second = second;
    }

    @Override
    public int compareTo(Computer o) {
        return Integer.compare(second, o.second);
    }
}