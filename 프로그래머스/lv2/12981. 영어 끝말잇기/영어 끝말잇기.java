import java.util.ArrayList;

class Solution {
    public int[] solution(int n, String[] words) {
        int[][] answer = new int[n][2];
        ArrayList<String> arr = new ArrayList<String>();
        arr.add(words[0]);
        answer[0][1]++;

        for(int i = 0; i < n; i++) {
            answer[i][0] = i + 1;
        }
        for(int i = 1; i < words.length; i++) {
            int who = i % n;
            answer[who][1]++;

            if(firstChar(words[i]) != lastChar(words[i-1])) return answer[who];

            if(arr.contains(words[i])) return answer[who];

            arr.add(words[i]);
        }

        return new int[] {0, 0};
    }

    public char lastChar(String st) {
        return st.charAt(st.length()-1);
    }
    public char firstChar(String st) {
        return st.charAt(0);
    }
}