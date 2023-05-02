class Solution {
    public boolean solution(int x) {

        int ten = 10;
        int cnt = 1;
        int sum = 0;
        int original =  x;

        while(x > ten) {

            ten *= 10;
            cnt++;

        }

        for(int i = 0; i < cnt + 1; i++) {
            sum += x % 10;
            x = (int) x/10;
        }

        return original % sum == 0;
    }
}