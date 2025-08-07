
import java.util.*;
import java.io.*;
class Solution {
    class Node{
        int y;
        int x;
        int count;
        public Node(int y,int x,int count){
            this.y=y;
            this.x=x;
            this.count=count;
        }
    }
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        boolean[][] visit = new boolean[maps.length][maps[0].length]; 
        int answer = 0;
        
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0,0,1));
        while(!que.isEmpty()){
            Node node = que.poll();
            if(node.y == maps.length-1 && node.x == maps[0].length-1){
                return node.count;
            }
            
            for(int i=0;i<4;i++){
                int nY = dy[i] + node.y;
                int nX = dx[i] + node.x;
                if(nY < 0 || nX < 0 || nY >= maps.length || nX >= maps[0].length) continue;
                if(visit[nY][nX]) continue;
                if(maps[nY][nX] == 0) continue;
                visit[nY][nX] = true;
                que.add(new Node(nY,nX,node.count+1));
            }
        }
        
        
        
        return -1;
    }
}