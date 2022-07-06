import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Pair 
{
    int y;
    int x;
    int cnt;
    public Pair(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}

class Solution {
    public int solution(int[][] board, int r, int c) 
    {
        int answer = Integer.MAX_VALUE;
        String[] permutation = getPeremu(board);

        for(String p : permutation)
        {
            int move = 0;
            Pair pair = new Pair(r,c,0);
            int[][] boardTmp = new int[4][4];
            for(int i=0;i<4;i++)
            {
                for (int j = 0; j < 4; j++) 
                {
                    boardTmp[i][j] = board[i][j];    
                }
            }

            for(String cardStr : p.split(" "))
            {
                int card = Integer.parseInt(cardStr);
                //첫번째 카드 찾기
                pair.cnt = 0;
                searchCard(pair,card,boardTmp,new boolean[4][4]);
                move += pair.cnt;
                move++;
                pair.cnt = 0;
                //두번째 카드 찾기
                searchCard(pair,card,boardTmp,new boolean[4][4]);
                move += pair.cnt;
                move++;
            }
            answer = Math.min(move,answer);
        }
        return answer;
    }

    private void searchCard(Pair curPair, int card, int[][] board,boolean[][] visit) 
    {
        Queue<Pair> q = new LinkedList<>();
        curPair.cnt = 0;
        q.add(curPair);
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,-1,1};
        int prevD = 0;
        //왜 bfs로하면 최단거리로 할수있는데 왜 간과한거지 왜 bfs로 안된다고 생각했는지....
        while(q.size() != 0)
        {
            Pair cur = q.poll();
            prevD = cur.cnt;

            int cR = cur.y;
            int cC = cur.x;
            int move = cur.cnt;

            if(board[cR][cC] == card)
            {
                curPair.y = cR;
                curPair.x = cC;
                curPair.cnt = move;
                board[cR][cC] = 0;
                return;
            }

            for(int i=0;i<4;i++)
            {
                cR = cur.y + dy[i];
                cC = cur.x + dx[i];

                if(!isValid(cR,cC)) continue;
                if(visit[cR][cC]) continue;

                q.add(new Pair(cR,cC,move+1));
                visit[cR][cC] = true;
            }
            

            for(int i=0;i<4;i++)
            {
                boolean flag = false;
                cR = cur.y + dy[i];
                cC = cur.x + dx[i];
                while(isValid(cR,cC))
                {
                    if(isEnd(cR,cC,board))
                    {
                        flag = true;
                        break;
                    }
                    cR += dy[i];
                    cC += dx[i];
                }

                if(!flag)
                {
                    cR -= dy[i];
                    cC -= dx[i];
                }

                if(visit[cR][cC]) continue;
                if(cR == cur.y && cC == cur.x) continue;

                q.add(new Pair(cR,cC,move+1));
                visit[cR][cC] = true;
            }

        }
        curPair.cnt = 0;
        
    }

    private boolean isEnd(int r, int c,int[][] board) 
    {
        if(board[r][c] != 0)
        {
            return true;
        }
        return false;
    }
    private boolean isValid(int r, int c) 
    {
        if(r < 0 || c < 0 || r >= 4 || c >= 4)
            return false;
        return true;
    }
    private String[] getPeremu(int[][] board) {
        HashSet<Integer> cardSet = new HashSet<>();
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board.length;j++)
            {
                if(board[i][j] == 0)
                    continue;
                cardSet.add(board[i][j]);
            }
        }

        ArrayList<String> permuList = new ArrayList<>();
        setPermuDFS(permuList,"",cardSet,new boolean[9],0);
        String[] resultArr = new String[permuList.size()];
        int i=0;
        for(String p : permuList)
        {
            resultArr[i++] = p;
        }
        return resultArr;
    }

    void setPermuDFS(ArrayList<String> permuList,String res,HashSet<Integer> cardSet,boolean[] visit,int depth)
    {
        if(depth == cardSet.size())
        {
            permuList.add(res);
            return;
        }  


        for(Integer key : cardSet)
        {
            if(!visit[key])
            {
                visit[key] = true;
                setPermuDFS(permuList,res + key + " ",cardSet,visit,depth+1);

                visit[key] = false;
            }

        }




    }

    public static void main(String[] args) 
    {
        int[][] a = {{1,2,3,4},{1,2,3,4}};
        int[][] b= a.clone();
        b[0][0]=9;
        System.out.println(a[0][0]);
        
    }
}

