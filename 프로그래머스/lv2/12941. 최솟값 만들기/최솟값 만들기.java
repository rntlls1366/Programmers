import java.util.Arrays;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        B = reverseSort(B);
        
        for(int i = 0; i < A.length; i++) {
            answer += A[i] * B[i];
        }

        return answer;
    }
    
    public int[] reverseSort(int[] B) {
        int temp;
        for(int i = 0; i < B.length / 2; i++) {
            temp = B[B.length - 1 - i];
            B[B.length - 1 - i] = B[i];
            B[i] = temp;
        }
        
        return B;
    }
}