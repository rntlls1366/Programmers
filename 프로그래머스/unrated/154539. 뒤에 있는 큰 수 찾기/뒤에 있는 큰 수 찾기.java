import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> st = new Stack<Integer>();
        st.push(0);     //스택에 인덱스 0 넣고 시작
        
        for(int i = 1; i < numbers.length; i++) {       //인덱스를 돌리며
            while(!st.isEmpty() && numbers[st.peek()] < numbers[i]){    
                //스택이 비어있지않고, 스택의 인덱스에 해당하는 수가 현재 수보다 작으면 answer에 저장하며 팝
                answer[st.pop()] = numbers[i];
            }
            st.push(i);     //현재 인덱스는 방금 막 비교됐으니 빠질 일 없음. push
        }
        
        while(!st.isEmpty()) {
            answer[st.pop()] = -1;      //아직도 스택에 남아있는 인덱스는 뒤큰수가 없는 애니까 answer를 -1로
        }
        return answer;
    }

}