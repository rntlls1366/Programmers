import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        if(n%2 == 1) {
            n--;
            answer++;
        }
        while(n > 0) {
            n /= 2;
            if(n%2 == 1) {
                n--;
                answer++;
            }
        }
        return answer;
    }
}