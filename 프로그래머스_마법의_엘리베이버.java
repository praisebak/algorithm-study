import java.util.*;
class Solution {
    
    class Node{
        int num;
        int count;
        public Node(int num,int count){
            this.num = num;
            this.count = count;
        }
    }
    
    public int solution(int storey) {
        int answer = 0;
        
        int maxNum = 100000000;
        
    
        

        while(storey != 0){
            //0에 가까운지 10에가까운지 판단해서 가까운쪽으로 이동
            int curDigit = storey % 10 ;
            storey /= 10;
            
            if(curDigit == 5 && storey % 10 >= 5){
                storey++;    
            }else{
                if(curDigit > 5) storey++;
                
            }
            answer += Math.min(Math.abs(curDigit),Math.abs(10-curDigit));
            
        }
   
            
        
        return answer;
    }
}
