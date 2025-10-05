import java.util.*;

class Node{
    int y;
    int x;
    int money;
    int dir;
    public Node(int y,int x,int money,int dir){
        this.y=y;
        this.x=x;
        this.money=money;
        this.dir =dir;
    }
}
class Solution {
    int N;
    int M;
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    public int solution(int[][] map) {
        int answer = Integer.MAX_VALUE;
        this.N = map.length;
        this.M = map[0].length;
        
        int[][][] visit = new int[N][M][4];
        
        for(int i=0;i<visit.length;i++){
            for(int j=0;j<N;j++){
                Arrays.fill(visit[i][j],Integer.MAX_VALUE);
            }
        }
        Arrays.fill(visit[0][0],0);
        
        
        PriorityQueue<Node> que = new PriorityQueue<>((o1,o2) -> o1.money - o2.money);
        que.add(new Node(0,0,0,0));
        
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.y == N-1 && cur.x == N-1){
                answer = Math.min(answer,cur.money);
            }
            
            for(int i=0;i<4;i++){
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if(map[nY][nX] == 1) continue;
                
                int money = cur.money+100;
                //직선
                if((cur.dir < 2 && i < 2) || (cur.dir >= 2 && i >= 2)) {
                //코너
                }else{
                    if(cur.y == 0 && cur.x == 0){
                        
                    }else{
                        money += 500;
                    }
                }
                
                if(visit[nY][nX][i] < money){
                    continue;
                }
                visit[nY][nX][i] = money;
             
                que.add(new Node(nY,nX,money,i));
            }
        }
        
        
        return answer;
    }
}