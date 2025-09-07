import java.util.*;
class Solution {
    class Node{
        int y;
        int x;
        int isSwitchOn = 0;
        int move = 0;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
            this.isSwitchOn = 0;
        }
        
        public Node(int y,int x,int isSwitchOn){
            this.y=y;
            this.x=x;
            this.isSwitchOn = isSwitchOn;
        }
        
        public Node(int y,int x,int isSwitchOn,int move){
            this.y=y;
            this.x=x;
            this.isSwitchOn = isSwitchOn;
            this.move=move;
        }        
    }
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    
    public int solution(String[] maps) {
        int answer = 0;
        
        boolean[][][] visit = new boolean[maps.length][maps[0].length()][2];
        Node start = null;        
        char[][] map = new char[maps.length][maps[0].length()];

        int row = 0;
        for(String s : maps){
            for(int i=0;i<s.length();i++){
                char ch = s.charAt(i);
                if(ch == 'S'){
                    start = new Node(row,i);
                }
                map[row][i] = ch;
            }
            row++;
        }
        
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(map[cur.y][cur.x] == 'E' && cur.isSwitchOn == 1){
                return cur.move;
            }
            for(int i=0;i<4;i++){
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(nY < 0 || nX < 0 || nY >= maps.length || nX >= maps[0].length()){
                    continue;
                }
                if(visit[nY][nX][cur.isSwitchOn]) continue;
                if(map[nY][nX] == 'X') continue;
                if(map[nY][nX] == 'L'){
                    que.add(new Node(nY,nX,1,cur.move+1));
                    //어쩌면 그냥 cur.is어쩌구로 하는게 맞을지도
                    visit[nY][nX][1] = true;
                }else{
                    que.add(new Node(nY,nX,cur.isSwitchOn,cur.move+1));
                    visit[nY][nX][cur.isSwitchOn] = true;
                }
            }
        }
        
        
        
        return -1;
    }
}