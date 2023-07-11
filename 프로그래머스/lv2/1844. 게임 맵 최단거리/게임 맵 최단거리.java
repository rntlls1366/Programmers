import java.util.Queue;
import java.util.LinkedList;

class Next {
    int x;
    int y;
    int cnt;
    
    Next(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

class Solution {
    Queue<Next> q = new LinkedList<>();
    int[][] check;
    public int solution(int[][] maps) {
        int answer = 0;
        check = new int[maps.length][maps[0].length];
        
        canWhere(maps, 0 ,0, 1);

        while(q.size() > 0) {
            Next next = q.poll();
            //System.out.println("next is " + next.x + " / " + next.y + " / cnt " + next.cnt);
            if(next.x == maps.length-1 && next.y == maps[0].length-1) return next.cnt;
            
            canWhere(maps, next.x, next.y, next.cnt);
        }
        
        return -1;
    }
    
    public void canWhere(int[][] maps, int x, int y, int cnt) {

        if(x - 1 >= 0) {
            if(maps[x-1][y] == 1 && check[x-1][y] == 0) {
                check[x - 1][y] = 1;
                q.add(new Next(x-1, y, cnt+1));
            }
        }
        if(x + 1 < maps.length) {
            if(maps[x+1][y] == 1 && check[x+1][y] == 0) {
                check[x+1][y] = 1;
                q.add(new Next(x+1, y, cnt+1));
            }
        }
        if(y - 1 >= 0) {
            if(maps[x][y-1] == 1 && check[x][y-1] == 0) {
                check[x][y-1] = 1;
                q.add(new Next(x, y-1, cnt+1));
            }
        }
        if(y + 1 < maps[0].length) {
            if(maps[x][y+1] == 1 && check[x][y+1] == 0) {
                check[x][y+1] = 1;
                q.add(new Next(x, y+1, cnt+1));
            }
        }
    }
}