import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); 
        for(int num : scoville) {
            pq.add(num);
        }
        int a;
        int b;
        while(pq.peek() < K) {
            if(pq.size() == 1) return -1;
            a = pq.poll();
            b = pq.poll();
            pq.add(a + b * 2);
            answer++;
        }
        return answer;
    }
}