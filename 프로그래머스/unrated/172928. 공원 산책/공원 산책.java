class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[][] stage = new int[park.length][park[0].length()];
        int c, x = 0, y = 0;
        for(int i = 0; i < park.length; i++) {
            for(int j = 0; j < park[0].length(); j++) {
                c = park[i].charAt(j);
                if(c == 'S')  {
                    stage[i][j] = 2;
                    x = j;
                    y = i;
                }
                else if(c == 'X') stage[i][j] = 1;
                else stage[i][j] = 0;
            }
        }
        int rollbackX, rollbackY, go;
        for(int i = 0; i < routes.length; i++) {
            rollbackX = x;
            rollbackY = y;
            go = Character.getNumericValue(routes[i].charAt(2));
            switch(routes[i].charAt(0)) {
                case 'N' :
                    if(y - go < 0) break;
                    for(int k = 0; k < go; k++) {
                        if(stage[y-1][x] == 1) {
                            x = rollbackX;
                            y = rollbackY;
                            break;
                        }
                        y--;
                    }
                    break;
                case 'S' :
                    if(y + go >= stage.length) break;
                    for(int k = 0; k < go; k++) {
                        if(stage[y+1][x] == 1) {
                            x = rollbackX;
                            y = rollbackY;
                            break;
                        }
                        y++;
                    }
                    break;
                case 'W' :
                    if(x - go < 0) break;
                    for(int k = 0; k < go; k++) {
                        if(stage[y][x-1] == 1) {
                            x = rollbackX;
                            y = rollbackY;
                            break;
                        }
                        x--;
                    }
                    break;
                case 'E' :
                    if(x + go >= stage[0].length) break;
                    for(int k = 0; k < go; k++) {
                        if(stage[y][x+1] == 1) {
                            x = rollbackX;
                            y = rollbackY;
                            break;
                        }
                        x++;
                    }
                    break;
            }
            
        }
        
        return new int[] {y, x};
    }
}