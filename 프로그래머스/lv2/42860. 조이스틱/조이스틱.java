import java.util.*;

class Solution {
    
    
    public int solution(String name) {
        int answer = 0;
        int[] check = new int[name.length()];
        
        int[][] find_a = new int[20][3];    //cnt, start, end
        int idx_a = 0;
        for(int i = 0; i < name.length(); i++) {
            answer += upDown(name.charAt(i));
            if(name.charAt(i) == 'A') { //A인 구간의 시작을 적고, 그 수를 카운트
                find_a[idx_a][0]++;
                if(find_a[idx_a][1] == 0) find_a[idx_a][1] = i;
            }
            else if(find_a[idx_a][0] > 0){  //A인 구간이 끝났다면 끝을 적고, 인덱스++
                find_a[idx_a][2] = i;
                idx_a++;
            }
        }
        
        int min = name.length() - 1;
        int find_cnt = 0;
        while(find_a[find_cnt][0] != 0) {
            System.out.println("now " + find_cnt + " cnt is " + find_a[find_cnt][0]);
            int left = find_a[find_cnt][1];
            int right = name.length() - find_a[find_cnt][2];
            System.out.println("left is " + left + " right is" + right);
            int temp;
            if(left == 0) temp = right+1;
            else if(right == name.length()) temp = left-1;
            else {
                temp = ((left-1)*2 + right) < (right*2 + (left-1))
                    ? ((left-1)*2 + right) : (right*2 + (left-1));
            }
            if(min > temp) {
                min = temp;
            }
            find_cnt++;
        }
        if(find_a[0][0] == name.length()) min = 0;
        System.out.println("min is " + min);
        
        answer += min;
        
        return answer;
    }
    
    
    public int upDown(char c) {
        int num = (int) c;
        if(num > 78) {
            num = 91 - num;
        }
        else {
            num = num - 65;
        }
        return num;
    }
    
    
}