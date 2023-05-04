import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Iterator;

class Solution {
    public long solution(int n, int[] works) {
        int sum = 0, num;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++) {
            sum += works[i];
            pq.add(works[i]);
        }
        if(sum <= n) return 0;
        
        for(int i = 0; i < n; i++) {
            num = (int) pq.poll();
            pq.add(num-1);
        }
        
        long result = 0;
        while(!pq.isEmpty()) {
            result += Math.pow(pq.poll(), 2);
        }
        return result;
        

    }
}