import java.util.ArrayList;

class Solution {
    public int[] solution(String s) {
        ArrayList<Integer> tube = new ArrayList<Integer>();
        
        for(int i = 0; i < s.length(); i++) {
            tube.add(Character.getNumericValue(s.charAt(i)));
        }
        
        int cnt = 1;
        int zero_cnt = 0;
        String temp;
        while(true) {
            for(int i = 0; i < tube.size(); i++) {
                if(tube.get(i) == 0) {
                    tube.remove(i);
                    zero_cnt++;
                    i--;
                }
            }
            if(tube.size() <= 1) break;
            temp = Integer.toString(tube.size(), 2);
            tube.clear();
            for(int i = 0; i < temp.length(); i++) {
                tube.add(Character.getNumericValue(temp.charAt(i)));
            }
            cnt++;
        }
        
        int[] answer = {cnt, zero_cnt};
        return answer;

    }
}