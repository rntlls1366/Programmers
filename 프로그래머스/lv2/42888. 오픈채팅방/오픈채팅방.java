import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] box;
        HashMap<String, String> user = new HashMap<>();
        String[][] chat = new String[100000][2];        //채팅이 저장될 배열, ex) ["Enter", "uid456"]
        int cnt = 0;
        for(String st : record) {
            box = st.split(" ");
            
            if(!box[0].equals("Leave")) user.put(box[1], box[2]);       //신규 유저면 user에 추가,  uid가 이미 있다면 바뀐 이름 수정됨

            if(box[0].equals("Enter")) {
                chat[cnt][0] = box[0];
                chat[cnt++][1] = box[1];
            }
            else if(box[0].equals("Leave")) {
                chat[cnt][0] = box[0];
                chat[cnt++][1] = box[1];
            }
        }

        String[] answer = new String[cnt];
        for(int i = 0; i < cnt; i++) {
            answer[i] = user.get(chat[i][1]) + "님이 " +
                    (chat[i][0].equals("Enter") ? "들어왔습니다." : "나갔습니다." );
        }
        return answer;
    }
}