import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
    
        Queue<Integer> servers = new LinkedList<>();
        
        for(int i=0;i<players.length;i++){
            while(!servers.isEmpty() && servers.peek() == i){
                servers.poll();
            }
            
            //증설필요
            while(servers.size() < (players[i] / m)){

                servers.add(i + k);
                answer++;
            }
        }

        
        
        return answer;
    }
}