import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Set<String> set = new HashSet<>();
        List<String> list = new LinkedList<>();

        for(int i=0;i<cities.length;i++){
            String s = cities[i].toLowerCase();
            if(set.contains(s)){
                list.remove(s);
                list.add(s);
                answer++;
                continue;
            }
            
            //미스인데 캐시가 다 찼음
            if(cacheSize <= set.size()){
                if(cacheSize != 0){
                    String first = list.remove(0);
                
                    set.remove(first);
                    set.add(s);
                    list.add(s);    
                }
                
                answer += 5;
            //미스인데 캐시 여유있음
            }else{
                set.add(s);
                list.add(s);
                answer += 5;
            }
        }
        
        return answer;
    }
}