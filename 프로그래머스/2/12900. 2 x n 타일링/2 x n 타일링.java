class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int[] dp = new int[60001];
        
        dp[0] = 1;
        dp[1] = 1;
        
        //dp[2] = 1 dp[2] = 1 + 1 + 0
        
        //dp[3] = 
        for(int i=2;i<=n;i++){
            //2개짜리 에 한개짜리 더한거
            dp[i] = dp[i-2];
            dp[i] = (dp[i] + dp[i-1]) % 1000000007;
        }
        
        return (dp[n]) % 1000000007;
    }
}