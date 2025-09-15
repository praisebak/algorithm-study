import java.util.*;

class Solution {
    int idx = 0;
    public String solution(String p) {
        String answer = "";
        
        
        boolean isEnd = false;
        
        return doThing(p);
    }
    
    public String doThing(String s){
        if(idx == 1){
            return s;
        }
        if(s.isEmpty()) {
            return "";
        }
        
        //2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
        
        int left = 0;
        int right = 0;
        StringBuilder u = new StringBuilder() ;
        for(int i=0;i<s.length();i++){            
            if(s.charAt(i) == '('){
                left++;
            }
            if(s.charAt(i) == ')'){
                right++;
            }
            if(left != 0 && right != 0 && left == right){
                u.append(s.substring(0,i+1));
                break;
            }
        }
        
        String v = s.substring(u.length(),s.length());

        if(isCollect(u.toString())){
            u.append(doThing(v));
            return u.toString();
        }
        
        StringBuilder sb = new StringBuilder("(");

        sb.append(doThing(v));
        sb.append(")");
        
        u.deleteCharAt(0);
        u.deleteCharAt(u.length()-1);
        

        
        StringBuilder uSB = new StringBuilder();
        for(int i=0;i<u.length();i++){
            if(u.charAt(i) == '('){
                uSB.append(")");
            }else{
                uSB.append("(");
            }
        }
                        
        sb.append(uSB.toString());

        return sb.toString();
    }
    
    public boolean isCollect(String s){
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
            
            if(s.charAt(i) == '('){
                stack.push(i);
            }
        }
        return stack.isEmpty();
        
    }
}