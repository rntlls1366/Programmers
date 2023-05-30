import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        HashMap<String, Integer> hm = new HashMap<>();
        
        for(int i = 0; i < clothes.length; i++) {
            if(hm.get(clothes[i][1]) != null) {
                hm.put(clothes[i][1], (hm.get(clothes[i][1]) + 1));
            }
            else hm.put(clothes[i][1], 1);
        }
        Collection<Integer> values = hm.values();
        ArrayList<Integer> arr = new ArrayList<>(values);
        answer = 1;
        for(int i = 0; i < arr.size(); i++) {
            answer = answer * (arr.get(i)+1);
        }
        return answer-1;
    }
}