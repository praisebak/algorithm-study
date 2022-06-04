class Solution {
    public int solution(int n, int[] money) 
    {
        long[] dp = new long[n+1];
        for(int i=0;i<=n;i+=money[0])
        {
            dp[i] = 1;
        }

        for(int i=1;i<=money.length-1;i++)
        {
            for(int j=money[i];j<=n;j++)
            {
                dp[j] += dp[j-money[i]];
            }
        }

        return (int)dp[n] % 1000000007;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(5,new int[] {1,2,5});
    }

}