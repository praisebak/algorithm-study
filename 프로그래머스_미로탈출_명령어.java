import java.util.*;
class Solution {
    
    class Node{
        int y;
        int x;
        int k;
        String s;
        public Node(int y,int x,int k,String s){
            this.y = y;
            this.x = x;
            this.k = k;
            this.s = s;
        }
        
        
    }
    int[][] map;
    int N;
    int M;
    String answer = "";
    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        map = new int[n][m];
        
        N = n; 
        M = m;

     
        bfs(x-1,y-1,r-1,c-1,k);    
        
        
        if(answer.isEmpty()){
            answer = "impossible";
        }
        
        return answer;
    }
    
    String[] dS = {"d","l","r","u"};
    //d l r u
    int[] dx = {0,-1,1,0};
    
    int[] dy = {1,0,0,-1};
    
    public void bfs(int startR,int startC,int objR,int objC,int k){
        boolean[][][] visit = new boolean[N][M][k+1];
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(startR,startC,0,""));

        while(!que.isEmpty()){
            Node cur = que.poll();
            
            if(distance(cur.y,cur.x,objR,objC) + cur.k > k) continue;
            
            if(cur.k == k){
                if(objR == cur.y && objC == cur.x){
                    answer = cur.s;
                    return;
                }
                continue;
            }
            
            for(int i=0;i<4;i++){
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX][cur.k+1]) continue;
                que.add(new Node(nY,nX,cur.k+1,cur.s + dS[i]));
                visit[nY][nX][cur.k+1] = true;
            }
            
        }
        
        
    }
    public boolean isValid(int nY,int nX){
        if(nY < 0|| nX < 0 || nY >= N || nX >= M){
            return false;
        }
        return true;
    }
    
    public int distance(int y,int x,int objR,int objC){
        return Math.abs(y-objR) + Math.abs(x - objC);
    }
    
  
}
