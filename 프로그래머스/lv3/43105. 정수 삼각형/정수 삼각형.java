class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] max = triangle.clone();
        for(int i = 1; i < triangle.length; i++) {
            max[i][0] = triangle[i][0] + max[i-1][0];
            max[i][max[i].length - 1] = triangle[i][triangle[i].length - 1] + max[i-1][max[i-1].length - 1];  //양 끝은 선택지가 없이 하나를 계승
            for(int j = 1; j < triangle[i].length - 1; j++) {
                int left = max[i-1][j-1];
                int right = max[i-1][j];
                max[i][j] += left > right ? left : right;       //더해져 내려오는 본인 위 2가지 중 더 큰것을 자신에게 합침
            }
        }

        for(int i = 0; i < max[max.length-1].length; i++) {
            answer = answer < max[max.length-1][i] ? max[max.length-1][i] : answer;     //바닥까지 왔을 때, 바닥 중 제일 큰 값 리턴
        }
        return answer;
    }
}