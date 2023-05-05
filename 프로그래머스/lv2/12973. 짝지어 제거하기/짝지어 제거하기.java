import java.util.ArrayList;
import java.util.Collections;

class Solution
{
    public int solution(String s)
    {
        ArrayList<Character> arr = new ArrayList<Character>();
        for(int i = 0; i < s.length(); i++) {
            if(arr.size() == 0) arr.add(s.charAt(i));
            else {
                if(arr.get(arr.size() - 1) == s.charAt(i)) {
                    arr.remove(arr.size() - 1);
                }
                else arr.add(s.charAt(i));
            }
        }
        
        return arr.size() == 0 ? 1 : 0;
        
    }
}