import java.util.*;
class Solution {
    class Node{
        int y;
        int x;
        int k;
        String move;
        public Node(int y,int x,int k,String move){
            this.y=y;
            this.x=x;
            this.k=k;
            this.move=move;
        }
    }
    
    int[] dy= {1,0,0,-1 };
    int[] dx = {0,-1,1,0};
    String[] sArr = new String[]{"d","l","r","u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        x--;
        y--;
        r--;
        c--;
        boolean[][][] visit = new boolean[n+1][m+1][k+1];
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(x,y,0,""));
        visit[x][y][0] = true;
        
        List<String> list = new ArrayList<>();
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.y == r && cur.x == c && cur.k == k){
                System.out.println(cur.move);
                list.add(cur.move);
                continue;
            }
            
            if(cur.k == k) continue;
            for(int i=0;i<4;i++){
                int nY = cur.y+dy[i];
                int nX = cur.x+dx[i];
                if(nY <0  || nX< 0 || nY >= n || nX >= m) continue;
                if(visit[nY][nX][cur.k+1]) continue;
                visit[nY][nX][cur.k+1] = true;
                que.add(new Node(nY,nX,cur.k+1,cur.move+sArr[i]));                
            }
            
        }
        if(list.size() == 0){
            return "impossible";
        }
        
        Collections.sort(list);
        return list.get(0);
    }
}