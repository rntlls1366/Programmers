import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++) {
            int F = Integer.parseInt(br.readLine());
            int[] arr = new int[2*F];
            for(int j = 0; j < 2*F; j++) {
                arr[j] = j;
            }
            int[] cnt = new int[2*F];
            Arrays.fill(cnt, 1);
            HashMap<String, Integer> nameMap = new HashMap<>();
            for(int j = 0; j < F; j++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if(!nameMap.containsKey(a)) nameMap.put(a, nameMap.size());
                if(!nameMap.containsKey(b)) nameMap.put(b, nameMap.size());
                System.out.println(union(arr, cnt, nameMap.get(a), nameMap.get(b)));
            }
        }

    }

    static int union(int[] arr,int[] cnt, int a, int b) {
        int a_parent = find(arr, a);
        int b_parent = find(arr, b);
        if(a_parent < b_parent) {
            arr[b_parent] = a_parent;
            cnt[a_parent] += cnt[b_parent];
            return cnt[a_parent];
        }
        else if(a_parent > b_parent){
            arr[a_parent] = b_parent;
            cnt[b_parent] += cnt[a_parent];
            return cnt[b_parent];
        }
        else  {
            return cnt[a_parent];
        }

    }
    static int find(int[] arr, int a) {
        if(a == arr[a]) return a;
        return arr[a] = find(arr, arr[a]);
    }
}
