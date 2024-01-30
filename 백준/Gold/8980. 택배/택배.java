import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int[] route = new int[N+1];
        Arrays.fill(route, C);
        ArrayList<Box> boxes = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            boxes.add(new Box(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(boxes);

        int result = 0;
        for(Box box : boxes) {
            int min = Integer.MAX_VALUE;
            for(int i = box.start; i < box.end; i++) {
                if(route[i] >= box.count) {
                    min = box.count;
                }
                else {
                    min = Math.min(min, route[i]);
                }
            }
            result += min;
            for(int i = box.start; i < box.end; i++) {
                route[i] -= min;
            }
        }

        System.out.println(result);
    }
}

class Box implements Comparable<Box>{
    int start;
    int end;
    int count;

    public Box(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
    }

    @Override
    public int compareTo(Box o) {       //배송 거리 순으로 정렬, 거리가 같다면 박스의 수 순으로 정렬
        int a = this.end - this.start;
        int b = o.end - o.start;
        if(a > b) return 1;
        else if(a < b) return -1;
        else {
            return Integer.compare(this.count, o.count);
        }
    }
}
