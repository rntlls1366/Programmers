import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int now = 0;
        int result = 0;

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(c == '(') now++;
            else if(c == ')' && str.charAt(i -1) == '(') {
                now--;
                result += now;
            }
            else {
                now--;
                result++;
            }
        }

        System.out.println(result);

    }
}
