class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        
        //현재 시점에서 현재껄 뜯냐 안뜯냐로 나뉨
        //뜯는다면 이전거에서 안뜯는거에서 더해야하고 뜯지않는다면 최대 갱신해주면됨

        if(sticker.length == 1){
            return sticker[0];
            
        }
        
        int[][] dp = new int[sticker.length+1][2];
        dp[0][1] = sticker[0];
        dp[1][0] = sticker[0];
        
        for(int i=2;i<sticker.length-1;i++){
            //현재걸 안뜯는 선택지 = 이전거 뜯는애 or 이전거 안뜯는 선택지중 베스트
            dp[i][0] = Math.max(dp[i-1][1],dp[i-1][0]);
            //현재걸 뜯는 선택지 = 
            dp[i][1] = Math.max(dp[i-1][0] + sticker[i],dp[i-2][1] + sticker[i]);
        }
        
        answer = Math.max(dp[sticker.length-2][0],dp[sticker.length-2][1]);

        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = sticker[1];

        for(int i=2;i<sticker.length;i++){
            //현재걸 안뜯는 선택지 = 이전거 뜯는애 or 이전거 안뜯는 선택지중 베스트
            dp[i][0] = Math.max(dp[i-1][1],dp[i-1][0]);
            //현재걸 뜯는 선택지 = 
            dp[i][1] = Math.max(dp[i-1][0] + sticker[i],dp[i-2][1] + sticker[i]);
        }
        
        answer = Math.max(dp[sticker.length-1][0],answer);
        answer = Math.max(dp[sticker.length-1][1],answer);
        
        return answer;
    }
}