import java.util.*;


class Solution {
    public int solution(int k, int[] tangerine) {
        int[] intArr = new int[10000005];
        for(int i : tangerine) intArr[i]++;
        
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i : intArr) if(i != 0) arr.add(i);
        
        Collections.sort(arr, Collections.reverseOrder());
        
        int sum = 0, cnt = 0;
        for(int i : arr) {
            sum += i;
            cnt++;
            if(sum >= k) break;
        }
        return cnt;
    }
}