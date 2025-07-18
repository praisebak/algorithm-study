import java.util.*;

class Solution {
    
    class Node{
        int y;
        int x;
        int time;
        int open;
        
        public Node(int y,int x,int time){
            this.y=y;
            this.x=x;
            this.time=time;
        }
        
        public Node(int y,int x,int time,int open){
            this.y=y;
            this.x=x;
            this.time=time;
            this.open=open;
        }
        
        public Node(int y,int x){
            this.y=y;
            this.x=x;
            this.time=0;
        }
    }
    
    int N;
    int M;
    Node start;
    Node end;
    public int solution(String[] maps) {
        int answer = -1;
        
        char[][] map = new char[maps.length][maps[0].length()];
        N = maps.length;
        M = maps[0].length();
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S'){
                    start = new Node(i,j);
                }
                if(map[i][j] == 'E'){
                    end = new Node(i,j);
                }
            }
        }
        
        boolean[][][] visit = new boolean[2][N][M];
        
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visit[0][start.y][start.x] = true;

        int minAnswer = Integer.MAX_VALUE;
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i=0;i<4;i++){
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(!isValid(nY,nX)) continue;
                if(visit[cur.open][nY][nX]) continue;
                int tmpOpen = cur.open;
                if(map[nY][nX] == 'L'){
                    tmpOpen = 1;
                }
                
                if(map[nY][nX] == 'X') continue;
                if(map[nY][nX] == 'E' && cur.open == 1){
                    minAnswer = Math.min(minAnswer,cur.time+1);
                    
                }
                
                visit[cur.open][nY][nX] = true;
                que.add(new Node(nY,nX,cur.time+1,tmpOpen));
            }
        }
                
        if(minAnswer == Integer.MAX_VALUE) return -1;
        return minAnswer;
    }
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    
    public boolean isValid(int nY, int nX){
        if(nY < 0 || nY >= N || nX < 0 || nX >= M) return false;
        return true;
    }
}