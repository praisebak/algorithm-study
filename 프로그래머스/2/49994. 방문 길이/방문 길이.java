class Solution {
    boolean[][][][] visit = new boolean[15][15][15][15];
    class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    
    public int solution(String dirs) {
        Node start = new Node(0,0);
        int dir = 0;
        for(int i=0;i<dirs.length();i++){
            char ch = dirs.charAt(i);
            if(ch == 'U') dir = 0;
            if(ch == 'D') dir = 1;
            if(ch == 'L') dir = 2;
            if(ch == 'R') dir = 3;
            move(dir,start);
        }
        
        return answer;
    }
    
    int answer = 0;
    
    public void move(int dir,Node node){
        int nY = node.y + dy[dir];
        int nX = node.x + dx[dir];
        if(nY <= -6 || nX <= -6 || nY >= 6 || nX >= 6) return;
        if(!visit[node.y+5][node.x+5][nY+5][nX+5]){
            answer++;
        }
        visit[node.y+5][node.x+5][nY+5][nX+5] = true;
        visit[nY+5][nX+5][node.y+5][node.x+5] = true;

        node.y = nY;
        node.x = nX;
    }
}