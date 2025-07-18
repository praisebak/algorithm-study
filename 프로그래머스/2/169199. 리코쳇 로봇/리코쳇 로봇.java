import java.util.*;
import java.io.*;
class Solution {
    class Point{
        int y;
        int x;
        int idx;
        
        public Point(int y,int x,int idx){
            this.y=y;
            this.x=x;
            this.idx=idx;
        }
        
        public Point(int y,int x){
            this.y=y;
            this.x=x;
            this.idx=0;
        }
    }
    
    Point end;
    char[][] map;
    int N;
    int M;
    public int solution(String[] board) {
        int answer = 0;
        map = new char[board.length][board[0].length()];
        N = board.length;
        M = board[0].length();
        
        int rowIdx = 0;

        
        Point start = null;

        for(String s : board){
            for(int colIdx=0;colIdx<s.length();colIdx++){
                map[rowIdx][colIdx] = s.charAt(colIdx);
                if(map[rowIdx][colIdx] == 'R'){
                    start = new Point(rowIdx,colIdx);
                }else if(map[rowIdx][colIdx] == 'G'){
                    end = new Point(rowIdx,colIdx);
                }
            }
            rowIdx++;
        }
        
        return bfs(start);
    }
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1}; 
    public int bfs(Point start){
        boolean[][] visit = new boolean[101][101];
        Queue<Point> que = new LinkedList<>();
        que.add(start);
        visit[start.y][start.x] = true;
        while(!que.isEmpty()){
            Point point = que.poll();
            if(map[point.y][point.x] == 'G'){
                return point.idx;
                
            }
            for(int i=0;i<4;i++){
                int nY = point.y + dy[i];
                int nX = point.x + dx[i];
                
                while(isValid(nY,nX)){
                    if(map[nY][nX] == 'D'){
                        break;
                    }
                    nY += dy[i];
                    nX += dx[i];
                }
                
                nY -= dy[i];
                nX -= dx[i];

                
                if(visit[nY][nX]) continue;
                visit[nY][nX] = true;
                que.add(new Point(nY,nX,point.idx+1));
            }
        }
        return -1;

    }
   
    public boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nY >= N || nX >= M){
            return false;
        }
        return true;
    }
}