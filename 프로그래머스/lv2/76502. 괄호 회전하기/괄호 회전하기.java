import java.util.*;

class Solution {
    public int solution(String s) {
        int x = s.length();

        ArrayList<Character> arr = new ArrayList<Character>();
        for(int i = 0; i < x; i++) arr.add(s.charAt(i));
        
        int cnt = 0;
        char temp;
        for(int i = 0; i < x; i++) {
            cnt += check(arr);
            temp = arr.remove(0);
            arr.add(temp);
        }
        
        return cnt;
    }
    
    public int check(ArrayList<Character> arr) {
        Stack<Character> stack = new Stack<Character>();
        for(char c : arr) {
            switch(c) {
                case ')' :
                    if(stack.size() == 0) return 0;
                    else if(stack.pop() != '(') return 0;
                    break;
                case ']' :
                    if(stack.size() == 0) return 0;
                    else if(stack.pop() != '[') return 0;
                    break;
                case '}' :
                    if(stack.size() == 0) return 0;
                    else if(stack.pop() != '{') return 0;
                    break;
                default :
                    stack.push(c);
            }
        }
        return stack.size() == 0 ? 1 : 0;
    }
}