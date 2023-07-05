class Solution {
    public int[] solution(int n) {

        int[][] tower = new int[n][n];

        int direction = 0;  //0은 아래, 1은 우측, 2는 위
        int x_point = 0, y_point = 0;
        int num = 1;
        int max = n*(n+1)/2;
        while(true) {
            if(num > max) break;
            //--아래로 가기
            if(direction == 0) {
                tower[x_point++][y_point] = num++;
                if(x_point == n) {
                    x_point--;
                    y_point++;
                    direction = 1;
                }
                else if(tower[x_point][y_point] != 0) {
                    x_point--;
                    y_point++;
                    direction = 1;
                }
            }
            //--우측으로 가기
            else if(direction == 1) {
                tower[x_point][y_point++] = num++;
                if(y_point == n) {
                    y_point -= 2;
                    x_point--;
                    direction = 2;
                }
                else if(tower[x_point][y_point] != 0) {
                    y_point -= 2;
                    x_point--;
                    direction = 2;
                }
            }
            //--위로 가기
            else if(direction == 2) {
                tower[x_point--][y_point--] = num++;
                if(tower[x_point][y_point] != 0) {
                    x_point += 2;
                    y_point++;
                    direction = 0;
                }
            }
            //System.out.println(x_point + " / " + y_point);
        }
        int[] answer = new int[max];
        int answer_idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i + 1; j++) {
                answer[answer_idx++] = tower[i][j];
            }
        }
        return answer;
    }
}