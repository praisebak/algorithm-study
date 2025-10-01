import java.util.*;
class Solution {
    boolean[][] visit;
    int n;
    int m;
    int[][] picture;
    public int[] solution(int M, int N, int[][] picture) {
        this.n=M;
        this.m=N;
        this.picture = picture;
        visit = new boolean[n][m];
        
        int[] answer = new int[2];
        int answerCount = 0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visit[i][j]) continue;
                if(picture[i][j] == 0) continue;
                answerCount++;
                visit[i][j] = true;
                bfs(i,j);
            }
        }
            
    
        return new int[]{answerCount,answerNum};
    }
    
    class Node{
        int y;
        int x;
        public Node(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    
    int[] dy = {-1,1,0,0};
    int answerNum = 0;
    int[] dx = {0,0,-1,1};
    
    public void bfs(int i,int j){
        int size = 1;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(i,j)); 
        visit[i][j] = true;
        int cur = picture[i][j];
        
        while(!que.isEmpty()){
            Node node = que.poll();
            for(int k=0;k<4;k++){
                int nY = node.y + dy[k];
                int nX = node.x + dx[k];
                if(nY < 0 || nX < 0 || nY >= n || nX >= m) continue;
                if(visit[nY][nX]) continue;
                if(picture[nY][nX] != cur) continue;
                size++;
                
                que.add(new Node(nY,nX));
                visit[nY][nX] = true;
            }
        }
        this.answerNum = Math.max(answerNum,size);


    }
}