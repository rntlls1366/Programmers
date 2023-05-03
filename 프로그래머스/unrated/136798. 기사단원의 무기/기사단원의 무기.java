class Solution {
    public int solution(int number, int limit, int power) {
        int iron = 0;
        int check = 0;
        
        
        for(int i = 1; i <= number; i++) {
            check = yaksoo(i);
            if(check > limit) iron += power;
            else iron += check;
            
        }
        
        return iron;
    }
    
    public int yaksoo(int n) {
        int cnt = 0;
        for(int i = 1; i * i <= n; i++) {
            if(i * i == n) cnt++;
            else if(n % i == 0) cnt += 2;
        }
        return cnt;
    }
}