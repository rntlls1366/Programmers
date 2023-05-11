import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        ArrayList<Mission> mArr = new ArrayList<Mission>();
        for(int i = 0; i < plans.length; i++) {
            mArr.add(new Mission(plans[i][0],
                    Integer.parseInt(plans[i][1].substring(0,2)),
                    Integer.parseInt(plans[i][1].substring(3)),
                    Integer.parseInt(plans[i][2])));
        }

        mArr.sort(new MissionComparator());     //mArr을 hour과 minute 기준으로 오름차순 정렬

        Stack<Mission> st = new Stack<Mission>();
        Mission temp;
        int cnt = 0;
        int time[] = {mArr.get(0).hour, mArr.get(0).minute};
        int time_compare_1, time_compare_2;
        //temp = mArr.remove(0);

        while(mArr.size() > 0) {
            time_compare_1 = time[0]*100 + time[1];
            time_compare_2 = mArr.get(0).hour*100 + mArr.get(0).minute;
            System.out.println("main : now " + time_compare_1 + " next " + time_compare_2);
            if (time_compare_1 < time_compare_2) {      //1. 만약 현재시각과 다음 시작시각 사이에 여유가 있다면
                System.out.println("1번루트실행");
                if(st.size() > 0) {     //1-1.스택에 쌓인 과제를 수행한다.
                    temp = st.pop();
                    time = temp.run(time[0], time[1], mArr.get(0));
                    if(time[0] == -1) {     //만약 수행이 완료되지 않았다면 다시 스택에 넣는다.
                        st.push(temp);
                        time[0] = mArr.get(0).hour;
                        time[1] = mArr.get(0).minute;
                    }
                    else answer[cnt++] = temp.name;     //수행이 완료되었다면 answer에 넣는다.
                }
                else {      //1-2. 여유가 있는데 스택이 비었다면 다음 과제를 수행한다.
                    temp = mArr.remove(0);
                    time = temp.run(time[0], time[1], mArr.get(0));
                    if(time[0] == -1) {     //만약 수행이 완료되지 않았다면 스택에 넣는다.
                        st.push(temp);
                        time[0] = mArr.get(0).hour;
                        time[1] = mArr.get(0).minute;
                    }
                    else answer[cnt++] = temp.name;     //수행이 완료되었다면 answer에 넣는다.
                }
            }
            else if(time_compare_1 == time_compare_2) {       //2. 현재시각과 다음 시작시각 사이에 여유가 없다면 다음 과제를 수행한다.
                System.out.println("2번루트 실행");
                temp = mArr.remove(0);
                if(mArr.size() == 0) {
                    answer[cnt++] = temp.name;
                    break;
                }
                time = temp.run(time[0], time[1], mArr.get(0));
                if(time[0] == -1) {     //만약 수행이 완료되지 않았다면 스택에 넣는다.
                    st.push(temp);
                    time[0] = mArr.get(0).hour;
                    time[1] = mArr.get(0).minute;
                }
                else answer[cnt++] = temp.name;     //수행이 완료되었다면 answer에 넣는다.
            }

        }


        while(st.size() > 0) {
            answer[cnt++] = st.pop().name;
        }

        //System.out.println("1번쨰는 " + mArr.get(0).name + " 2번째는 " + mArr.get(1).name + " 3번째는 " + mArr.get(2).name);
        return answer;
    }
}

class Mission {
    String name;
    int hour;
    int minute;
    int time;

    public Mission(String name, int hour, int minute, int time) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.time = time;
    }

    public int[] run(int h_now, int m_now, Mission next) {
        System.out.println("now name is " + this.name + " time is " + h_now + " and " + m_now + " / next is " + next.name);
        int m = m_now + this.time;
        int h = h_now;
        if(m >= 60) {
            h += m / 60;
            m = m % 60;
        }
        System.out.println("예상종료시각 "  + h + " : " + m);
        if(h < next.hour) {
            System.out.println("수행완료");
            this.time = 0;
            return new int[]{h, m};      //해당 객체의 남은 시간을 모두 소모했다는 뜻으로 종료 시간 반환
        }
        else if(h == next.hour && m <= next.minute) {
            System.out.println("수행완료");
            this.time = 0;
            return new int[]{h, m};
        }
        else {          //예상 시각이 다음 객체의 시각을 넘어선다면 거기까지만 time을 수행함
            System.out.println("수행미완료");
            int temp = 0;
            temp += (h - next.hour) * 60;
            temp += m - next.minute;
            this.time = temp;
        }
        return new int[]{-1, -1};       //해당 객체가 완료되지 않았다는 뜻으로 -1 반환
    }
}


class MissionComparator implements Comparator<Mission>{
    @Override
    public int compare(Mission a,Mission b){
        if(a.hour > b.hour) return 1;
        if(a.hour == b.hour && a.minute > b.minute) return 1;
        if(a.hour == b.hour && a.minute < b.minute) return -1;
        if(a.hour < b.hour) return -1;
        return 0;
    }
}
