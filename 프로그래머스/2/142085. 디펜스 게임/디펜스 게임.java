import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        PriorityQueue<Integer> maxEnemy = new PriorityQueue<>((o1,o2) -> o1-o2);
        
        for(int i=0;i<enemy.length;i++){
            maxEnemy.add(enemy[i]);
            
            //무적권 있으면 그냥 써.
            if(k != 0){
                k--;
            }else{
                
                n -= maxEnemy.poll();
            }
            
            if(n < 0){
                return i;
            }
        }

        
        return enemy.length;
    }
}