import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Car {
    int start;
    int end;
    
    Car(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (o1, o2) -> {
            return o1[0]-o2[0]; 
        });
        
        Queue<Car> q = new LinkedList<Car>();
        for(int i = 0;i < routes.length; i++) {
            q.add(new Car(routes[i][0], routes[i][1]));
        }
        Car now = q.poll();
        int start = now.start;
        int end = now.end;
        while(q.size() > 0) {
            Car next = q.poll();
            if(next.start > end) {
                answer++;
                start = next.start;
                end = next.end;
            }
            else {
                start = next.start;
                end = end < next.end ? end : next.end;
            }
            
        }
        return answer+1;
    }
}