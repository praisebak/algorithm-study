import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
class Solution {
    int m;
    int n;
    char[][] map;

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        this.m=m;
        this.n=n;
        
        map = new char[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                map[i][j] = board[i].charAt(j);
            }
        }
        
        boolean isExist = true;
        while(isExist){
            boolean[][] visit = new boolean[m][n];
            List<Node> removedList = new ArrayList<>();
            
            isExist = false;
            for(int i=0;i<m-1;i++){
                for(int j=0;j<n-1;j++){
                    if(map[i][j] == ' ') continue;
                    if(map[i][j] == map[i+1][j+1] && map[i+1][j+1] == map[i+1][j] && map[i][j] == map[i][j+1]){
                        removedList.add(new Node(i,j));
                        removedList.add(new Node(i,j+1));
                        removedList.add(new Node(i+1,j));
                        removedList.add(new Node(i+1,j+1));
                    }                    
                }
            }
            
            for(Node node : removedList){
                if(visit[node.y][node.x]) continue;
                visit[node.y][node.x] = true;
                map[node.y][node.x] = ' ';
                System.out.println(node.y + "," + node.x);
                answer++;
                isExist= true; 
            }
                        
            if(isExist){
                for(int i=m-1;i>=0;i--){
                    for(int j=0;j<n;j++){
                        if(map[i][j] != ' ') continue;
                        moveToDown(i,j);
                    }    
                }
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
    
    public void moveToDown(int y,int x){
        for(int cy=y-1;cy>=0;cy--){
            if(map[cy][x] != ' '){
                map[y][x] = map[cy][x];
                map[cy][x] = ' ';
                return;
            }
        }
    }
}