import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, E;
        int a, b;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        HashMap<Integer, ArrayList<Point>> map = new HashMap<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            if(map.containsKey(start)) {
                map.get(start).add(new Point(end, cost));
            }
            else {
                ArrayList<Point> arr = new ArrayList<>();
                arr.add(new Point(end, cost));
                map.put(start, arr);
            }
            if(map.containsKey(end)) {
                map.get(end).add(new Point(start, cost));
            }
            else {
                ArrayList<Point> arr = new ArrayList<>();
                arr.add(new Point(start, cost));
                map.put(end, arr);
            }
        }
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()) - 1;
        b = Integer.parseInt(st.nextToken()) - 1;

        //여기까지 데이터 input

        int[] fromStart = Solution.dijkstra(map, 0, N);
        int[] fromA = Solution.dijkstra(map, a, N);
        int[] fromB = Solution.dijkstra(map, b, N);

        int planA = fromStart[a] + fromA[b] + fromB[N-1];
        int planB = fromStart[b] + fromB[a] + fromA[N-1];

        int result = Math.min(planA, planB);
        if (result >= 99999999) System.out.println(-1);
        else System.out.println(Math.min(planA, planB));
    }
}

class Solution {

    static int[] dijkstra(HashMap<Integer, ArrayList<Point>> map, int start, int size) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[] costs = new int[size];
        Arrays.fill(costs, 99999999);

        pq.add(new Point(start, 0));

        while(!pq.isEmpty()) {
            Point point = pq.poll();

            if(map.containsKey(point.now)) {
                ArrayList<Point> arr = map.get(point.now);
                for(Point next : arr) {
                    if(costs[next.now] > point.cost + next.cost) {
                        costs[next.now] = point.cost + next.cost;
                        pq.add(new Point(next.now, point.cost + next.cost));
                    }
                }
            }
        }

        costs[start] = 0;

        return costs;
    }

}

class Point implements Comparable<Point>{
    int now;
    int cost;

    Point(int now, int cost) {
        this.now = now;
        this.cost = cost;
    }

    @Override
    public int compareTo(Point o) {
        if(this.cost > o.cost) return 1;
        else if (this.cost < o.cost) return -1;
        return 0;
    }
}