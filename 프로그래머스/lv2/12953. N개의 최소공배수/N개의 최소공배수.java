import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        
        Arrays.sort(arr);
        int max = arr[arr.length - 1];
        int cnt = 0;
        while(true) {
            
            for(int i = 0; i < arr.length - 1; i++) {
                //System.out.println("max is " + max + " now is " + arr[i]);
                if(max % arr[i] != 0) break;
                else cnt++;
            }
            
            if(cnt == arr.length - 1) break;
            cnt = 0;
            max += arr[arr.length - 1];
            
        }
        return max;
    }
}