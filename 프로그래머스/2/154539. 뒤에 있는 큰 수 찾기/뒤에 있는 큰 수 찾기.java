import java.util.*;
class Node{
    int idx;
    int val;
    public Node(int idx,int val){
        this.idx=idx;
        this.val=val;
    }
}

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        int prevIdx = 0;       
        
        Stack<Node> s = new Stack<>();
        
        s.push(new Node(0,numbers[0])); 
        
        for(int i=1;i<numbers.length;i++){

            while(!s.isEmpty() && s.peek().val < numbers[i]){
                Node prev = s.pop();
                answer[prev.idx] = numbers[i];
            }
            
            s.push(new Node(i,numbers[i]));
        }
        
        //더 큰수들 다 계산하기
        while(!s.isEmpty()){
            answer[s.pop().idx] = -1;    
        }
        
        return answer;
    }
}