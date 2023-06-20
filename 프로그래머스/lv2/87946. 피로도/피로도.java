import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer;
        int[] check = new int[dungeons.length];
        
        answer = go(k, dungeons, check, 0);
        return answer;
    }
    
    public int go(int k, int[][] dungeons, int[] check, int cnt) {
        
        int possible = 0;
        for(int i = 0; i < dungeons.length; i++) {
            if(check[i] == 0 && dungeons[i][0] <= k) possible++;
        }
        if(possible == 0) return cnt;
        
        int[] check_copy = check.clone();
        int[] result = new int[dungeons.length];
        int result_idx = 0;
        for(int i = 0; i < dungeons.length; i++) {
            if(check_copy[i] == 0 && dungeons[i][0] <= k) {
                check_copy[i] = 1;
                result[result_idx++] = go(k-dungeons[i][1], dungeons, check_copy, cnt+1);
                check_copy[i] = 0;
            }
        }
        
        int max = 0;
        for(int num : result) {
            if(max < num) {
                max = num;
            }
        }
        
        return max;
    }
    
}