
import java.util.*;

class Solution {
    
    class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    int N;
    int M;
    char[][] maps;
    boolean[][] visit; 
    
    public int[] solution(String[] map) {
        N = map.length;
        M = map[0].length();
        int[] answer;
        visit = new boolean[N][M];
        this.maps = new char[map.length][map[0].length()];
        
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length();j++){
                this.maps[i][j] = map[i].charAt(j);
            }
        }
        
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length();j++){
                if(visit[i][j]) continue;
                if(maps[i][j] == 'X') continue;
                visit[i][j]= true;
                
                list.add(bfs(i,j));
            }
        }
        
        Collections.sort(list,(o1,o2) -> o1 - o2);
        
        answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        if(answer.length == 0){
            return new int[]{-1};            
        }
        return answer;
    }
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    public int bfs(int y,int x){
        int answer = 0;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y,x));
        answer += maps[y][x] - '0';
        
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i=0;i<4;i++){
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];

                if(nY<  0 || nX < 0 || nY >= N  || nX >=M ) continue;
                if(visit[nY][nX]) continue;
                if(maps[nY][nX] == 'X') continue;
                
                visit[nY][nX] = true;

                answer += (maps[nY][nX] - '0');
                que.add(new Node(nY,nX));
            }
        }
        
        return answer;        
    }
}