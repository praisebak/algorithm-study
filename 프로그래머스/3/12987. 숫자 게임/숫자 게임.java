import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int left = 0;
        int right = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        while(right < A.length && left < A.length){
            int curLeft = A[left++];
            
            while(right < A.length && B[right] <= curLeft){
                right++;
            }
            
            if(right < A.length){
                right++;
                answer++;
            }
        }
        
        return answer;
    }
}