import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int[] citations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < citations.length; i++) {
            pq.add(citations[i]);
        }
        int cnt = 0, top, h = 0;
        while(!pq.isEmpty()) {
            top = pq.poll();
            if(top <= pq.size()) h = top;
            else {
                while(true) {
                    h++;
                    if(h > pq.size()+1) return h-1;
                }
            }
        }
        return 0;
    }
}