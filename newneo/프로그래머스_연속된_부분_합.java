import java.util.*;
class Solution {
    
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        int startIdx = 0;
        int endIdx = 0;
        int cur = 0;
        int length = Integer.MAX_VALUE;
        List<Integer> resultList = new ArrayList<>();
        for(int i=0;i<sequence.length;i++){
            cur += sequence[i];
            
            while(cur > k){
                cur -= sequence[startIdx];
                startIdx++;
            }

            if(cur == k){
                
                if(length > endIdx - startIdx){
                    length = endIdx - startIdx;
                    answer = new int[]{startIdx,endIdx};
                }
                cur -= sequence[startIdx];
                startIdx++; 
                
            }
            endIdx++;
        }
        
        
       
        return answer;
    }
}
