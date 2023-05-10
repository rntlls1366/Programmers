import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Deque<Integer> dq = new ArrayDeque<Integer>(50005);
        
        for(int i : people) {
            arr.add(i);
        }
        Collections.sort(arr);
        for(int i : arr) dq.add(i);
        
        int max;
        while(!dq.isEmpty()) {
            max =dq.removeLast();
            if(dq.size() > 0 && max + dq.getFirst() <= limit) dq.removeFirst();
            answer++;
        }
        
        return answer;
    }
    
}