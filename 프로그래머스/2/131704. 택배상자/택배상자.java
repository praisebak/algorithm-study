
import java.util.*; 

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        int cur = 0;
        
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i=1;i<=order.length;i++){
            if(i == order[cur]){
                answer++;
                cur++;
            }else{
                stack.push(i);    
            }
            
            
            
            while(!stack.isEmpty() && order.length >= cur){
                if(stack.peek() == order[cur]){
                    cur++;
                    stack.pop();
                    answer++;
                }
                else{
                    break;
                }
            }
        }
        
        return answer;
    }
}