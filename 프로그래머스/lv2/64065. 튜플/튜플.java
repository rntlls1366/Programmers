import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(String s) {
        String[] box = s.split("}");
        for(int i = 0; i < box.length; i++) {
            box[i] = box[i].replace("{","");
        }
        String[][] result = new String[505][505];
        for(String s1 : box) {
            String[] temp = s1.split(",");
            List<String> temp2 = new ArrayList(List.of(temp));
            int idx = 0;
            temp2.remove("");
            for(int i = 0; i < temp2.size(); i++) {
                result[temp2.size()][idx++] = temp2.get(i);
                //System.out.println("length is " + temp2.size() + " num is " + temp2.get(i));
            }
        }
        
        
        int[] answer = new int[box.length];
        int answer_idx = 0;
        ArrayList<String> dup = new ArrayList<>();
        for(int i = 1; i <= box.length; i++) {

            for(int j = 0; j < i; j++) {
                if(!dup.contains(result[i][j])) {
                    dup.add(result[i][j]);
                    answer[answer_idx++] = Integer.parseInt(result[i][j]);
                }
            }
            
        }
        
        return answer;
    }
}