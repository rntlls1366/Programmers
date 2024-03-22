import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        ArrayList<Line> arr = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(arr);

        int idx = 0;

        Line[] dp = new Line[n];
        int[] backTracking = new int[n];
        dp[0] = arr.get(0);

        for(int i = 1; i < n; i++) {
            Line now  = arr.get(i);

            if(dp[idx].e < now.e) {
                idx++;
                dp[idx] = now;
                backTracking[i] = idx;
            }
            else {
                int point = bs(dp, idx, now.e);
                backTracking[i] = point;
                dp[point] = now;
            }
        }

        System.out.println(n - (idx + 1));

        HashSet<Line> hs = new HashSet<>();

        //idx--;
        for(int i = n - 1; i >= 0; i--) {
            if(backTracking[i] == idx) {
                hs.add(arr.get(i));
                idx--;
            }
        }

        for(Line now : arr) {
            if(!hs.contains(now)) System.out.println(now.s);
        }
    }

    static int bs(Line[] dp, int idx, int target) {
        int s = 0;
        int e = idx;
        int mid = (s + e) / 2;

        while(s < e) {
            mid = (s + e) / 2;
            if(dp[mid].e >= target) {
                e = mid;
            }
            else {
                s = mid + 1;
                mid = mid + 1;
            }
        }

        return mid;
    }
}

class Line implements Comparable<Line> {
    int s;
    int e;

    public Line(int s, int e) {
        this.s = s;
        this.e = e;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(this.s, o.s);
    }
}