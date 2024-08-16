

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int goalAlgo = 0;
        int goalCode = 0;
        for(int i=0;i<problems.length;i++){
            goalAlgo = Math.max(problems[i][0],goalAlgo);
            goalCode = Math.max(problems[i][1],goalCode);
        }

        int[][] dp = new int[goalAlgo+2][goalCode+2];

        if(alp >= goalAlgo && cop >= goalCode){
            return 0;
        }

        if(alp >= goalAlgo){
            alp = goalAlgo;
        }

        if(cop >= goalCode){
            cop = goalCode;
        }

        for(int i=0;i<dp.length;i++){
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 0;

        for(int i=alp;i<=goalAlgo;i++){
            for (int j = cop; j <= goalCode; j++) {
                //알고 공부
                dp[i+1][j] = Math.min(dp[i+1][j],dp[i][j]+1);
                //코드 공부
                dp[i][j+1] = Math.min(dp[i][j+1],dp[i][j]+1);

                for(int idx = 0;idx < problems.length;idx++){
                    int[] problem = problems[idx];

                    if(i >= problem[0] && j >= problem[1]){
                        int nextAlgo = Math.min(i + problem[2],goalAlgo);
                        int nextCode = Math.min(j + problem[3],goalCode);
                        dp[nextAlgo][nextCode] = Math.min(dp[i][j] + problem[4],dp[nextAlgo][nextCode]);
                    }
                }
            }
        }






        return dp[goalAlgo][goalCode];
    }
}
