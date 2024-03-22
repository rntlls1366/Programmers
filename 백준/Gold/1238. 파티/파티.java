import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X;
    static final int INF = 2000000;
    static int[] costs_fromX;
    static ArrayList<ArrayList<Road>> adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for(int i = 0; i < N + 1; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj.get(s).add(new Road(e, cost));
        }

        int max = 0;

        costs_fromX = deikstra(X);

        for(int i = 1; i < N + 1; i++) {

            int[] costs = deikstra(i);

            max = Math.max(max, costs[X] + costs_fromX[i]);

        }

        System.out.println(max);

    }

    static int[] deikstra(int i) {

        int[] costs = new int[N+1];
        Arrays.fill(costs, INF);

        PriorityQueue<Road> pq = new PriorityQueue<>();

        pq.add(new Road(i, 0));

        while(!pq.isEmpty()) {
            Road now = pq.poll();
            if(costs[now.detination] <= now.cost) continue;

            costs[now.detination] = now.cost;

            for(Road r : adj.get(now.detination)) {
                if(costs[r.detination] > costs[now.detination] + r.cost) {
                    pq.add(new Road(r.detination, costs[now.detination] + r.cost));
                }
            }
        }

        return costs;
    }
}

class Road implements Comparable<Road> {
    int detination;
    int cost;

    public Road(int detination, int cost) {
        this.detination = detination;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road o) {
        return Integer.compare(this.cost, o.cost);
    }
}