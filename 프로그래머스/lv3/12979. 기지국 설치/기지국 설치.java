// 1. 구간을 분할해서 생각하자.
// 2. 이미 기지국의 범위 내인 곳 말고 나머지 부분 범위에 대해 최소값을 리턴하는 함수에 보낸다.
class Solution {
    public int solution(int n, int[] stations, int w) {
        
        int answer = 0;

        int start = 0, end = 0;
        
        for(int now : stations) {
            end = now - w - 1;
            answer += run(start, end, w);
            start = now + w;
        }

        if(start < n) {
            answer += run(start, n, w);
        }
        
        return answer;
    }

    public int run(int start, int end, int w) {
        return (int) Math.ceil((double)(end - start) / (w*2 + 1));
    }
}