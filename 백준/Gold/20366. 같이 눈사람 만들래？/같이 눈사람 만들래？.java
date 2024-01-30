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
        ArrayList<Integer> arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Snow> snow = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i != j) {
                    snow.add(new Snow(arr.get(i) + arr.get(j), i, j));
                }
            }
        }

        Collections.sort(snow);

        int result = Integer.MAX_VALUE;

        Loop1 :
        for(int i = 1; i < snow.size(); i++) {
            Snow now = snow.get(i);
            Snow before = snow.get(i-1);
            int cnt = i - 1;
            while(true) {
                if(before.index_a == now.index_a || before.index_a == now.index_b || before.index_b == now.index_a || before.index_b == now.index_b) {
                    cnt--;
                    if(cnt < 0) continue Loop1;
                    before = snow.get(i - cnt);
                    continue;
                }
                break;
            }


            int n = (int) Math.abs(before.length - now.length);
            result = Math.min(result, n);
        }
        System.out.println(result);
    }
}

class Snow implements Comparable<Snow>{
    long length;
    int index_a;
    int index_b;

    public Snow(long length, int index_a, int index_b) {
        this.length = length;
        this.index_a = index_a;
        this.index_b = index_b;
    }

    @Override
    public int compareTo(Snow o) {
        return Long.compare(this.length, o.length);
    }
}
