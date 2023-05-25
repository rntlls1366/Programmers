import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> pq = new PriorityQueue<>();  //오름차순
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
        //내림차순
        
        char c;
        int n;
        int cnt = 0;
        for(String st : operations) {
            c = st.charAt(0);
            //System.out.println("cnt is " + cnt);
            if(c == 'I') {      //오름차순, 내림차순 pq 둘 다에 삽입함.
                pq.add(Integer.parseInt(st.substring(2)));
                pq2.add(Integer.parseInt(st.substring(2)));
                cnt++;
            }
            else {
                if(st.charAt(2) == '-') {   //오름차순 pq 뻄
                    
                    if(cnt > 0)  {
                        //System.out.println("min poll " + pq.peek());
                        pq.poll(); cnt--;
                    }
                }
                else {      //내림차순 pq 뺌
                    
                    if(cnt > 0) {
                        //System.out.println("max poll " + pq2.peek());
                        pq2.poll(); cnt--;
                    }
                }
            }
            if(cnt == 0) {      //cnt는 현재 진짜 들어있는 개수, 이게 0이면 둘다 초기화
                pq.clear();
                pq2.clear();
            }
        }
        
        if(pq.size() <= 1 || pq2.size() <= 1) return new int[] {0, 0};
        //둘 중 하나라도 남은 수가 1이하 라면 00리턴
        int a = pq.poll();
        int b = pq2.poll();

        if(a > b) return new int[] {0, 0};
        //남은 최소값이 최대값 쪽 보다 크다면 실패, 00리턴

        answer[0] = b;
        answer[1] = a;
        return answer;
    }
}