class Solution {
    int MOD = 20170805;
    int count = 0;
    int[] dx = {1,0};
    int[] dy = {0,1};
    int M = 0;
    int N = 0;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;

        int[][][] dp = new int[m+1][n+1][2];
        dp[1][1][0] = 1;
        dp[1][1][1] = 1;

        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(cityMap[i-1][j-1] == 1)
                {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                }
                else if(cityMap[i-1][j-1] == 0)
                {
                    dp[i][j][0] += ((dp[i][j-1][0]) + (dp[i-1][j][1])) % MOD;
                    dp[i][j][1] += ((dp[i][j-1][0]) + (dp[i-1][j][1])) % MOD;
                }
                else
                {
                    dp[i][j][0] += dp[i][j-1][0] % MOD;
                    dp[i][j][1] += dp[i-1][j][1] % MOD;
                }
            }
        }

        return dp[m][n][1];

    }

    private void dfs(int r,int c, int prev,int[][] cityMap,boolean[][] visit) 
    {
        if(r == M-1 && c == N-1)
        {  
            count = (count + 1) % MOD;
            // System.out.println("도착!");
            return;
        }

        for(int i=0;i<2;i++)
        {  
            int cR = r + dy[i];
            int cC = c + dx[i];

            if(!isValid(cR,cC)) continue;
            if(cityMap[cR][cC] == 1) continue;
            if(prev !=- 1 && cityMap[r][c] == 2)
                if(prev != i) continue;
            

            // System.out.println(r + "," + c + " -> " + cR + "," + cC + " :" + (i == 0 ? "오른쪽" : "아래"));
            dfs(cR,cC,i,cityMap,visit);
        }
    }

    private boolean isValid(int cR, int cC) {
        if(cR >= M || cC >= N || cR < 0 || cC < 0 ) return false;
        return true;
    }


}