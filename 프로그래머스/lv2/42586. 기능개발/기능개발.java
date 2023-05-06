import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length];
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int day = 1, speed_cnt = 0, answer_cnt = 0, pro_cnt = 0;
        
        for(int i = 0; i < progresses.length; i++)  {
            arr.add(progresses[i]);
        }
        while(!arr.isEmpty()) {
            
            if(arr.get(0) + speeds[speed_cnt] * day >= 100) {
                //System.out.println("cnt is " + pro_cnt + " day i " + day);
                arr.remove(0);
                pro_cnt++;
                speed_cnt++;
            }
            else if(pro_cnt > 0 ){
                answer[answer_cnt++] = pro_cnt;
                //System.out.println("pro_cnt is " + pro_cnt);
                pro_cnt = 0;
                day++;
            }
            else day++;
            
        }
        answer[answer_cnt++] = pro_cnt;
        
        int[] answer2 = new int[answer_cnt];
        for(int i = 0; i < answer_cnt; i++) {
            answer2[i] = answer[i];
        }
        return answer2;
    }
}