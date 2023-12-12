import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<Wood> woods = new ArrayList<>();
        parent = new int[N + 1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            woods.add(new Wood(i + 1, x1, x2, y));
            parent[i + 1] = i + 1;
        }

        int[][] Questions = new int[Q][2];

        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Questions[i][0] = a;
            Questions[i][1] = b;
        }

        woods.sort(new Wood.ComparatorByX1());

        PriorityQueue<Wood> pq = new PriorityQueue<>(new Wood.ComparatorByX2());
        pq.add(woods.get(0));
        for(int i = 1; i < woods.size(); i++) {
            Wood now = woods.get(i);

            if(pq.isEmpty()) {    //pq가 비었으면 나무 하나 넣고 끝
                pq.add(now);
                continue;
            }
            while(pq.peek().x2 < now.x1) {  //now나무에 닿지 않는 나무 전부 제거
                pq.poll();
                if(pq.isEmpty()) break;
            }

            for (Wood wood : pq) {
                //System.out.println("Union " + wood.number + " and " + now.number);
                union(wood.number, now.number);
            }
            pq.add(now);
        }

        for(int i = 0; i < Q; i++) {
            if(checkParent(Questions[i][0], Questions[i][1])) System.out.println(1);
            else System.out.println(0);
        }


    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            if(x < y) parent[y] = x;
            else parent[x] = y;
        }
    }

    static boolean checkParent(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

}

class Wood {
    int number;
    int x1;
    int x2;
    int y;

    public Wood(int number, int x1, int x2, int y) {
        this.number = number;
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    // x1에 대해 오름차순으로 정렬하는 Comparator
    public static class ComparatorByX1 implements Comparator<Wood> {
        @Override
        public int compare(Wood wood1, Wood wood2) {
            return Integer.compare(wood1.x1, wood2.x1);
        }
    }

    // x2에 대해 오름차순으로 정렬하는 Comparator
    public static class ComparatorByX2 implements Comparator<Wood> {
        @Override
        public int compare(Wood wood1, Wood wood2) {
            return Integer.compare(wood1.x2, wood2.x2);
        }
    }

}