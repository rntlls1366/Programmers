class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        int x = 0, y;
        //double y_2;
        while(true) {
            //y_2 = (double)Math.sqrt((long)d*d - (long)x*x);
            y = (int)Math.floor(Math.sqrt((long)d*d - (long)x*x));
            //System.out.println("x is " + x + " / y is " + y);
            answer += y/k + 1;
            x += k;
            if(x > d) break;
        }
        return answer;
    }
}