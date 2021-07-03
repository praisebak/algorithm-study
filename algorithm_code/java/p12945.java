import java.util.Arrays;

class Solution {
    int MAX = 100000+1;
    int dp[] = new int[MAX];
    int divNum = 1234567;
    int fibo(int n)
    {
        if(n == 0)
        {
            return 0;
        }
        if(n == 1)
        {
            return 1;
        }
        
        if(dp[n-1]== -1)
        {
            dp[n-1] = fibo(n-1) % divNum;
        }
        
        if(dp[n-2] == -1)
        {
            dp[n-2] = fibo(n-2) % divNum;
        }
        
        return (dp[n-2] + dp[n-1]) % divNum;
    }
    
    public int solution(int n) {
        int answer = 0;
        Arrays.fill(dp, -1);
        fibo(n);
        answer = dp[n];
        
        return answer;
    }
}