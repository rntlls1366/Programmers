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
        int T = Integer.parseInt(st.nextToken());

        ArrayList<Carrot> carrots = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int original = Integer.parseInt(st.nextToken());
            int doping = Integer.parseInt(st.nextToken());
            carrots.add(new Carrot(original, doping));
        }


        carrots.sort(Collections.reverseOrder());

        T--;
        long result = 0;
        for(Carrot carrot : carrots) {
            long now = carrot.original + (long) carrot.doping * T;
            result += now;
            T--;
        }

        System.out.println(result);
    }
}

class Carrot implements Comparable<Carrot>{
    int original;
    int doping;

    public Carrot(int original, int doping) {
        this.original = original;
        this.doping = doping;
    }

    @Override
    public int compareTo(Carrot o) {
        return Integer.compare(this.doping, o.doping);
    }
}