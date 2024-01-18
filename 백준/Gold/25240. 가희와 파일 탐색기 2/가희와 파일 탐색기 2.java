import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int U = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());

        HashMap<String, HashSet<String>> hm = new HashMap<>();

        for (int i = 0; i < U; i++) {
            st = new StringTokenizer(br.readLine());
            String user = st.nextToken();
            if(!hm.containsKey(user)) {
                hm.put(user, new HashSet<>());
            }
            hm.get(user).add(user);     //자기 자신의 이름인 그룹에 속하고 시작함
            if (st.hasMoreTokens()) {
                String[] groups = st.nextToken().split(",");
                for(String group : groups) {
                    if (!hm.containsKey(group)) {
                        hm.put(group, new HashSet<>());
                    }
                    hm.get(group).add(user);    //그룹 맵에 유저를 넣음
                }


            }
        }

        HashMap<String, File> fileHashMap = new HashMap<>();

        for(int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            String fileName = st.nextToken();
            int chmod = Integer.parseInt(st.nextToken());
            String owner = st.nextToken();
            String group = st.nextToken();
            fileHashMap.put(fileName, new File(chmod, owner, group));
        }

        st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String questioner = st.nextToken();
            String fileName = st.nextToken();
            int query = 0;
            char c = st.nextToken().charAt(0);
            switch (c) {
                case 'R' :
                    query = 4;
                    break;
                case 'W' :
                    query = 2;
                    break;
                case 'X' :
                    query = 1;
            }

            File file = fileHashMap.get(fileName);
            if(file.owner.equals(questioner)) {     //파일 소유자 라면
                int num = file.chmod / 100;
                if((num & query) != 0) {
                    System.out.println(1);
                    continue;
                }
            }
            else if(hm.get(file.group).contains(questioner)) {   //파일 소유 그룹에 질문자가 속해있다면
                int num = file.chmod % 100;
                num = num / 10;
                if((num & query) != 0) {
                    System.out.println(1);
                    continue;
                }
            }
            else {  //소유자도, 그룹도 아니라면
                int num = file.chmod % 10;
                if((num & query) != 0) {
                    System.out.println(1);
                    continue;
                }
            }
            System.out.println(0);
        }

    }
}

class File {
    int chmod;
    String owner;
    String group;

    public File(int chmod, String owner, String group) {
        this.chmod = chmod;
        this.owner = owner;
        this.group = group;
    }
}