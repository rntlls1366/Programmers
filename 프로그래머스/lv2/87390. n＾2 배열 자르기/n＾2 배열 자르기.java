import java.util.Arrays;

class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int)(right-left) + 1;
        int[] answer = new int[size];
        int level = 1;
        int idx = 0;
        for(long i = left; i <= right; i++) {
            int num = (int)(i % (long)n) + 1;
            level = (int)(i/(long)n) + 1;
            answer[idx++] = num < level ? level : num;
        }
        
        return answer;
    }
}