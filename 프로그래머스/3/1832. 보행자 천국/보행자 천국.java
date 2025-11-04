class Solution {
    int MOD = 20170805;
    public int solution(int n, int m, int[][] cityMap) {
        int answer = 0;
        int[][][] dp = new int[n+1][m+1][2];
        
        for(int i=0;i<n;i++){
            if(cityMap[i][0] == 1) break;
            dp[i][0][1] = 1;
        }
        for(int i=0;i<m;i++){
            if(cityMap[0][i] == 1) break;
            dp[0][i][0] = 1;
        }
        
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(cityMap[i][j] == 1){
                    continue;
                }
                
               // 위에서 내려오는 경우 (↓)
        if(cityMap[i-1][j] == 0)
            dp[i][j][1] = (dp[i-1][j][0] + dp[i-1][j][1]) % MOD;
        else if(cityMap[i-1][j] == 2)
            dp[i][j][1] = dp[i-1][j][1] % MOD;

        // 왼쪽에서 오는 경우 (→)
        if(cityMap[i][j-1] == 0)
            dp[i][j][0] = (dp[i][j-1][0] + dp[i][j-1][1]) % MOD;
        else if(cityMap[i][j-1] == 2)
            dp[i][j][0] = dp[i][j-1][0] % MOD;
            }
        }
        
        return (dp[n-1][m-1][0] + dp[n-1][m-1][1]) % 20170805;
    }
}