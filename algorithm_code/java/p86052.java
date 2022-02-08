import java.util.ArrayList;
import java.util.HashMap;
    //left 0, right 1,up 2,down 3
    class Solution 
    {
        static boolean[][][] visit; 
        int[][] directionArr = {{0,-1},{0,1},{1,0},{-1,0}};
        static int[][] arrGrid;
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<Integer,Integer> leftTurnMap = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> rightTurnMap = new HashMap<Integer,Integer>();

        void dfs(int direction,int countNum,int row,int cal)
        {
            //S.out.print("이전 방향 : " + direction + " " + row + "," + cal + " ");
            if(visit[direction][row][cal])
            {
                answer.add(countNum);
                return;
            }
            int curRow = 0;
            int curCal = 0;
            visit[direction][row][cal] = true;
            
            if(arrGrid[row][cal] == 2)
            {
            direction = leftTurnMap.get(direction);
        }
        else if(arrGrid[row][cal] == 3)
        {
            direction = rightTurnMap.get(direction);
        }

        curRow = row + directionArr[direction][0];
        curCal = cal + directionArr[direction][1];
        if(!(curRow >= 0 && curRow <= arrGrid.length-1))
        {
            if(!(curRow >= 0))
            {
                curRow = arrGrid.length-1;
            }
            if(!(curRow <= arrGrid.length-1))
            {
                curRow = 0;
            }

        }
        if(!(curCal >= 0 && curCal <= arrGrid[0].length-1))
        {
            //왼우,상하
            if(!(curCal >= 0))
            {
                curCal = arrGrid[0].length-1;
            }
            if(!(curCal <= arrGrid[0].length-1))
            {
                curCal = 0;
            }

        }
        dfs(direction,countNum+1,curRow,curCal);
    }


    void dfsLoop(int direction,int row,int cal)
    {

        int countNum = 0;
        int curRow = 0;
        int curCal = 0;
        int maxRow = arrGrid.length;
        int maxCal = arrGrid[0].length;
        while(true)
        {
            if(visit[direction][row][cal])
            {
                answer.add(countNum);
                break;
            }
            countNum++;
            //좌
            visit[direction][row][cal] = true;
            if(arrGrid[row][cal] == 2)
            {
                direction = this.leftTurnMap.get(direction);
            }

            //우
            if(arrGrid[row][cal] == 3)
            {
                direction = this.rightTurnMap.get(direction);
            }

            row = (row + directionArr[direction][0] + maxRow) % maxRow;
            cal = (cal + directionArr[direction][1] + maxCal) % maxCal;
            
            //범위 벗어날 경우
            
        }


    }


    public int[] solution(String[] grid) 
    {
        int row = grid.length;
        int cal = grid[0].length();
        visit = new boolean[4][row][cal];
        initGrid(grid);
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[i].length();j++)
            {
                for(int k=0;k<4;k++)
                {
                    if(!visit[k][i][j])
                    {
                        dfsLoop(k, i, j);
                    }
                }
            }

        }
        int[] tmpAnswer = answer.stream().sorted().mapToInt(i -> i).toArray();
        
        return tmpAnswer;
    }

    private void initGrid(String[] grid) 
    {
        leftTurnMap.put(0,3);
        leftTurnMap.put(1,2);
        leftTurnMap.put(2,0);
        leftTurnMap.put(3,1);
        rightTurnMap.put(0,2);
        rightTurnMap.put(1,3);
        rightTurnMap.put(2,1);
        rightTurnMap.put(3,0);
        Character curCh = ' ';

        arrGrid = new int[grid.length][grid[0].length() ];

        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[i].length();j++)
            {
                curCh = grid[i].charAt(j);
                int val = 1;
                if(curCh == 'L')
                {
                    val = 2;
                }
                if(curCh == 'R')
                {
                    val = 3;
                }
                arrGrid[i][j] = val;
            }
        }

    }
}