class Solution {
    public int solution(int n) {
        int one_cnt = Integer.bitCount(n);
        int box = n;
        while(true) {
            box++;
            if(one_cnt == Integer.bitCount(box)) break;
        }
        return box;
    }
}