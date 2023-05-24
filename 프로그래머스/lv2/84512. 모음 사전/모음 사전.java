class Solution {
    public int solution(String word) {
        int answer = 0;
        int len = word.length();
        int[] lst = {781,156,31,6,1};
        
        answer += len;
        char temp_char;
        int temp_int = 0;
        for(int i = 0; i <len; i++) {
            temp_char = word.charAt(i);
            switch(temp_char) {
                case 'A' :temp_int = 0; break;
                case 'E' :temp_int = 1; break;
                case 'I' :temp_int = 2; break;
                case 'O' :temp_int = 3; break;
                case 'U' :temp_int = 4; break;
            }
            answer += lst[i] * temp_int;
        }
        return answer;
    }
}