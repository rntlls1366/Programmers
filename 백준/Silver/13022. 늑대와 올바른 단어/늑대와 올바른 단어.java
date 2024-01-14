import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        char[] table = {'w', 'o', 'l', 'f'};
        int[] cnt = new int[4];
        int idx = 0;
        int result = 1;

        for(int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);
            if(table[idx] == now) {
                cnt[idx]++;
            }
            else {
                idx++;
                if(idx == 4) {
                    for(int j = 0; j < 3; j++) {
                        if(cnt[j] != cnt[j+1]) {
                            result = 0;
                            break;
                        }
                    }
                    idx = 0;
                }
                if(table[idx] == now) {
                    cnt[idx]++;
                }
                else {
                    result = 0;
                    break;
                }
            }
        }

        for(int j = 0; j < 3; j++) {
            if(cnt[j] != cnt[j+1]) {
                result = 0;
            }
        }

        System.out.println(result);
    }
}
