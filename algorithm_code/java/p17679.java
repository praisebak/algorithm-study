import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;


class Pair
{
    int row;
    int cal;
    Pair(int row,int cal)
    {
        this.row = row;
        this.cal = cal;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof Pair)
        {
            Pair tmpPair = (Pair) obj;
            return row == tmpPair.row && cal == tmpPair.cal;
        }
        return false;
    }

    public int hashCode()
    {
        return Objects.hash(row,cal);
    }

}


class Solution
{
    boolean[][] visit;
    Character[][] arr;
    // 왼 오 상 하
    // 1과 3과 1,3을 인덱스로 사용해
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,1,-1};


    HashMap<Character,HashSet<Pair> > checkedPair;


    public int solution(int m,int n,String[] board)
    {
        int answer = 0;
        int prevAns = answer-1;
        arr = new Character[m][n];
        initArr(board);

        while(answer != prevAns)
        {
            visit = new boolean[m][n];
            checkedPair = new HashMap<>();
            prevAns = answer;
            for(int i=0;i<m;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(!visit[i][j])
                    {
                        checkTwoXTwoVal(i, j);
                        dfs(i,j,0);
                    }
                }
            }
            answer += deleteCollectedBlock();
            moveDownBlocks();
        }

        return answer;
    }
    private void moveDownBlocks() 
    {
        for (int i = 0; i < arr[0].length; i++) 
        {
            moveDown(i);
            
        }
    }
    private void moveDown(int cal) 
    {
        for(int row = arr.length-1;row>0;row--)
        {
            int tmpRow = arr.length-1;
            while(tmpRow > 0)
            {
                if(arr[tmpRow][cal] == '0')
                {
                    arr[tmpRow][cal] = arr[tmpRow-1][cal];
                    arr[tmpRow-1][cal] = '0';
                }
                tmpRow--;

            }
        }

    }
    
    private int deleteCollectedBlock() 
    {
        int count = 0; 
        for(Character key : checkedPair.keySet())
        {
            for(Pair pair : checkedPair.get(key))
            {
                if(arr[pair.row][pair.cal] == '0')
                {
                    continue;
                }
                arr[pair.row][pair.cal] = '0';
                count++;
            }
        }
        return count;
    }

    private void checkTwoXTwoVal(int curRow,int curCal) 
    {
        Character key = arr[curRow][curCal];
        Pair curPoint = new Pair(curRow,curCal);
        Pair right = new Pair(curRow,curCal+1);
        Pair down = new Pair(curRow+1,curCal);
        Pair rightDown = new Pair(curRow+1,curCal+1);
        HashSet<Pair> tmpSet = checkedPair.getOrDefault(key, new HashSet<Pair>());

        if(isValidArea(right,arr[curRow][curCal]) && isValidArea(down,arr[curRow][curCal]) && isValidArea(rightDown,arr[curRow][curCal]))
        {
            tmpSet.add(curPoint);
            tmpSet.add(right);
            tmpSet.add(down);
            tmpSet.add(rightDown);
            checkedPair.put(key, tmpSet);

        }
    }

    private boolean isValidArea(Pair direction, Character character) 
    {
        int row = direction.row;
        int cal = direction.cal;


        if(row >= arr.length || cal >= arr[0].length) 
        {
            return false;
        }
        if(cal < 0 || row < 0)
        {
            return false;
        }
        if(character != arr[row][cal])
        {
            return false;
        }
        return true;
    }

    private int dfs(int row, int cal, int depth) 
    {
        int tmpDepth = depth;
        visit[row][cal] = true;

        for(int i=0; i<4;i++)
        {
            int nextX = dx[i] + cal;
            int nextY = dy[i] + row;
            if(isValid(nextY,nextX) && !visit[nextY][nextX]
            && arr[nextY][nextX] == arr[row][cal])
            {
                checkTwoXTwoVal(nextY, nextX);
                tmpDepth = dfs(nextY,nextX,depth+1);
            }

        }
        return tmpDepth;
    }

    private boolean isValid(int nextY, int nextX) 
    {
        if(nextY < 0  || nextY >= arr.length)
        {
            return false;
        }
        if(nextX < 0 || nextX >= arr[0].length)
        {
            return false;
        }
        return true;
    }


    private void initArr(String[] board) 
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length();j++)
            {
                arr[i][j] = board[i].charAt(j);
            }
        }
    }    

    public static void main(String[] args) 
    {
        Solution solution = new Solution();
        solution.solution(4,4, new String[]{"ABCD", "BACE", "BCDD", "BCDD"});
        
    }
}