import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static char[] arr;
    static HashSet<String> hs = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = st.nextToken().toCharArray();

        for(int i = 0; i < arr.length; i++) {
            dfs(i, i, ""+arr[i], ""+arr[i]);
        }
        System.out.println(hs.size());
    }

    static void dfs(int L, int R, String s, String path) {
        if(L==0 && R == arr.length - 1) {
            hs.add(path);
            return;
        }
        if(L-1 >= 0) {
            dfs(L-1, R, arr[L-1] + s, path + " " + arr[L-1] + s);
        }
        if(R+1 < arr.length) {
            dfs(L, R+1, s + arr[R+1], path + " " + s + arr[R+1]);
        }
    }
}
