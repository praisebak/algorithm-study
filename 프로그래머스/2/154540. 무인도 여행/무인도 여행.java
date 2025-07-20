import java.util.*;
import java.io.*;

class Solution {
    int N;
    int M;
    boolean[][] visit;
    List<Integer> list = new ArrayList<>();
    String[] maps;
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        visit = new boolean[N][M];
        
        this.maps=maps;
        
        for(int i=0;i<N;i++){
            
            for(int j=0;j<M;j++){
                char cur = maps[i].charAt(j);
                if(visit[i][j]) continue;
                if(cur == 'X'){
                    continue;
                }
                bfs(i,j);                
            }
        }
        
        if(list.size() == 0){
            list.add(-1);
        }
        Collections.sort(list);
        
        return list.stream()
                         .mapToInt(num -> num)
                         .toArray();   
    }
    class Point{
        int y;
        int x;
        public Point(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    public void bfs(int y,int x){
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(y,x));
        visit[y][x] = true;
        int answer = maps[y].charAt(x) -'0';
        while(!que.isEmpty()){
            Point cur = que.poll();

            for(int i=0;i<4;i++){
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if(visit[nY][nX]) continue;
                if(maps[nY].charAt(nX) == 'X') continue;
                visit[nY][nX] = true;
                answer+= (maps[nY].charAt(nX) - '0') ;
                que.add(new Point(nY,nX));
            }
        }
        if(answer == 0) return;
        System.out.println(y + "," + x + " " +answer);
        list.add(answer);
        
    }
}