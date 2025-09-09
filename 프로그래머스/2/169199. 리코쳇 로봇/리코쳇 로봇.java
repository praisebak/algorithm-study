import java.util.*;
class Solution {
    class Node{
        int y;
        int x;
        int move;
        
        public Node(int y,int x){
            this.y=y;
            this.x=x;
        }
        public Node(int y,int x,int move){
            this.y=y;
            this.x=x;
            this.move = move;
        }
    }
    
    public int solution(String[] board) {
        int answer = 0;
        char[][] map = new char[board.length][board[0].length()];
        int row = 0;
        Node start = null;
        for(String s : board){
            for(int i=0;i<s.length();i++){
                char ch = s.charAt(i);
                map[row][i] = ch;
                if(map[row][i] == 'R'){
                    start = new Node(row,i);
                }
            }
            row++;
        }
        
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        int M = board[0].length();
        boolean[][] visit = new boolean[row][board[0].length()];
        visit[start.y][start.x] = true;
        
        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};
        
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(map[cur.y][cur.x] == 'G'){
                return cur.move;
            }
            for(int i=0;i<4;i++){
                int tmpY = cur.y;
                int tmpX = cur.x;
                while(true){
                    tmpY += dy[i];
                    tmpX += dx[i];
                    //장애물, 외부에 부딪혔을때
                    if((tmpY < 0 || tmpX < 0 || tmpY >= row || tmpX >= M) || map[tmpY][tmpX] == 'D'){
                        tmpY -= dy[i];
                        tmpX -= dx[i];
                        if(visit[tmpY][tmpX]) break;
                        que.add(new Node(tmpY,tmpX,cur.move+1));
                        visit[tmpY][tmpX] = true;
                        break;
                    }
                    
                }
                
            }
        }
        
        
        
        return -1;
    }
}