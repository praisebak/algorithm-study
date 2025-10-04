import java.util.*;
class Solution {
    String[] userIds;
    String[] bannedIds;
    boolean[] visit;
    
    public int solution(String[] user_id, String[] banned_id) {
        visit = new boolean[user_id.length];
        this.userIds = user_id;
        this.bannedIds = banned_id;
        
        dfs(0,0,"");
        
        return set.size();
    }
    
    int answer = 0;
    
    Set<String> set = new HashSet<>();
    
    public void dfs(int len,int idx,String s){
        
        if(len == bannedIds.length){
            String[] splited = s.split(" ");
            Arrays.sort(splited);
            StringBuilder sb = new StringBuilder();
            for(String tmp : splited){
                sb.append(tmp + " ");
            }
            set.add(sb.toString());
            return;
        }
        
        for(int i=0;i<userIds.length;i++){
            String userId = userIds[i];
            String bannedId = bannedIds[len];
            if(visit[i]) continue;
            if(bannedId.length() != userId.length()) continue;
            boolean isValid = true;
            
            //일치하는지 확인하기
            for(int j=0;j<bannedId.length();j++){
                char ch = bannedId.charAt(j); 
                if(ch == '*') continue;
                if(ch != userId.charAt(j)) {
                    isValid = false;
                    break;
                }
            }
            
            if(isValid){
                visit[i] = true;

                dfs(len+1,i+1, s + " " + i);
                visit[i] = false;
            }
        }
    }
}