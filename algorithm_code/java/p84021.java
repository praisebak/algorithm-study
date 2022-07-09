import java.util.ArrayList;
import java.util.HashSet;

class Pos
{
    int x,y;

    public Pos(int y, int x) {
        this.x = x;
        this.y = y;
    }

}

class Solution 
{
    int answer = -1;
    int N;
    int M;
    public int solution(int[][] game_board, int[][] table) {
        N = table.length;
        M = N;

        int[][][] rotaTable = new int[4][N][M];
        for(int i=0;i<4;i++)
        {
            setRotatable(table,i,rotaTable);
        }
        
        ArrayList<ArrayList<ArrayList<Pos>>> tableList = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Pos>>> boardList = new ArrayList<>();

        insertConnectList(tableList,rotaTable);
        reverseGameBoard(game_board);
        insert2rdList(boardList, game_board, new boolean[N][M]);
        // for(int i=0;i<N;i++)
        // {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(game_board[i][j]);
        //     }
        //     System.out.println();
        // }

        for(int i=0;i<4;i++)
        {
            System.out.println(i + "번째");
            for(ArrayList<Pos> l : boardList.get(0))
            {
                for(Pos p : l)
                {
                    System.out.println(p.y + "," + p.x);
                }
                System.out.println();
            }
        }

        compareBoardAndTable(tableList,boardList,new boolean[tableList.get(0).size()],0);
        // checkConnect(boardList);

        return answer;
    }

    private void reverseGameBoard(int[][] game_board) 
    {
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board.length; j++) {
                if(game_board[i][j] == 0) game_board[i][j] = 1;
                else game_board[i][j] = 0;
            }
        }
    }

    private void compareBoardAndTable(ArrayList<ArrayList<ArrayList<Pos>>> tableList,
    ArrayList<ArrayList<ArrayList<Pos>>> boardList,boolean[] visit,int sum) 
    {
        answer = Math.max(sum, answer);
        for(int i=0;i<visit.length;i++)
        {
            for(int m=0;m<4;m++)
            {                    
                boolean flag = false;
                for(int k=0;k<tableList.get(m).size();k++)
                {
                    if(visit[i]) continue;
                    ArrayList<Pos> curTable = tableList.get(m).get(k);
                    ArrayList<Pos> curBoard = boardList.get(0).get(i);
                    if(curTable.size() != curBoard.size())
                    {
                        continue;
                    }
                    if(compare(curTable,curBoard))
                    {
                        visit[i] = true;
                        compareBoardAndTable(tableList,boardList,visit,sum + curTable.size());
                        // System.out.println(i + "번째 = ");
                        // for(Pos p : curTable)
                        // {
                        //     System.out.println(p.y + "," + p.x);
                        // }
                        visit[i] = false;
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    break;

            }
        }
        

    }


    private boolean compare(ArrayList<Pos> tableList, ArrayList<Pos> boardList) 
    {  
        for(int i=0;i<tableList.size();i++)
        {
            Pos curTablePos = tableList.get(i);
            Pos curBoardPos = boardList.get(i);
            if(!(curTablePos.y == curBoardPos.y && curTablePos.x == curBoardPos.x))
            {
                return false;
            }
        }        
        return true;
    }

    private void checkConnect(ArrayList<ArrayList<ArrayList<Pos>>> tableList) 
    {
        for(ArrayList<ArrayList<Pos>> list : tableList)
        {
            for(ArrayList<Pos> l : list)
            {
                for(Pos p : l)
                {
                    System.out.println(p.y + "," + p.x);
                }
                System.out.println();   
            }
        }
    }

    private void insertConnectList(ArrayList<ArrayList<ArrayList<Pos>>> tableList,int[][][] table) 
    {
        boolean[][] visit = new boolean[N][M];
        //TODO 1 -> 4
        for(int m=0;m<4;m++)
        {
            insert2rdList(tableList, table[m], visit);
        }
    }

    private void insert2rdList(ArrayList<ArrayList<ArrayList<Pos>>> tableList, int[][] table, boolean[][] visit) {
        ArrayList<ArrayList<Pos>> posList = new ArrayList<>();
        tableList.add(posList);
        ArrayList<Pos> curRecList = new ArrayList<>();
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                if(table[i][j] == 1 && !visit[i][j] && isValid(i, j))
                {
                    connectListByDFS(curRecList,table,visit,i,j,0,0);
                    if(curRecList.size() == 0) continue;
                    posList.add(curRecList);
                    curRecList = new ArrayList<>();
                }
            }
        }
        if(curRecList.size() != 0) posList.add(curRecList);
    }

    private void connectListByDFS(ArrayList<Pos> puzzle, int[][] table, boolean[][] visit,int curY,int curX,int yDiff,int xDiff) 
    {
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        visit[curY][curX] = true;
        puzzle.add(new Pos(yDiff,xDiff));

        for(int i=0;i<4;i++)
        {
            int nY = dy[i] + curY;
            int nX = dx[i] + curX;
            if(isValid(nY,nX) && !visit[nY][nX] && table[nY][nX] == 1)
            {
                connectListByDFS(puzzle, table, visit, nY, nX,yDiff + dy[i] ,xDiff + dx[i]);
            }
        }
    }

    private boolean isValid(int nextY, int nextX) 
    {
        if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= M)
            return false;
        return true;
    }

    

    private void setRotatable(int[][] table, int mode,int[][][] rotaTable) 
    {
        if(mode == 0)
        {
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<M;j++)
                {
                    rotaTable[mode][i][j] = table[i][j];
                }
            }
        }
        else if(mode == 1)
        {
            for(int j=M-1;j>=0;j--)
            {
                for(int i=0;i<N;i++)
                {
                    rotaTable[mode][i][j] = table[M-1-j][i];
                }
            }
        }
        else if(mode == 2)
        {
            for(int i=N-1;i>=0;i--)
            {
                for(int j=M-1;j>=0;j--)
                {
                    rotaTable[mode][i][j] = table[N-1-i][M-1-j];
                }
            }
        }
        else
        {
            for(int j=0;j<M;j++)
            {
                for(int i=N-1;i>=0;i--)
                {
                    rotaTable[mode][i][j] = table[j][N-1-i];
                }
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[][] {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}}, new int[][] {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}});
        // solution.solution(new int[][] {{0,0,0},{1,1,0},{1,1,1}}, new int[][] {{1,1,1},{1,0,0},{0,0,0}});
        // [[0,0,0],[1,1,0],[1,1,1]]
        // {{1,1,1},{1,0,0},{0,0,0}}
    }
}