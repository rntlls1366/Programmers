class Solution {
    public final int MAX = Integer.MAX_VALUE - 10;
    public int solution(int x, int y, int n) {
        int[] dp = new int[y+5];

        for(int i = x + 1; i < y + 1; i++) {
            int one = MAX, two = MAX, three = MAX;
            if(i-n >= x) {
                one = dp[i-n] + 1;
            }
            if(i%2 == 0 && i/2 > 0 && i/2 >= x) two = dp[i/2] + 1;
            if(i%3 == 0 && i/3 > 0 && i/3 >= x) three = dp[i/3] + 1;
            //System.out.println("now is " + i + " " +one+ " " +two+ " "+three);
            dp[i] = Math.min(one, two);
            dp[i] = Math.min(dp[i], three);
        }
        if(dp[y] == MAX) return -1;
        
        return dp[y];
    }
}