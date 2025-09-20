import java.util.*;
class Solution {
    class Node{
        int y;
        int x;
        int move;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
            this.move=0;
        }
        
        public Node(int y,int x,int move){
            this.y=y;
            this.x=x;
            this.move=move;
        }
    }
    
    int N = 5;
    int M = 5;
    char[][] map;
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        
        
        int idx = 0;
        for(String[] place : places){
            map = new char[5][5];
            List<Node> players= new ArrayList<>();

            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    map[i][j] = place[i].charAt(j);
                    if(map[i][j] == 'P'){
                        players.add(new Node(i,j));
                    }
                }
            }
            
            if(players.size() == 0){
                answer[idx] = 1;
            }
            for(int i=0;i<players.size();i++){
                Node node = players.get(i);
                int result = bfs(node.y,node.x);
            
                if(result == 0){
                    answer[idx] = result;        
                    break;
                }
                //모든플레이어를 순회했는데 문제없었음
                if(i == players.size()-1){
                    answer[idx] = 1;        
                    break;
                }
            }
            
            idx++;
        }
        
        return answer;
    }
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    
    public int bfs(int y,int x){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y,x));
        boolean[][] visit = new boolean[N][M];
        visit[y][x] = true;
        
        while(!que.isEmpty()){
            Node cur = que.poll();

            for(int i=0;i<4;i++){
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(nY < 0 || nX < 0 || nY >=N || nX >=M ) continue;
                if(visit[nY][nX]) continue;

                if(map[nY][nX] == 'P' && cur.move+1 <= 2){
                    return 0;
                }
                if(map[nY][nX] == 'X') continue;   
                if(map[nY][nX] == 'P') continue;
                visit[nY][nX] = true;
                
                que.add(new Node(nY,nX,cur.move+1));
            }
        }
        return 1;
    }
}