import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        Map<Character,Integer> idxMap = new HashMap<>();

        for(int i=0;i<skill.length();i++){
            idxMap.put(skill.charAt(i),idxMap.getOrDefault(skill.charAt(i),i));
        }
        
        for(int i=0;i<skill_trees.length;i++){
            String s = skill_trees[i];
            
            for(int j=0;j<26;j++){
                char tmp = (char)(j + 65);
                if(idxMap.containsKey(tmp)) continue;
                
                s = s.replace(tmp +"","");
            }
            skill_trees[i] = s;
        }
        
        for(int i=0;i<skill_trees.length;i++){
            String s = skill_trees[i];
            int idx = 0;
            
            if(s.length() == 0) answer++;
            else{
                for(int j=0;j<s.length();j++){
                    char ch = s.charAt(j);
                    if(ch != skill.charAt(j)){
                        break;
                    }
                    
                    if(j == s.length()-1){
                        answer++;
                    }
                }
            }
        }
        
        return answer;
    }
}