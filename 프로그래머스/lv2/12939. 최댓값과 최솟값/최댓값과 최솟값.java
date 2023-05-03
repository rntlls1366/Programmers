class Solution {
    public String solution(String s) {
        
        String[] box = s.split(" ");
        int max = Integer.parseInt(box[0]) , min = Integer.parseInt(box[0]);
        int num;
        
        for(int i = 1; i < box.length; i++) {
            num = Integer.parseInt(box[i]);
            if(max < num) max = num;
            if(min > num) min = num;
        }
        
        
        return "" + min + " " + max;
    }
        
}
    