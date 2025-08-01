import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
    
        Map<Integer,Integer> left = new HashMap<>();
        Map<Integer,Integer> right = new HashMap<>();

        left.put(topping[0],1);
        
        int leftCount = 1;
        int rightCount = 0;
        for(int i=1;i<topping.length;i++){
            right.put(topping[i],right.getOrDefault(topping[i],0)+1);
            if(right.get(topping[i]) == 1){
                rightCount++;
            }
        }
        if(leftCount == rightCount){
            answer++;
        }
        
        for(int i=1;i<topping.length;i++){
            int cur = topping[i]; 
            left.put(cur,left.getOrDefault(cur,0)+1);            
            if(left.get(cur) == 1){
                leftCount++;
            }
            right.put(cur,right.get(cur)-1);
            if(right.get(cur) == 0){
                rightCount--;
            }
            if(rightCount == leftCount){
                answer++;
            }

        }
        
        

        return answer;
    }
}