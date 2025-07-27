import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0,0};
        
        //이미 말한 단어면 탈락시키기 위함 
        Set<String> set = new HashSet<>();
        
        //탈락 사람의 번호 몇번째 차례에 탈락했는가
        
        for(int i=0;i<words.length;i++){
            String cur = words[i];
            
            if(set.contains(cur) || (i != 0 && cur.charAt(0) != words[i-1].charAt(words[i-1].length()-1))){
                return new int[]{i%n +1,(i / n)+1};
            }
            
            set.add(cur);
        }
        

        return answer;
    }
}