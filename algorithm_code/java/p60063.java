import java.util.*;
 
class Solution {
    class Item{
        int x1, x2, y1, y2, time, vertical;
        Item(int x1, int y1, int x2, int y2, int time, int v){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
            this.vertical = v;
        }
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        Queue<Item> q = new LinkedList<>();
        int[][] op = {{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][][] visited = new boolean[board.length][board.length][2];
        
        q.offer(new Item(0, 0, 0, 1, 0, 0));
        
        while(!q.isEmpty()){
            Item item = q.peek();
            q.poll();
            
            if(item.x1 < 0 || item.x1 >= board.length || item.y1 < 0 || item.y1 >= board.length ||
                   item.x2 < 0 || item.x2 >= board.length || item.y2 < 0 || item.y2 >= board.length){
                continue;
            }
            
            if(board[item.x1][item.y1] == 1 || board[item.x2][item.y2] == 1){
                continue;
            }
            
            if(visited[item.x1][item.y1][item.vertical] && 
              visited[item.x2][item.y2][item.vertical])
                continue;
                
            if((item.x1 == board.length - 1 && item.y1 == board.length - 1) ||
               (item.x2 == board.length - 1 && item.y2 == board.length - 1)){
                answer = item.time;
                break;
            }
            
            visited[item.x1][item.y1][item.vertical] = true;
            visited[item.x2][item.y2][item.vertical] = true;
            
            for(int i = 0; i < op.length; i++){
                int n_x1 = item.x1 + op[i][0];
                int n_y1 = item.y1 + op[i][1];
                int n_x2 = item.x2 + op[i][0];
                int n_y2 = item.y2 + op[i][1];
 
                q.offer(new Item(n_x1, n_y1, n_x2, n_y2, item.time + 1, item.vertical));
            }
            
            if(item.vertical == 1){
                if(item.y1 - 1 >= 0 && board[item.x1][item.y1 - 1] == 0 && board[item.x2][item.y2 - 1] == 0){
                    q.offer(new Item(item.x1, item.y1, item.x1, item.y1 - 1, item.time + 1, 0));
                    q.offer(new Item(item.x2, item.y2, item.x2, item.y2 - 1, item.time + 1, 0));
                }
                
                if(item.y1 + 1 <= (board.length - 1) && 
                   board[item.x1][item.y1 + 1] == 0 && board[item.x2][item.y2 + 1] == 0){
                    q.offer(new Item(item.x1, item.y1, item.x1, item.y1 + 1, item.time + 1, 0));
                    q.offer(new Item(item.x2, item.y2, item.x2, item.y2 + 1, item.time + 1, 0));                    
                }
            }else{
                if(item.x1 - 1 >= 0 && board[item.x1 - 1][item.y1] == 0 &&
                  board[item.x2 - 1][item.y2] == 0){            
                    q.offer(new Item(item.x1, item.y1, item.x1 - 1, item.y1, item.time + 1, 1));
                    q.offer(new Item(item.x2, item.y2, item.x2 - 1, item.y2, item.time + 1, 1));
                }
                
                if(item.x1 + 1 <= (board.length - 1) && board[item.x1 + 1][item.y1] == 0 &&
                  board[item.x2 + 1][item.y2] == 0){
                    q.offer(new Item(item.x1, item.y1, item.x1 + 1, item.y1, item.time + 1, 1));
                    q.offer(new Item(item.x2, item.y2, item.x2 + 1, item.y2, item.time + 1, 1));    
                }
            }
        }
 
        return answer;
    }
}


/*
import java.util.*;

class Node implements Cloneable
{
    int x;
    int y;
    int depth;
    //1 가로 2 세로
    int direction;
    public Node(int x, int y, int direction,int depth) 
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.depth = depth;
    }

    public Node() 
    {
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
    
class Solution 
{
    int[][] board;
    int N = 0;
    private boolean[][] visit;
    private boolean[][] oneVisit;

    public int solution(int[][] boardTmp) throws CloneNotSupportedException 
    {
        N = boardTmp.length;
        this.board = boardTmp;
        visit = new boolean[N][N];
        oneVisit = new boolean[N][N];
        int answer = 0;
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(1,0,1,0));
        oneVisit[0][1] = true;
        while (q.size() != 0) 
        {
            Node cur = q.poll();
            if(cur.x == N-1 && cur.y == N-1)
            {
                answer = cur.depth;
                break;
            }
            getMovedNode(cur,q);
            addRotatedNode(cur,q);
        }
        return answer;
    }

    private void addRotatedNode(Node cur, Queue<Node> q) throws CloneNotSupportedException 
    {
        for(int i=0;i<4;i++)
        {
            Node subNextLeft = (Node)cur.clone();
            Node subNextRight = (Node)cur.clone();
            subNextLeft.depth++;
            subNextRight.depth++;
            subNextLeft.direction = cur.direction == 1 ? 0 : 1;
            subNextRight.direction = cur.direction == 1 ? 0 : 1;
            boolean flag = false;

            if(cur.direction == 1 && i > 2)
            {
                break;
            }
            if(i <= 1 && cur.direction == 0)
            {
                continue;
            }
            if(i == 0 && checkRange(cur.x-1, cur.y-1) && checkRange(cur.x, cur.y-1))
                flag = true;
            else if(i == 1 && checkRange(cur.x-1, cur.y+1) && checkRange(cur.x, cur.y+1))
                flag = true;
            else if(i == 2  && checkRange(cur.x-1, cur.y-1) && checkRange(cur.x-1, cur.y))
                flag  = true;
            else if(i == 3  && checkRange(cur.x+1, cur.y-1) && checkRange(cur.x+1, cur.y))
                flag  = true;

            if(i == 0)
            {
                subNextLeft.x--;
            }
            else if(i == 1)
            {
                subNextLeft.x--;
                subNextLeft.y++;
                subNextRight.y++;
            }
            else if(i == 2)
            {
                subNextLeft.y--;
            }
            else
            {
                subNextLeft.y--;
                subNextLeft.x++;
                subNextRight.x++;
            }

            if(flag)
            {
                if(subNextLeft.direction == 1)
                {
                    
                    if(oneVisit[subNextRight.y][subNextRight.x] == false)
                    {
                        if(subNextRight.x != 0)
                        {
                            q.add(subNextRight);
                            oneVisit[subNextRight.y][subNextRight.x] = true;
                        }
                    }
                    if(oneVisit[subNextLeft.y][subNextLeft.x] == false)
                    {
                        if(subNextLeft.x != 0)
                        {
                            q.add(subNextLeft);
                            oneVisit[subNextLeft.y][subNextLeft.x] = true;
                        }
                    }
                }
                else
                {
                    if(visit[subNextRight.y][subNextRight.x] == false)
                    {
                        if(subNextRight.y != 0)
                        {
                            q.add(subNextRight);
                            visit[subNextRight.y][subNextRight.x] = true;
                        }
                    }
                    if(visit[subNextLeft.y][subNextLeft.x] == false)
                    {
                        if(subNextLeft.y != 0)
                        {
                            q.add(subNextLeft);
                            visit[subNextLeft.y][subNextLeft.x] = true;
                        }
                    }
                }
            }
        }
    }


    private boolean checkRange(int x,int y)
    {
        if(x < 0 || y < 0)
            return false;
        if(x >= N || y >= N)
            return false;
        if(board[y][x] == 1)
            return false;
        return true;
    }

    private void getMovedNode(Node cur,Queue<Node> que) throws CloneNotSupportedException 
    {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        for(int i=0;i<4;i++)
        {
            Node newNode = (Node) cur.clone();
            newNode.depth++;
            int nX = dx[i] + newNode.x;
            int nY = dy[i] + newNode.y;

            if(cur.direction == 1)
            {
                if(nY >= 0 && nY < N && nX > 0 && nX < N && !oneVisit[nY][nX] && board[nY][nX] != 1 && board[nY][nX-1] != 1)
                {
                    newNode.x = nX;
                    newNode.y = nY;
                    oneVisit[nY][nX] = true;
                    que.add(newNode);
                }
            }
            else
            {
                if(nY > 0 && nY < N && nX >= 0 && nX < N && !visit[nY][nX] && board[nY][nX] != 1 && board[nY-1][nX] != 1)
                {
                    newNode.x = nX;
                    newNode.y = nY;
                    visit[nY][nX] = true;
                    que.add(newNode);
                }
            }
        }
    }

}

*/