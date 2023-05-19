import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        Stack<Character> st = new Stack<>();
        int answer = 0;
        //1. peek와 유저스킬이 같다면 pop
        //2. 같지않고 st에 포함되지 않는다면 패스
        //3. 같지않고 st에 포함된다면 불가능한 스킬트리
        
        for(int i = skill.length() - 1; i >= 0; i--) {
            st.push(skill.charAt(i));
        }
        for(int i = 0; i < skill_trees.length; i++) {
            answer += check(st, skill_trees[i]);
        }
        return answer;
    }
    
    public int check(Stack<Character> stack, String stree) {
        Stack<Character> st = (Stack<Character>) stack.clone();
        for(int i = 0; i < stree.length() && !st.isEmpty(); i++) {
            if(st.peek() == stree.charAt(i)) st.pop();
            else if(st.contains(stree.charAt(i))) return 0;
        }
        return 1;
    }
}