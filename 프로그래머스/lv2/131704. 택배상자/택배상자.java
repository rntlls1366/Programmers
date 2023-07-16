import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        int now = 1;
        stack.push(0);
        while(idx < order.length) {
            if(order[idx] == now) {
                //System.out.println("정순서 컨테이너 벨트");
                now++;
                idx++;
                answer++;
            }
            else if(stack.peek() == order[idx]) {
                //System.out.println("보조 컨테이너 벨트");
                stack.pop();
                idx++;
                answer++;
            }
            else {
                if(now >= order.length) break;
                //System.out.println("수납");
                stack.push(now);
                now++;
            }
        }
        return answer;
    }
}