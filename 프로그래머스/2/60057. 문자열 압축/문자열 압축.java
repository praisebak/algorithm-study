import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            StringBuilder sb = new StringBuilder();
            String prev = s.substring(0, i);
            int count = 1;

            for(int j=i;j<s.length();j+=i){
                String curStr = s.substring(j,Math.min(j+i,s.length()));
                if(curStr.equals(prev)){
                    count++;
                }else{
                    if(count != 1){
                        sb.append(count);    
                    }
                    sb.append(prev);
                    count = 1;
                }

                if(j + i >= s.length()){
                    if(count != 1){
                        sb.append(count);    
                    }
                    sb.append(curStr);
                }
                
                prev = curStr;
            }
            
            
            answer = Math.min(answer,sb.length());
        }
        return answer;
    }
}
