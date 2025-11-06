import java.util.*;
class Solution {
    class Node{
        int y;
        int x;
        int count;
        public Node(int y, int x,int count){
            this.y=y;
            this.x=x;
            this.count=count;
        }
    }
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    public int solution(int[][] map) {
        int N = map.length;
        int M = map[0].length;
        int answer = 0;
        Queue<Node> que = new LinkedList<>(); 
        que.add(new Node(0,0,0));
        boolean[][] visit = new boolean[N][M];
        visit[0][0] = true;
 
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.y == N-1 && cur.x == M-1){
                return cur.count+1;
            }
            for(int i=0;i<4;i++){
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(nY < 0|| nX< 0 || nY >= N || nX >= M){
                    continue;
                }
                if(visit[nY][nX]) continue;
                if(map[nY][nX] == 0) continue;
                visit[nY][nX] = true;
                que.add(new Node(nY,nX,cur.count+1));
            }
        }

        return -1;
    }
}