class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        
        
        int[] dp = new int[sticker.length];
        
        if(sticker.length == 1) return sticker[0];
        
        dp[0] = sticker[0];
        dp[1] = 0;
        
        //0번째 스티커 뜯었으니 마지막은 못뜯는다
        for(int i=2;i<sticker.length-1;i++){
            dp[i] = Math.max(sticker[i] + dp[i-2],dp[i-1]);
        }
        
        answer = dp[sticker.length-2];
        dp = new int[sticker.length];
        
        dp[0] = 0;
        dp[1] = sticker[1];
        
        for(int i=2;i<sticker.length;i++){
            dp[i] = Math.max(sticker[i] + dp[i-2],dp[i-1]);
        }
        
        
        answer = Math.max(answer,dp[sticker.length-1]);

        return answer;
    }
}
