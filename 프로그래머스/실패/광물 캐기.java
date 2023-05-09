import java.util.ArrayList;
import java.util.Collections;

class gok {
    int n;
    int type;
    public gok(int n, int type) {
        this.n = n;
        if(type == 0) {
            this.type = 25;
        }
        else if(type == 1) {
            this.type = 5;
        }
        else this.type = 1;
    }
    
    public int run(String k) {
        //System.out.println("type : " + type + " do");
        if(k.equals("diamond")) return 25/type;
        else if(k.equals("iron")) return 5/type < 1 ? 1 : 5/type;
        else return 1;
        
    }
}

class Solution {
    public int solution(int[] picks, String[] minerals) {
        gok[] gg = new gok[3];
        int gok_cnt = 0;
        for(int i = 0; i < 3; i++) {
            gg[i] = new gok(picks[i], i);
            gok_cnt += picks[i];
        }
        
        int len = (minerals.length % 5 == 0) ? 
            (minerals.length / 5) : (minerals.length / 5) + 1;    //광산을 5개씩 나누었을때 몇개 구간이 나오나
        if(len > gok_cnt) len = gok_cnt;    //그 구간의 개수가 곡괭이의 수보다 크다면 곡괭이의 수로 대체
        
        String[][] mine = new String[len][5];
        int cnt = 0;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < 5; j++) {
                if(cnt >= minerals.length) break;
                else mine[i][j] = minerals[cnt++];
            }
        }
        
        int[] mine_num = new int[len];
        int[] dia_check = new int[len];
        int n = 0;
        
        for(int i = 0; i < len; i++) {
            for(String st : mine[i]) {
                if(st == null) break;
                else n += gg[2].run(st);
            }
            mine_num[i] = n;
            n = 0;
        }
        //--돌곡 기준으로 피로도를 mine_num에 넣었음--
        
        int max;
        int index;
        
        for(int i = 0; i < gg[0].n; i++) {
            max = mine_num[0];
            index = 0;
            n = 0;
            for(int j = 1; j < len; j++) {
                if(max < mine_num[j]) {
                    max = mine_num[j];
                    index = j;
                }
            }
            for(String st : mine[index]) {
                if(st == null) break;
                else n += gg[0].run(st);
            }
            dia_check[index] = 1;
            mine_num[index] = mine_num[index] > n ? n : mine_num[index];
        }
        //--다곡 n개를 mine_num 상위n개에 사용했음--
        
        for(int i = 0; i < gg[1].n; i++) {
            max = mine_num[0];
            index = 0;
            n = 0;
            for(int j = 1; j < len; j++) {
                if(max < mine_num[j]) {
                    max = mine_num[j];
                    index = j;
                }
            }
            if(dia_check[index] == 1) continue;
            for(String st : mine[index]) {
                if(st == null) break;
                else n += gg[1].run(st);
            }
            mine_num[index] = mine_num[index] > n ? n : mine_num[index];
        }
        //--철곡 n개를 mine_num 상위n개에 사용했음--
        
        int sum = 0;
        for(int i = 0; i < len; i++) {
            sum += mine_num[i];
        }
       
        
        return sum;
    }
}
