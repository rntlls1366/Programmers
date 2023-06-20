class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        //double gi = (double)h / (double)w;
        for(long i = 1; i < w; i++) {
            answer += (long)((double)h * i / (double)w);
        }
        answer *= 2;
        return answer;
    }

}