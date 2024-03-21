import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        if(n == 1) {
            System.out.println(0);
            return;
        }

        boolean[] primes = new boolean[n+1];

        for(int i = 2; i < n+1; i++) {
            if(primes[i]) continue;

            for(int j = i * 2; j < n+1; j += i) {
                primes[j] = true;
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i = 2; i < n+1; i++) {
            if(!primes[i]) arr.add(i);
        }

        int cnt = 0;
        int l = 0;
        int r = 0;
        int sum = arr.get(l);
        while(l <= r) {
            if(sum == n) {
                cnt++;
                sum -= arr.get(l++);
                if(++r >= arr.size()) break;
                sum += arr.get(r);
            }
            else if(sum < n) {
                if(++r >= arr.size()) break;
                sum += arr.get(r);
            }
            else {
                sum -= arr.get(l++);
            }
        }

        System.out.println(cnt);
    }
}
