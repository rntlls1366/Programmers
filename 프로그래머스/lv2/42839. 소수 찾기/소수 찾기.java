import java.util.ArrayList;
class Solution {
    public int cnt = 0;
    ArrayList<Integer> duplicate = new ArrayList<>();
    public int solution(String numbers) {
        ArrayList<Integer> arr = new ArrayList<>();
        
        for(int i = 0; i < numbers.length(); i++) {
            arr.add(Character.getNumericValue(numbers.charAt(i)));
        }
        go(arr, 0);
        return cnt;
    }
    public void go(ArrayList<Integer> arr, int n) {
        if(sosu(n) && !duplicate.contains(n)) {
            duplicate.add(n);
            cnt++;
        }
        if(arr.size() == 0) return;
        
        ArrayList<Integer> arr2;
        int temp;
        for(int i = 0; i < arr.size(); i++) {
            arr2 = (ArrayList<Integer>) arr.clone();
            temp = n * 10 + arr2.remove(i);
            go(arr2, temp);
        }
    }
    public boolean sosu(int n) {
        System.out.println("sosu  " + n);
        if(n <= 1) return false;
        if(n == 2 || n == 3) return true;
        for(int i = 2; i < n; i++) {
            if(n%i == 0) return false;
        }
        return true;
    }
}