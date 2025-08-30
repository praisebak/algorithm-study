import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        

        
        //작은쪽에서 10보다 클때까지 계속 빼는거임
        
        Queue<Integer> left = new LinkedList<>();
        Queue<Integer> right = new LinkedList<>();
        
        long leftSum = 0;
        long rightSum = 0;
        for(int i=0;i<queue1.length;i++){
            left.add(queue1[i]);
            leftSum += queue1[i];
        }
        
        for(int i=0;i<queue2.length;i++){
            right.add(queue2[i]);
            rightSum += queue2[i];
        }
        
        long obj = (leftSum + rightSum) / 2;
        
        int leftPop = 0;
        int rightPop = 0;
        
        int originLeftSize = left.size();
        int originRightSize = right.size();
        
        if(obj == leftSum && obj == rightSum){
            return 0;
        }
        
        while(leftPop != originLeftSize && rightPop != originRightSize){
            boolean moved = false;
            while(!left.isEmpty() && leftSum > rightSum){    
                leftSum -= left.peek();
                leftPop++;
                rightSum += left.peek();
                right.add(left.poll());
                answer++;
                moved = true;
            }   
            
            while(!right.isEmpty() && rightSum > leftSum){
                rightSum -= right.peek();
                rightPop++;
                leftSum += right.peek();

                left.add(right.poll());
                
                answer++;                
                moved = true;
            }
            
            if(rightSum == obj){
                return answer;
            }
            
            if(!moved){
                break;
            }
            
        }
        
        return -1;
    }
}