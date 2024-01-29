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

        int T = Integer.parseInt(st.nextToken());

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            ArrayList<Integer> A = new ArrayList<>();
            ArrayList<Integer> B = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                A.add(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                B.add(Integer.parseInt(st.nextToken()));
            }

            sol(A, B);
        }
    }

    static void sol(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(B);

        long result = 0;
        for(int num : A) {
            int point = binarySearch(B, num);
            result += getNearNum(num, B, point);
        }
        System.out.println(result);
    }

    static int getNearNum(int num, ArrayList<Integer> B, int point) {
        int now = B.get(point);
        if(now == num) return num;

        if(now < num) {
            int before = num - now;
            if(point < B.size() - 2) {
                int after = B.get(point + 1) - num;
                return before <= after ? now : B.get(point + 1);
            }
            return now;
        }
        else {
            int after = now - num;
            if(point > 0) {
                int before = num - B.get(point - 1);
                return before <= after ? B.get(point - 1) : now;
            }
            return now;
        }
    }

    static int binarySearch(ArrayList<Integer> B, int num) {
        int s = 0;
        int e = B.size() - 1;
        int mid = 0;
        //int result = -1;
        while(s <= e) {
            mid = (s + e) / 2;
            int now = B.get(mid);
            if(now == num) return mid;

            if(now < num) {
                //result = s;
                s = mid + 1;
            }
            else {
                //result = e;
                e = mid - 1;
            }
        }

        return mid;
    }
}
