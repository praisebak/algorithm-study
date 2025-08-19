class Solution {
    int solution(int[][] land) {


        int[][] dp = new int[land.length][4];
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];
        
        for(int i=1;i<land.length;i++){
            //같은 열을 안밟게해서 최대값 계산해서 출력하면됨

            //이전에서 참고할곳
            for(int j=0;j<4;j++){
                //현재 넣을곳
                for(int k=0;k<4;k++){
                    if(j == k) continue;
                    dp[i][k] = Math.max(dp[i][k],dp[i-1][j] + land[i][k]);
                }   
            }
            
            
        }
        
        int answer = 0;
        for(int i=0;i<4;i++){
            answer=  Math.max(answer,dp[land.length-1][i]);
        }
        
        return answer;
    }
}