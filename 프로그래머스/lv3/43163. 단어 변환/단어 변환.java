class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int[] check = new int[words.length];
        answer = dfs(begin, target, words, check, 0);
        if(answer == 51) answer = 0;
        return answer;
    }
    
    /**DFS*/
    public int dfs(String now, String target, String[] words, int[] check, int cnt) {
        if(now.equals(target)) return cnt;

        int min = 51;
        for(int i = 0; i < words.length; i++) {
            if(check[i] == 0 && (compare(now, words[i]))) {
                int[] check_copy = check.clone();
                check_copy[i] = 1;
                int result = dfs(words[i], target, words, check_copy, cnt + 1);
                if(min > result) min = result;
            }
        }

        return min;
    }
    
    /**두 문자열이 다른 부분이 딱 한개인지? */
    public boolean compare(String a, String b) {
        int cnt = 0;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i))cnt++;
        }
        return cnt == 1 ? true : false;
    }
}