/*
* 
* 
* 
* 
* fail code
* import java.util.LinkedList;
import java.util.Queue;


class Node
{
    int row;
    int cal;
    Node(int row,int cal)
    {
        this.row = row;
        this.cal = cal;
    }

    Node()
    { 
    }
}

class Solution {
    String debug = "오왼아위";
    int[][] board;
    int N;
    static Node t;
    int[] dx = {1,-1,0,0};
    int[] dy= {0,0,1,-1};
    int result = Integer.MAX_VALUE;
    String tmp;
    public int solution(int[][] boardInst, int r, int c) 
    {
        boolean[][][] visit;
        board = boardInst;
        N = board.length;
        int count = 0;
        int answer = 0;
        count = getCount();
        visit = new boolean[4][4][8];
        visit[r][c][0] = true;
        dfs(r,c,count,0,0,visit); 
        answer = result;
        return answer;
    }

    private int getCount() 
    {
        int count = 0;
        for(int i=0;i<board.length;i++)
        {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] != 0)
                    count++;
            }
        }
        return count / 2;
    }

    private void dfs(int r, int c, int count, int prev,int depth,boolean[][][] visit) 
    {
        

        if(endLoop(count,depth))
        {
            return;
        }

        if(prev != 0 && checkCard(prev,r,c))
        {
            count--;
        }

        for(int i=0;i<4;i++)
        {
            System.out.println(i);
            Node node;
            if((node = getMoveNode(dx[i], dy[i], new Node(r,c),prev,visit)) != null)
            {
                if(board[r][c] != 0)
                {
                    visit[node.row][node.cal][board[r][c]] = true;
                    // System.out.println("MOVE : 현 좌표 = " + r + "," + c + " -> " + node.row + "," + node.cal + " 방향 = " + debug.charAt(i) + " 깊이 = " + depth);
                    dfs(node.row, node.cal,count,board[r][c],depth+1,visit);
                    visit[node.row][node.cal][board[r][c]] = false;

                }
                else
                {
                    visit[node.row][node.cal][prev] = true;
                    // System.out.println("MOVE : 현 좌표 = " + r + "," + c + " -> " + node.row + "," + node.cal + " 방향 = " + debug.charAt(i) + " 깊이 = " + depth);
                    dfs(node.row, node.cal,count,prev,depth+1,visit);
                    visit[node.row][node.cal][prev] = false;
                }
            }

            
        }
    }

    private boolean endLoop(int count, int depth) {
        if(count == 0)
        {
            this.result = Math.min(depth,result);
            return true;
        }
        return false;
    }

    private boolean checkCard(int prev,int r,int c) {
        if(board[r][c] == prev)
            return true;
        return false;
    }

    private Node getMoveNode(int dx, int dy,Node cur,int prev,boolean[][][] visit) 
    {  
        int x = dx + cur.cal;
        int y = dy + cur.row;

        if(isValid(x, y))
        {
            if(visit[y][x][prev])
                return null;
            return new Node(y,x);
        }
        return null;
    }

    private Node getCTRLNode(int dx, int dy,Node cur,int prev,boolean[][][] visit) 
    {
        int x = cur.cal;
        int y = cur.row;
        int prevX = x;
        int prevY = y;
        while(isValid(x, y))
        {
            if(!(x == cur.cal && y == cur.row) && isCTRLMoveEnd(x,y) )
            {
                if(y == cur.row && x == cur.cal)
                {
                    return null;
                }
                return new Node(y,x);
            }
            prevX = x;
            prevY = y;
            x += dx;
            y += dy;
        }
        if(visit[prevY][prevX][prev])
            return null;
        return new Node(prevY,prevX);
    }

    private Boolean isValid(int x,int y)
    {
        if(x >= 4 || y>= 4 || x < 0 || y < 0)
            return false;

        return true;
    }

    private Boolean isCTRLMoveEnd(int x,int y)
    {
        //카드가 있는 경우
        if(board[y][x] != 0)
            return true;
        return false;
    }


    public static void main(String[] args) {
        Solution solu = new Solution();
        solu.solution(new int[][]{{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}}, 1, 0);
    }

}


*/