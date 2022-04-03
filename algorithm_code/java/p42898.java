class Solution {
    int div =  1000000007;
    int M =0,N=0;
    int[][] dp;
    int[] dx = {1,0};
    int[] dy = {0,1};

    public boolean isValid(int[][] puddles,int row,int cal)
    {
        if(row >= M || cal >= N)
        {
            return false;
        }
        
        if(row < 0 || cal < 0)
        {
            return false;
        }

        for(int i=0;i<puddles.length;i++)
        {
            if(puddles[i][0]-1 == row && puddles[i][1]-1 == cal)
            {
                return false;
            }
        }
        

        return true;
    }

    public int solution(int m, int n, int[][] puddles) 
    {
        dp = new int[m][n];
        M = m;
        N = n;
        
        //dfs(puddles,0,0);
        int answer = dp(puddles,m-1,n-1) % div;
        return answer;
    }

    private int dp(int[][] puddles, int i, int j) 
    {
        if(!isValid(puddles, i, j))
        {
            return 0;
        }
        if(dp[i][j] != 0)
        {
            return dp[i][j];
        }
        
        if(i == 0 && j == 0)
        {
            return 1;
        }

        
        
        dp[i][j] = ((dp(puddles,i-1,j)%div) + (dp(puddles,i,j-1)%div)%div);
        return dp[i][j];
    }

    private void dfs(int[][] puddles, int row, int cal) 
    {
        // System.out.print(row + "," + cal + " ++");
        // System.out.println();
        dp[row][cal] = dp[row][cal] + 1;
        for(int i=0;i<2;i++)
        {
            if(isValid(puddles, row + dy[i], cal + dx[i]))
            {
                dfs(puddles,row+dy[i],cal+dx[i]);
            }
        }
    }
}