import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = new int[2000];
        ArrayList<String> arr = new ArrayList<>();

        for(int i = 65; i <= 90; i++) {     //arr에 대문자 A-Z를 삽입
            char c = (char)i;
            arr.add(Character.toString(c));
        }

        int pointer_1 = 0, pointer_2 = 1, cnt = 0;
        String box;
        while(true) {
            if (pointer_2 + 1 >= msg.length()) break;
            pointer_2 = check(pointer_1, pointer_2, msg, arr);
            box = msg.substring(pointer_1, pointer_2);
            System.out.println(pointer_1 + " / " + pointer_2 + " / " + box);
            answer[cnt++] = arr.indexOf(box) + 1;
            if(pointer_2 + 1 < msg.length()) {
                arr.add(msg.substring(pointer_1, pointer_2 + 1));
                System.out.println("add word is  " + msg.substring(pointer_1, pointer_2 + 1));
            }

            pointer_1 = pointer_2;
            pointer_2++;
        }

        if(arr.contains(msg.substring(pointer_1)))  answer[cnt++] = arr.indexOf(msg.substring(pointer_1)) + 1;
        else {
            for(int i = pointer_1; i < msg.length(); i++) {
                answer[cnt++] = arr.indexOf(msg.substring(i, i + 1)) + 1;
            }
        }
        return Arrays.copyOf(answer, cnt);
    }

    public int check(int pointer_1, int pointer_2, String msg, ArrayList<String> arr) {
        if(pointer_2 == msg.length() && arr.contains(msg.substring(pointer_1))) return pointer_2 ;
        else if(pointer_2 == msg.length()) return pointer_2 - 1;
        String box = msg.substring(pointer_1, pointer_2);
        if(arr.contains(box)) return check(pointer_1, pointer_2 + 1, msg, arr);
        return pointer_2 - 1;
    }

}