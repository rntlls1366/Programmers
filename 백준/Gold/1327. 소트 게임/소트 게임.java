import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(st.hasMoreTokens()) sb.append(st.nextToken());

        HashSet<String> hs = new HashSet<>();
        hs.add(sb.toString());

        Queue<String> q = new ArrayDeque<>();
        q.add(sb.toString());

        int cnt = 0;

        if(determine(sb.toString())) {
            System.out.println(cnt);
            return;
        }

        while(!q.isEmpty()) {

            int size = q.size();
            cnt++;

            while(size-- > 0) {
                String now = q.poll();

                for(int i = 0; i < N - K + 1; i++) {
                    StringBuilder next = new StringBuilder();
                    if(i > 0) {
                        next.append(now.substring(0, i));
                    }
                    StringBuilder reverse = new StringBuilder(now.substring(i, i + K));
                    next.append(reverse.reverse());
                    next.append(now.substring(i + K));

                    if(determine(next.toString())) {
                        System.out.println(cnt);
                        return;
                    }

                    if(!hs.contains(next.toString())) {
                        hs.add(next.toString());
                        q.add(next.toString());
                    }
                }
            }

        }

        System.out.println(-1);

    }

    static boolean determine(String str) {
        int before = Character.getNumericValue(str.charAt(0));
        for(int i = 1; i < str.length(); i++) {
            int n = Character.getNumericValue(str.charAt(i));
            if(before > n) return false;
            before = n;
        }

        return true;
    }
}
