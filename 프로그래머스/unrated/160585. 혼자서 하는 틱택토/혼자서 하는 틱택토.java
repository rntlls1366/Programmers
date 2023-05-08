class Solution {
    public int solution(String[] board) {
        int[][] stage = new int[3][3];
        int cnt_o = 0, cnt_x = 0;
        char c;
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                c = board[i].charAt(j);
                if(c == 'O') {
                    stage[i][j] = 1;
                    cnt_o++;
                }
                else if(c == 'X') {
                    stage[i][j] = 2;
                    cnt_x++;
                }
                else stage[i][j] = 0;
            }
        }
        
        //1단계. o와 x의 차는 0이거나 1이 아닌가?
        if(!(cnt_o - cnt_x >= 0 && cnt_o - cnt_x < 2)) return 0;
        
        //2단계. 둘 다 승리한 상황인가?
        int[] win = winCheck(stage);
        if(win[0] > 0 && win[1] > 0) return 0;
        
        //3단계. o가 승리하였는데 o와 x의 수가 같은가? x가 승리하였는데 o와 x의 수가 다른가? 
        if(win[0] > 0 && cnt_o != cnt_x + 1) return 0;
        if(win[1] > 0 && cnt_o != cnt_x) return 0;
        
        return 1;
    }
    
    public int[] winCheck(int[][] stage) {
        
        int[] win = new int[2];
        
        for(int i = 1; i <= 2; i++) {
            for(int j = 0; j < 3; j++) {
                if(stage[j][0] == i && stage[j][1] == i && stage[j][2] == i) win[i-1]++;
                if(stage[0][j] == i && stage[1][j] == i && stage[2][j] == i) win[i-1]++;
            }
            if(stage[0][0] == i && stage[1][1] == i && stage[2][2] == i) win[i-1]++;
            if(stage[0][2] == i && stage[1][1] == i && stage[2][0] == i) win[i-1]++;
        }
        
        return win;
    }
}