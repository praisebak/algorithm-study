import java.util.*;
class Solution {
    class Node{
        int y;
        int x;
        boolean isLeverOn;
        int time;
        public Node(int y,int x,boolean isLeverOn,int time){
            this.y = y;
            this.x = x ;
            this.isLeverOn = isLeverOn;
            this.time = time;

        }
        
        @Override
        public String toString(){
            return y  +"," + x + " " + isLeverOn + " " + time;
        }
        
    }
    
    boolean[][][] visit;
    String[] maps;
    int answer = 0;
    public boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
        
    }
    
    int[] dy = {-1,1,0,0 };
    int[] dx= {0,0,-1,1};
    
    public void bfs(int row,int cal){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(row,cal,false,0));
        visit[row][cal][0] = true;

        
        while(!que.isEmpty()){
            Node n = que.poll();
            if(maps[n.y].charAt(n.x) == 'E' && n.isLeverOn){
                answer = n.time;
                return;
            }

            for(int i=0;i<4;i++){
                int nY = dy[i] + n.y;
                int nX = dx[i] + n.x;
                
                if(!isValid(nY,nX)) continue;

                int isLever = n.isLeverOn ? 1 : 0;
                char next = maps[nY].charAt(nX);
                boolean leverMeet = n.isLeverOn ? true :false;
                
                if(visit[nY][nX][isLever]) continue;
                if(next == 'X') continue;
                if(next == 'L') leverMeet = true;
                
                visit[nY][nX][isLever] = true;
                que.add(new Node(nY,nX,leverMeet,n.time+1));
            }

            
            
        }
        
        
        
               
        
        
        
    }

    
    
    int N;
    int M;
    public int solution(String[] maps) {
        this.maps = maps;
        //0은 lever 꺼짐 1은 켜짐
        visit = new boolean[maps.length][maps[0].length()][2];
        
            
        
        N = maps.length;
        M = maps[0].length();
        for(int n=0;n<N;n++){
            for(int i=0;i<M;i++){
                char cur = maps[n].charAt(i);
                if(cur == 'S'){
                    bfs(n,i);
                }   
            }
        }
        
        return answer == 0 ? -1 : answer;
        
    }
}
