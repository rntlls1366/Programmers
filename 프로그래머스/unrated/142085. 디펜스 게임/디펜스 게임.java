import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0, cnt = 0;
        
        for(int num : enemy) {
            pq.add(num);
            answer++;
            sum += num;
            if(sum > n && k > 0) {
                sum -= pq.poll();
                k--;
            }
            else if(sum > n) { answer--; break; }
        }
        return answer;
    }
}