class Solution {
    public String solution(String s) {
        String answer = "";
        String[] st = s.toLowerCase().split("");
        
        st[0] = st[0].substring(0,1).toUpperCase();
        answer = st[0];
        for(int i = 1; i < st.length; i++) {
            answer += st[i-1].equals(" ")  ? st[i].substring(0,1).toUpperCase() : st[i];
        }
        return answer;
    }
}