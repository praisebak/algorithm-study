class Solution {
    int minVal = Integer.MAX_VALUE; 
    //좌우상하
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,1,-1};
    int[][][] dp;
    String str = "좌우하상";
    boolean[][] visit;



    public int solution(int[][] board) {
        int n = board.length;
        dp = new int[n][n][2];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                dp[i][j][0] = Integer.MAX_VALUE; 
                dp[i][j][1] = Integer.MAX_VALUE; 
            }
        }

        //좌우,상하로 나뉘어 계산
        visit = new boolean[n][n];
        dfs(board,0,0,0,0);
        return Math.min(dp[n-1][n-1][0],dp[n-1][n-1][1]) * 100;
    }

    void dfs(int [][] board,int cost,int curX,int curY,int isHorizontality)
    {
        int n = board.length-1;
        if(curX == n && curY == n)
        {
            return;
        }

        for(int i=0;i<4;i++)
        {
            int nextDx = dx[i] + curX;
            int nextDy = dy[i] + curY;

            if(isValid(board,i,nextDx,nextDy) && !visit[nextDy][nextDx])
            {
                int tmpCost = cost;
                int curHorizontality = i <= 1 ? 1 : 0;

                //시작점아닌 경우
                if(cost != 0)
                {
                    //코스트 추가
                    if(isHorizontality != curHorizontality)
                    {
                        tmpCost += 5;
                    }
                }
                
                ++tmpCost;
                if(dp[nextDy][nextDx][curHorizontality] < tmpCost)
                {
                    continue;
                }
                dp[nextDy][nextDx][curHorizontality] = tmpCost;

                visit[nextDy][nextDx] = true;
                dfs(board,tmpCost,nextDx,nextDy,curHorizontality);
                visit[nextDy][nextDx] = false;
            }
        }

    }

    boolean isValid(int[][] board,int idx,int nextX,int nextY)
    {
        if(board.length <= nextY || board.length <= nextX)
        {
            return false;
        }

        if(0 > nextY || 0 > nextX)
        {
            return false;
        }

        if(board[nextY][nextX] == 1)
        {
            return false;
        }

        return true;
    }


}