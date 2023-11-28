import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<Integer, ArrayList<Point>> map = new HashMap<>();

        for (int i = 0; i < M; i++) {
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

        //여기까지 데이터 input

        Point firstRoute = Solution.firstDijkstra(map, N);

        ArrayList<Integer> delayedTimes = new ArrayList<>();
        for(int i = 1; i < firstRoute.history.size(); i++) {

            int a = firstRoute.history.get(i);

            int time = Solution.dijkstra(map, 0, N, a)[N-1];
            if(time == 99999999) {
                System.out.println(-1);
                return;
            }
            int delayedTime = time - firstRoute.cost;
            delayedTimes.add(delayedTime);
        }

        int result = Collections.max(delayedTimes);
        System.out.println(result);

    }
}

class Solution {

    static Point firstDijkstra(HashMap<Integer, ArrayList<Point>> map, int size) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[] costs = new int[size];
        Point[] points = new Point[size];
        Arrays.fill(costs, 99999999);

        ArrayList<Integer> history = new ArrayList<>();
        history.add(0);
        pq.add(new Point(0, 0, history));

        while(!pq.isEmpty()) {
            Point point = pq.poll();

            if(map.containsKey(point.now)) {
                ArrayList<Point> arr = map.get(point.now);
                for(Point next : arr) {
                    if(costs[next.now] > point.cost + next.cost) {
                        ArrayList<Integer> nextHistory = (ArrayList<Integer>) point.history.clone();
                        nextHistory.add(next.now);
                        costs[next.now] = point.cost + next.cost;
                        Point newPoint = new Point(next.now, point.cost + next.cost, nextHistory);
                        points[next.now] = newPoint;
                        pq.add(newPoint);
                    }
                }
            }
        }

        return points[size-1];
    }

    static int[] dijkstra(HashMap<Integer, ArrayList<Point>> map, int start, int size, int removed) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[] costs = new int[size];
        Arrays.fill(costs, 99999999);

        pq.add(new Point(start, 0));

        while(!pq.isEmpty()) {
            Point point = pq.poll();
            if(point.now == removed) continue;
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
    ArrayList<Integer> history;

    Point(int now, int cost) {
        this.now = now;
        this.cost = cost;
    }

    Point(int now, int cost, ArrayList<Integer> history) {
        this.now = now;
        this.cost = cost;
        this.history = history;
    }

    @Override
    public int compareTo(Point o) {
        if(this.cost > o.cost) return 1;
        else if (this.cost < o.cost) return -1;
        return 0;
    }
}