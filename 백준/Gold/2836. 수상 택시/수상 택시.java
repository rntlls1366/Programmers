import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        ArrayList<AtoB> arr = new ArrayList<>();

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a > b) {
                arr.add(new AtoB(b, a));
            }
        }

        if(arr.isEmpty()) {
            System.out.println(M);
            return;
        }

        Collections.sort(arr);

        int before_a = arr.get(0).a;
        int before_b = arr.get(0).b;
        for(int i = 1; i < arr.size(); i++) {
            int now_a = arr.get(i).a;
            int now_b = arr.get(i).b;

            if(before_a == now_a) {
                continue;
            }

            if(before_b >= now_a) {
                before_b = Math.max(before_b, now_b);
            }
            else {
                M += (before_b - before_a) * 2L;
                before_a = now_a;
                before_b = now_b;
            }
        }

        M += (before_b - before_a) * 2L;

        System.out.println(M);
    }
}

class AtoB implements Comparable<AtoB> {
    int a;
    int b;

    public AtoB(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(AtoB o) {
        if(this.a < o.a) return -1;
        else if(this.a > o.a) return 1;
        else {
            return Integer.compare(o.b, this.b);
        }
    }
}