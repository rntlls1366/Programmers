import java.util.Stack;

class Solution {
    boolean solution(String s) {
       
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(1);
            }
            else {
                if(stack.size() == 0) return false;
                else {
                    stack.pop();
                }
            }
        }
        
        if(stack.size() == 0) return true;
        return false;
    }
}