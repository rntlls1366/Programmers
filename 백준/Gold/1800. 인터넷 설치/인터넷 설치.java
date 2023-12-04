import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, P, K;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Point>> arr = new ArrayList<>();
        for(int i = 0; i <= N; i++) arr.add(new ArrayList<>());

        int max = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr.get(s).add(new Point(cost, e));
            arr.get(e).add(new Point(cost, s));
            max = Math.max(max, cost);
        }
        //여기까지 데이터 input

        Solution solution = new Solution(arr, N, K, max);

        solution.run();

        br.close();


    }
}

class Solution {

    ArrayList<ArrayList<Point>> arr;
    int N;
    int K;
    int max;

    public Solution(ArrayList<ArrayList<Point>> arr, int n, int k, int max) {
        this.arr = arr;
        N = n;
        K = k;
        this.max = max;
    }

    void run() {
        int s = 0;
        int e = max;
        int answer = -1;
        while(s <= e) {
            int mid = (s + e) / 2;
            if(dijkstra(mid)) {
                answer = mid;
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        }

        System.out.println(answer);
    }

    boolean dijkstra(int mid) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 1));
        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[1] = 0;

        while(!pq.isEmpty()) {
            Point now = pq.poll();
            for(Point next : arr.get(now.next)) {
                int nextCost = now.cost;
                if(next.cost > mid) {
                    nextCost++;
                }
                if(nextCost < costs[next.next]) {
                    costs[next.next] = nextCost;
                    pq.add(new Point(nextCost, next.next));
                }
            }
        }

        return costs[N] <= K;

    }

}

class Point implements Comparable<Point> {
    int cost;
    int next;

    public Point(int cost, int next) {
        this.cost = cost;
        this.next = next;
    }

    @Override
    public int compareTo(Point o) {
        if(this.cost > o.cost) return 1;
        else if (this.cost < o.cost) return -1;
        return 0;
    }
}