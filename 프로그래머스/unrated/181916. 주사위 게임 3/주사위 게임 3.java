class Solution {
    public int solution(int a, int b, int c, int d) {
        int[] dice = new int[7];
        dice[a]++;
        dice[b]++;
        dice[c]++;
        dice[d]++;
        
        for(int i = 1; i < 7; i++) {
            if(dice[i] == 4) return 1111*i;
            else if(dice[i] == 3) {
                for(int j = 1; j < 7; j++) {
                    if(dice[j] == 1) {
                        return (10*i+j) * (10*i+j);
                    }
                }
            }
            else if(dice[i] == 2) {
                int[] lst = new int[2];
                int cnt = 0;
                for(int j = 1; j < 7; j++) {
                    if(dice[j] == 2 && i != j) {
                        
                        return (i + j) * (int) Math.abs(i - j);
                    }
                    if(dice[j] == 1) lst[cnt++] = j;
                }
                return lst[0] * lst[1];
                
            }
        }
        int min = 7;
        for(int i = 1; i < 7; i++) {
            if(min > i && dice[i] == 1) min = i;
        }
        return min;
    }
}