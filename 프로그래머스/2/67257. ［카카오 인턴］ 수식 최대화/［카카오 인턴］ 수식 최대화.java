import java.util.*;

class Solution {
    List<String> values = new ArrayList<>();
    char[] opers = new char[]{'+','-','*'};
    int[] opersIdx = new int[3];
    boolean[] visit = new boolean[3];
    long answer = 0;

    public long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<expression.length();i++){
            if(!Character.isDigit(expression.charAt(i))){
                values.add(sb.toString());
                sb = new StringBuilder();
                values.add(expression.charAt(i) + "");
                continue;
            }
            sb.append(expression.charAt(i) +"");
        }
        values.add(sb.toString());

        dfs(0);
        
        return answer;
    }
    
    public long cal(String left,String oper,String right){
        
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        if(oper.equals("*")){
            return l * r;
        }else if(oper.equals("-")){
            return l - r;
        }else{
            return l + r;
        }
    }

    boolean test = false;
    public void dfs(int len){
        if(len == 3 && !test){
            List<String> values = new ArrayList<>(this.values);
            for(int i=0;i<3;i++){
                String cur = opers[opersIdx[i]] + "";
                int size = values.size();
                for(int j=0;j<size;j++) { 
                    //크기가 3개 -> 1개로 줄어야함
                    if(values.get(j).equals(cur)) {
                        long result = cal(values.remove(j-1),values.remove(j-1),values.remove(j-1));
                        
                        values.add(j-1,result+"");
                        j-=2;
                        size -=2;
                            
                    }
                }
            }
            
            answer = Math.max(answer,Math.abs(Long.parseLong(values.get(0))));
            
            return;
        }
        for(int i=0;i<3;i++){
            if(visit[i]) continue;
            visit[i] = true;
            opersIdx[len] = i;
            dfs(len+1);
            visit[i] = false;
        }
    }
}