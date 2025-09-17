import java.util.*;
class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        
        int[][] dp = new int[board.length][board[0].length];
        
        
        for(int i=0;i<board.length;i++ ){
            for(int j=0;j<board[0].length;j++){
                dp[i][j] = board[i][j];
                if(dp[i][j] == 1){
                    answer = 1;
                }
            }
        }
        
        
        
        for(int i=1;i<board.length;i++ ){
            for(int j=1;j<board[0].length;j++){
                if(dp[i][j] != 0 && dp[i-1][j-1] != 0&& dp[i][j-1] != 0 && dp[i-1][j] != 0){
                    int prevNum = Math.min(dp[i-1][j],dp[i-1][j-1]);
                    prevNum = Math.min(prevNum,dp[i][j-1]);
                    dp[i][j] = Math.max(dp[i][j],prevNum+1);
                    answer = Math.max(dp[i][j],answer);
                }
            }
        }


        return answer * answer;
    }
}