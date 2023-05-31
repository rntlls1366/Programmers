class Solution {
    public int cnt = 0;
    public int solution(int[] numbers, int target) {
        go(numbers, target, 0, 0);
        return cnt;
    }
    
    public void go(int[] numbers, int target, int start, int sum) {
        if(start == numbers.length) {
            if(sum == target) cnt++;
            return;
        }

        go(numbers, target, start +1, sum + numbers[start]);
        go(numbers, target, start +1, sum - numbers[start]);
        
        return;
    }
}