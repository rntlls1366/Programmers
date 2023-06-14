import java.util.ArrayList;
import java.util.Iterator;

class Solution {
    public String solution(String number, int k) {

        StringBuilder answer = new StringBuilder();
        answer.append(number);
        
        int start = 0;

        while(k > 0) {
            
            if(start + 1 >= answer.length()) {
                answer.delete(answer.length()-1, answer.length());
                k--;
            }
            else if(Character.getNumericValue(answer.charAt(start)) == 9) {
                start++;
            }
            else {
                if(Character.getNumericValue(answer.charAt(start)) < (Character.getNumericValue(answer.charAt(start+1)))) {
                    answer.delete(start, start+1);
                    k--;
                    start = (start > 0) ? start - 1 : start;
                }
                else start++;
            }
        }
        
        String result = answer.toString();

        return result;
    }
}