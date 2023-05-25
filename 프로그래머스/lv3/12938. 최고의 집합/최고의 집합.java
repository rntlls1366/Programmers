// ex)
// n이 3이고 S가 14면
// 가장 골고루 분배한 것이 답...
// -> 4 5 5
// 어떻게 ?
// S를 n으로 나눈다. 그걸 뿌리고
// S를 n으로 나눈 나머지 만큼을 순차적으로 더해준다.
    
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if(n > s) return new int[] {-1};
        for(int i = 0; i < n; i++) {
            answer[i] = s/n;
        }
        int cnt = n - 1;
        for(int i = 0; i < s%n; i++) {
            answer[cnt--] += 1; 
        }
        return answer;
    }
}