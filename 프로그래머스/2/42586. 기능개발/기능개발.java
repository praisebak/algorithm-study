import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        //(100 - 30) == 70 / 30 = 3
        //근데 앞에것이 더 크니까 거기에 편승한다
        //앞에거가 작으면 내거 넣어도됨
        //List에 넣는다
        
        int tmpCount = 0;
        List<Integer> answer = new ArrayList<>();
        
        Stack<Integer> prevProg = new Stack<>();
        
        for(int i=0;i<progresses.length;i++){
            int count = (int) Math.ceil(((double)100 - progresses[i]) / speeds[i]);
            
            
            if(prevProg.isEmpty()){
                prevProg.add(count);
                tmpCount++;
                continue;
            }
            
            if(count > prevProg.peek()){
                answer.add(tmpCount);
                
                prevProg.pop();
                prevProg.add(count);
                
                tmpCount = 1;
            }else{
                tmpCount++;
            }
        }
        
        if(tmpCount != 0 ){
            answer.add(tmpCount);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}