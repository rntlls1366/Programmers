import java.util.ArrayList;

class Solution {
    public boolean solution(String[] phone_book) {

        int cnt = phone_book.length;
        int[] life = new int[cnt];
        ArrayList<String> arr = new ArrayList<String>();
        for(String st : phone_book) {
            arr.add(st);
        }
        if(go(arr, 0) == 1) return false;



        return true;
    }

    public int go(ArrayList<String> arr, int n) {
        for(int i = 0; i < arr.size(); i++) {

            if(arr.get(i).length() <= n){
                //System.out.println("cnt is " + n + " "+ arr.get(0) + " and " + arr.get(1));
                return 1;
            }
        }
        ArrayList<String> arr2 = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < arr.size(); j++) {
                if(Character.getNumericValue(arr.get(j).charAt(n)) == i) {
                    arr2.add(arr.get(j));
                }
            }
            if(arr2.size() > 1) {
                if(go(arr2, n+1) == 1) return 1;
            }
            arr2.clear();
        }

        return 0;
    }
}