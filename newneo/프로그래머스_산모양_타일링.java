import java.util.*;


class Solution {
    List<String> sList = new ArrayList<>();



    int answer = 0;

    int N;
    public int solution(int n, int[] tops) {
        int[] dp = new int[n+n+1];
        N = n * 2 +1;

        if(n == 1) return 3;
        dp[0] = 1;
        dp[1] = 2;

        if(tops[0] == 1) dp[1]++;


        for(int i=2;i<n+n+1;i++){

            if(i % 2 != 0 && tops[i / 2] == 1){
                dp[i] = dp[i-1] * 2 + dp[i-2];
            }else{
                dp[i] = dp[i-2] + dp[i-1];    
            }
            
            dp[i] = dp[i] % 10007;
                            

        }

        answer = dp[n+n];

        return answer % 10007;
    }




}
