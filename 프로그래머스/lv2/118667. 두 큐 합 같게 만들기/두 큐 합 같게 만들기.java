import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int cnt = 0;
        int answer = 0;
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        
        for(int num : queue1) {
            q1.add((long)num);
            cnt++;
        }
        for(int num : queue2) {
            q2.add((long)num);
            cnt++;
        }
        if(cnt%2 == 1) return -1;
        
        long q1_sum = sum(q1);
        long q2_sum = sum(q2);
        int end = q1.size() + q2.size();
        while(q1_sum != q2_sum) {
            if(answer > cnt * 4) return -1;
            if(q1_sum > q2_sum) {
                long num = q1.poll();
                q2.add(num);
                q1_sum -= num;
                q2_sum += num;
            }
            else {
                long num = q2.poll();
                q1.add(num);
                q2_sum -= num;
                q1_sum += num;
            }
            if(cnt > end*2) return -1;
            answer++;
        }
        
        return answer;
    }
    
    public long sum(Queue q) {
        long result = 0;
        for (Iterator<Long> iter = q.iterator(); iter.hasNext(); ) {
            result += iter.next();
        }
        return result;
    }
}