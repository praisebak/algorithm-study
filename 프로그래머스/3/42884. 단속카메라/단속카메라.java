import java.util.*;

class Solution {
    class Node{
        int left;
        int right;
        public Node(int left, int right){
            this.left=left;
            this.right=right;
        }
    }
    
    public int solution(int[][] routes) {
        PriorityQueue<Node> que = new PriorityQueue<>((o1,o2) -> {
            return o1.right - o2.right;
        });
            
        int answer = 0;

        
        for(int i=0;i<routes.length;i++){
            int left = routes[i][0];
            int right = routes[i][1];
            que.add(new Node(left,right));
            
        } 
        
        int curCount = 1;
        int curRight= que.poll().right;
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(curRight >= cur.left) continue;

            curCount++;
            curRight = cur.right;
        }
        
        return curCount;
    }
}