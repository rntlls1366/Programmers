import java.util.Stack;

class Solution {
    public int solution(int[] ingredient) {
        Stack<Integer> burger = new Stack<Integer>();
        int cnt = 0;
        
        for(int i : ingredient) {
            burger.push(i);
            
            if(burger.size() >= 4) {
                if(burger.get(burger.size()-4) == 1 && burger.get(burger.size()-3) == 2 && burger.get(burger.size()-2) == 3 && burger.get(burger.size()-1) == 1) {
                    cnt++;
                    for(int j = 0; j < 4; j++) burger.pop();
                }
            }
        }
        
        return cnt;
    }
}