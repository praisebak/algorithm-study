import java.util.*;

class Solution {
    public long solution(int[] weights) {
        Arrays.sort(weights);
        long answer = 0;
        Map<Double,Long> map = new HashMap<>();
        
        for(int i=0;i<weights.length;i++){
            double cur = weights[i];
            long curSum = 0;
            
            double n = cur;
            curSum += map.getOrDefault(n,0L);
            n = (cur * 2) / 3;
            curSum += map.getOrDefault(n,0L);
            n = (cur * 2) / 4;
            curSum += map.getOrDefault(n,0L);
            
            n = (cur * 3) / 4;
            curSum += map.getOrDefault(n,0L);


            map.put(cur,map.getOrDefault(cur,0L)+1L);
            
            
            answer += curSum;
        }
        
        return answer;
    }
}