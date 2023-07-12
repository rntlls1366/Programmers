import java.util.Set;
import java.util.HashSet;
class Solution {
    public Set<Integer> arr = new HashSet<>();
    public int solution(int[] elements) {
        int answer = 0;
        int max_idx = elements.length - 1;
        int sum = 0;
        for(int i = 0; i < elements.length; i++) {
            if(!arr.contains(elements[i])) arr.add(elements[i]);
            sum += elements[i];
        }
        arr.add(sum);
        for(int i = 2; i <= elements.length - 1; i++) {
            run(i, elements);
        }
        answer = arr.size();
        return answer;
    }
    
    public void run(int num, int[] elements) {
        //System.out.println("Level "  + num);
        for(int i = 0; i < elements.length; i++) {
            //0번째 원소부터 n번째 원소까지 한번씩 시작점으로 잡음.
            int sum = 0;
            int cnt = 0;
            int idx = i;
            //num번 수행함.
            while(cnt < num) {
                if(idx >= elements.length) idx = 0;
                sum += elements[idx++];
                cnt++;
            }
            //System.out.println("sum is " + sum);
            if(!arr.contains(sum)) arr.add(sum);
            //각 원소를 더한 값이 중복값이 아니라면 arr에 추가함
        }
        return;
    }
}