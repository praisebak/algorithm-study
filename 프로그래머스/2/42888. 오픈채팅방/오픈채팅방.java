import java.util.*;

class Solution {
    public String[] solution(String[] record) {

        Map<String,String> nicknames = new HashMap<>();
        
        for(String s : record){
            String[] sArr = s.split(" ");
            String uId = sArr[1]; 
            if(sArr[0].equals("Enter") || sArr[0].equals("Change")){
                nicknames.put(uId,sArr[2]);    
            }
        }
        
        List<String> answer= new ArrayList<>();
        for(String s : record){
            String[] sArr = s.split(" ");
            String uId = sArr[1]; 
            String n = nicknames.get(sArr[1]);
            if(sArr[0].equals("Change")) continue;
            if(sArr[0].equals("Enter")){
                
                answer.add(n + "님이 들어왔습니다.");
            }else{
                answer.add(n + "님이 나갔습니다.");
            }
            
        }
        //uuid 별로 변경되는 닉네임을 추적한다.
        //추적이 끝나면 들어오고 나간것을 보여준다.
        String[] result = new String[answer.size()];
        int i = 0;
        for(String s : answer){
            result[i++] = s;
        }
        return result;
    }
}