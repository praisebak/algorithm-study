import java.util.*;
class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        int[] dp = new int[n+1];
        
        dp[0] = 1;
        Arrays.sort(money);
        
        for(int i=0;i<money.length;i++){
            int m = money[i];
            for(int j=money[i];j<=n;j++){
                if(j > n) break;
                dp[j] += dp[j-m];
                dp[j] %= 1_000_000_007;
            }
        }
        
        return dp[n];
    }
}