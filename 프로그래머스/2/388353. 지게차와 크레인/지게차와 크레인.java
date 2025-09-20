import java.util.*;

class Solution {
    int N;
    int M;
    boolean[][] visit;
    char[][] map;
    public int solution(String[] storage, String[] requests) {
        int answer = storage.length * storage[0].length();
        
        map = new char[storage.length+2][storage[0].length()+2];
        visit = new boolean[storage.length+2][storage[0].length()+2];
        this.N= storage.length+2;
        this.M= storage[0].length()+2;
        for(int i=0;i<N;i++){
            Arrays.fill(map[i],' ');     
        }
        
        for(int i=1;i<=storage.length;i++){
            for(int j=1;j<=storage[0].length();j++){
                map[i][j] = storage[i-1].charAt(j-1);
            }
        }        

        
        for(String request : requests){
            boolean isK = request.length() >= 2;
            
            char ch = request.charAt(0);
            if(isK){
                for(int i=1;i<N;i++){
                    for(int j=1;j<M;j++){
                        if(map[i][j] == ch){
                            answer--;
                            map[i][j] = ' ';
                        }
                    }
                }
            }else{
                visit = new boolean[N][M];
                answer -= bfs(0,0,ch);
            }
        }
        
       
        return answer;
    }
    
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
    public int bfs(int row,int col,char ch){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(row,col));
        int answer = 0;
        List<Node> removeList = new ArrayList<>();
        
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i=0;i<4;i++){
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if(visit[nY][nX]) continue;
                visit[nY][nX] = true;
                if(map[nY][nX] == ch){
                    answer++;
                    removeList.add(new Node(nY,nX));
                }
                
                if(map[nY][nX] == ' '){
                    que.add(new Node(nY,nX));    
                }
            }
        }
        
        for(Node node : removeList){
            map[node.y][node.x] = ' ';
        }
        
        
        return removeList.size();
    }
}