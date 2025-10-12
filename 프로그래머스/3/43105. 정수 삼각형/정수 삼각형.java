class Solution {
    public int solution(int[][] triangle) {

        int n = triangle.length;
        int m = triangle[triangle.length-1].length;
        
        int[][] dp = new int[n][m];
        dp[0][0] = triangle[0][0];
        
        int answer = 0;
        for(int i=1;i<n;i++){
            for(int j=0;j<triangle[i].length;j++){
                int cur = triangle[i][j];
                int prev = 0;
                //자기자신 가능
                if(triangle[i-1].length > j){
                    prev = dp[i-1][j];
                }
                //자신 왼쪽가능
                if(j-1 >= 0){
                    prev = Math.max(dp[i-1][j-1],prev);
                }
                dp[i][j] = Math.max(dp[i][j],prev+cur);
                answer = Math.max(answer,dp[i][j]);
            }
        }
        return answer;
    }
}