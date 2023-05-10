class Solution {
    public int solution(int storey) {
        int answer = 0;
        int temp;
        while(storey > 0) {
            temp = storey % 10;
            storey =  storey / 10;
            if(temp > 5) {
                storey++;
                answer += (10-temp);
            }
            else if(temp == 5 && storey % 10 >= 5) {
                storey++;
                answer += (10-temp);
            }
            else {
                answer += temp;
            }
        }
        return answer;
    }
    

}