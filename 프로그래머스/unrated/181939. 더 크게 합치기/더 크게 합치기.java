class Solution {
    public static int solution(int a, int b) {
        int answer = 0;
        int box = 1;
        for(int i = 10000; i > 0; i = i / 10) {
            if(b > i)  {
                box = i;
                break;
            }
        }
        
        int sum1 = a * (box*10) + b ;
        box = 1;
        
        for(int i = 10000; i > 0; i = i / 10) {
            if(a > i)  {
                box = i;
                break;
            }
        }
        
        int sum2 = b * (box*10) + a ;
        return sum1 > sum2 ? sum1 : sum2;
    }
}