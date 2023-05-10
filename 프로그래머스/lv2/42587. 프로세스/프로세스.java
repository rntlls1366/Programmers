import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0, temp = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 0; i < priorities.length; i++) {        //큐에 원소들을 담으며 location에 해당하는 값은 temp에 담고 큐에는 -1을 넣음
            if(i == location) {
            q.add(-1);
            temp = priorities[i];
            }
            else q.add(priorities[i]);
        }
        int max = maxCheck(q);
        int n;
        //System.out.println("max is " + max + " temp is " + temp);

        while(true) {
            n = q.poll();
            if(n >= max && n >= temp) {
                answer++;
                max = maxCheck(q);
            }
            else if(n == -1 && temp >= max) return answer + 1;
            else q.add(n);
        }
        
        //return answer;
    }
    
    public int maxCheck(Queue<Integer> q) {
        int max = 0;
        Iterator it = q.iterator();
        while(it.hasNext()) {
            int n = (int) it.next();
            if(n > max) max = n;
        }
        return max;
    }
}