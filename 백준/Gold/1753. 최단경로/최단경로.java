import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int V, E, K;
        int[][] board;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

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

        }

        //여기까지 데이터 input

        Solution.dijkstra(map, K, V);

    }
}

class Solution {

    static void dijkstra(HashMap<Integer, ArrayList<Point>> map, int start, int size) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[] costs = new int[size];
        Arrays.fill(costs, 999999);

        pq.add(new Point(start-1, 0));

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

        costs[start-1] = 0;

        for(int num : costs) {
            if(num == 999999) System.out.println("INF");
            else System.out.println(num);
        }
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