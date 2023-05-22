import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : A) pq.add(num);

        Arrays.sort(B);
        ArrayList<Integer> arr = new ArrayList<>();
        for(int num : B) arr.add(num);

        int oneA;
        while(true) {
            oneA = pq.poll();
            
            if(oneA > arr.get(arr.size() - 1)) break;
            for(int i = 0; i < arr.size(); i++) {
                if(arr.get(i) > oneA) {
                    arr.remove(i);
                    answer++;
                    break;
                }
            }
            if(pq.size() == 0)break;
        } 

        return answer;
    }
}