import java.util.*;
class Solution {
    
    int MAX = Integer.MAX_VALUE;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n][m];
        for(int i=0;i< puddles.length;i++){
            int c = puddles[i][0]-1;
            int r = puddles[i][1]-1;
            dp[r][c] = MAX;
        }
        
        dp[0][0] = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(dp[i][j] == MAX) continue; 
                int sum = 0;
                //왼쪽보기
                if(j-1 >= 0 && dp[i][j-1] != MAX){
                    //i,j가 값이 있음
                    sum += dp[i][j-1];
                    sum %= 1000000007;
                }
                //위 보기
                if(i-1 >= 0 && dp[i-1][j] != MAX){
                    sum += dp[i-1][j];
                    sum %= 1000000007;
                }
                dp[i][j] += sum;
                dp[i][j] %= 1000000007;
            }
        }
        
        return dp[n-1][m-1];
    }
}