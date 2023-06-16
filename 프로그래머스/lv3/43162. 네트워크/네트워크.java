import java.util.*;

class Solution {
    int[] dup;
    int answer = 0;
    public int solution(int n, int[][] computers) {
        dup = new int[computers.length];
        for(int i = 0; i < n; i++) {
            go(n, computers, i, 0);
        }
        
        return answer;
    }
    public void go(int n, int[][] computers, int now, int layer) {
        if(Collections.frequency(Arrays.asList(dup), 1) == n) return;
        //System.out.println("now point : " + now + " / layer is " + layer);
        
        if(dup[now] == 0 && layer == 0)  {
                //System.out.println("update on " + now);
                answer++;
                dup[now] = 1;
        }
        else if(dup[now] == 0 && layer > 0) dup[now] = 1;
        
        for(int i = 0; i < n; i ++) {
            
            if(computers[now][i] == 1 && dup[i] == 0) {
                
                //System.out.println("go on " + i);
                go(n, computers, i, layer+1);

            }
        }
        
        return;
    }
}