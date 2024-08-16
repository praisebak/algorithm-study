
import java.util.*;
// bfs로 풀건데
//PriorityQueue로 풀고

class Node implements Comparable<Node>{
    int algo;
    int code;  
    int time;
    
    public Node(int algo,int code,int time){
        this.algo = algo; 
        this.code = code;
        this.time = time;
    }
    
    @Override
    public int compareTo(Node o){
        return this.time - o.time;
    }
}

class Solution {
    public boolean isSolveable(int[] problem,Node curStatus){
        return curStatus.algo >= problem[0] && curStatus.code >= problem[1];        
    }
    
    public int solution(int alp, int cop, int[][] problems) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(alp,cop,0));
        
        while(!que.isEmpty()){
            Node cur = que.poll();
            
            
            int solveCount = 0;
            
            for(int i=0;i<problems.length;i++){
                int[] problem = problems[i];
                if(!isSolveable(problems[i],cur)) continue;
                solveCount++;
                que.add(new Node(cur.algo + problem[2],cur.code + problem[3],cur.time +problem[4]));                               
            }
            if(solveCount == problems.length){
                return cur.time;
            }
        
            que.add(new Node(cur.algo + 1,cur.code + 0,cur.time +1));                               
            que.add(new Node(cur.algo + 0,cur.code + 1,cur.time +1));                               
            //문제안푸는 경우도 있을수있음
        }
        
        

    
        return 0;
    
        
        
    }
}
