import java.util.ArrayList;

class Pos
{
    int x,y;

    public Pos(int x, int y) {
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

        int[][][] rotaTable = new int[N][N][4];
        for(int i=0;i<4;i++)
        {
            setRotatable(table,i,rotaTable);
        }
        

        ArrayList<ArrayList<ArrayList<Pos>>> list = new ArrayList<>();
        connectList(list,rotaTable);

        //dfs돌면서 현재 좌표에서 가능한 퍼즐이 있는지 끼워맞추기


        return answer;
    }

    private void connectList(ArrayList<ArrayList<ArrayList<Pos>>> list, int[][][] rotaTable) 
    {
        for(int m=0;m<4;m++)
        {
            ArrayList<ArrayList<Pos>> posListPerMode = new ArrayList<>();
            for(int i=0;i<rotaTable.length;i++)
            {
                ArrayList<Pos> prev = new ArrayList<>();
                for (int j = 0; j < rotaTable.length; j++) 
                {
                    int cur = rotaTable[i][j][m];
                    if(cur == 1)
                    {
                        prev.add(new Pos(i,j));
                    }
                    else
                    {
                        if(prev.size() != 0) posListPerMode.add(prev);
                        prev = new ArrayList<>();
                    }
                }
            }
            list.add(posListPerMode);
        }

    }

    private void setRotatable(int[][] table, int mode,int[][][] rotaTable) 
    {
        if(mode == 0)
        {
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<M;j++)
                {
                    rotaTable[i][j][mode] = table[i][j];
                }
            }
        }
        else if(mode == 1)
        {
            for(int j=M-1;j>=0;j--)
            {
                for(int i=0;i<N;i++)
                {
                    rotaTable[i][j][mode] = table[M-1-j][i];
                }
            }
        }
        else if(mode == 2)
        {
            for(int i=N-1;i>=0;i--)
            {
                for(int j=M-1;j>=0;j--)
                {
                    rotaTable[i][j][mode] = table[N-1-i][M-1-j];
                }
            }
        }
        else
        {
            for(int j=0;j<M;j++)
            {
                for(int i=N-1;i>=0;i--)
                {
                    rotaTable[i][j][mode] = table[j][N-1-i];
                }
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(null, new int[][] {{1,2,3},{4,5,6},{7,8,9}});
    }
}public class p84021 {
    
}
