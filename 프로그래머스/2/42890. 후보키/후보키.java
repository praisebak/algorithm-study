import java.util.*;
class Solution {
    int answer;
    int M;
    String[][] relation;
    
    public int solution(String[][] relation) {
        this.M = relation[0].length;
        this.relation = relation;
        
        for(int i=1;i<=M;i++){
            visit = new boolean[M];       
            comb(i-2,new int[M],0,i);    
        }

        return answer;
    }
    
    boolean[] visit;
    List<Set<Integer>> isSelected = new ArrayList<>();
        
    public void comb(int curIdx,int[] tmps,int len,int limit){
        if(len == limit){
            Map<String,Integer> map = new HashMap<>();
            Arrays.sort(tmps);

            Set<Integer> selected = new HashSet<>();
            for(int i=0;i<len;i++){
                selected.add(tmps[i]);
            }
            
            // 최소성 검사
            for (Set<Integer> key : isSelected) {
                if (selected.containsAll(key)) {
                    return;
                }
            }

            Set<String> set = new HashSet<>();
            for (String[] s : relation) {
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<len; i++) {
                    sb.append(s[tmps[i]]).append(",");
                }
                set.add(sb.toString());
            }
            if (set.size() != relation.length) {
                return; // 유일성 실패
            }
            
            answer++;
            
            isSelected.add(selected);
            
            return;
        }
        
        for(int i=curIdx+1;i<M;i++){
            if(visit[i]) continue;
            visit[i] = true; 
            tmps[len] = i; 
            comb(curIdx+1,tmps,len+1,limit);
        }
    }
}