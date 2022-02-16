import java.util.*;
import java.util.concurrent.CountDownLatch;


class Solution 
{
    //왼 오 하 상
    int x[] = {-1,1,0,0};
    int y[] = {0,0,-1,1};
    int minCount = 987654321;
    boolean checkVisit = false; 

    void dfs(int count,int[][] maps,int curX,int curY,boolean[][] visit)
    {
        Queue<Integer> rowQue = new LinkedList<>();
        Queue<Integer> calQue = new LinkedList<>();
        Queue<Integer> countQue = new LinkedList<>();
        int curRow = 0;
        int curCal = 0;
        int nextRow = 0;
        int nextCal = 0;
        int tmpCount = 0;
         
        rowQue.add(0);
        calQue.add(0);
        countQue.add(1);

        while(!rowQue.isEmpty())
        {
            curRow = rowQue.remove();
            curCal = calQue.remove();
            tmpCount = countQue.remove();
            if(curRow == maps.length-1 && curCal == maps[0].length-1)
            {
                minCount = Math.min(minCount,tmpCount);
                checkVisit = true;
                return;
            }

            for(int i=0;i<4;i++)
            {
                nextRow = curRow + x[i];
                nextCal = curCal + y[i];
                if(isValidMove(maps, nextRow,nextCal) && !visit[nextRow][nextCal] && maps[nextRow][nextCal] == 1)
                {
                    visit[nextRow][nextCal] = true;
                    rowQue.add(nextRow);
                    calQue.add(nextCal);
                    countQue.add(tmpCount+1);
                }
            }

        }


    }

    private boolean isValidMove(int[][] maps, int nextX, int nextY) 
    {
        if(nextX < 0 || nextY < 0) 
        {
            return false;
        }
        if(maps.length <= nextX || maps[0].length <= nextY)
        {
            return false;
        }

        return true;
    } 

    public int solution(int[][] maps) 
    {

        boolean[][] visit = new boolean[maps.length][maps[0].length];
        dfs(0,maps,0,0,visit);
        if(!checkVisit)
        {
            minCount = -1;
        }
        int answer = minCount;
        return answer;
    }

    public static void main(String[] args)
    {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        Solution solution = new Solution();

    }
}  
/*
dfs
class Solution 
{
    //왼 오 하 상
    int x[] = {-1,1,0,0};
    int y[] = {0,0,-1,1};
    int minCount = 987654321;
    boolean checkVisit = false; 

    void dfs(int count,int[][] maps,int curX,int curY,boolean[][] visit)
    {
        count++;
        int nextX = 0;
        int nextY = 0;
        if(curX == maps.length-1 && curY == maps[0].length-1)
        {
            minCount = Math.min(minCount, count);
            checkVisit = true;
            return;
        }
        for(int i=0;i<x.length;i++)
        {
            nextX = curX  + x[i];
            nextY = curY + y[i];    
            if(nextX<0 || nextY <0 || nextY >= maps.length || nextX>= maps[0].length) continue;
            if(maps[nextY][nextX] == 1 && !visit[nextY][nextX])
            {
                visit[nextY][nextX] = true;
                dfs(count,maps,nextX,nextY,visit);
                visit[nextY][nextX] = false;

            }
        

        }

    }

    private boolean isValidMove(int[][] maps, int nextX, int nextY) 
    {
        if(nextX < 0 || nextY < 0) 
        {
            return false;
        }
        if(maps.length <= nextY || maps[0].length <= nextX)
        {
            return false;
        }

        return true;
    } 

    public int solution(int[][] maps) 
    {

        boolean[][] visit = new boolean[maps.length][maps[0].length];
        dfs(0,maps,0,0,visit);
        if(!checkVisit)
        {
            minCount = -1;
        }
        int answer = minCount;
        return answer;
    }



*/