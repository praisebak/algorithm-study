class Solution {
    public long solution(int[] sequence) {
        long answer = 0;

        //dp[i][0] 곱했을때 아니면 -1로 하던가
        //-값 이하로가면 버린다
        
        long cur = 0;
        long curMul = 1;
        //1부터 시작하는걸로 해서
        for(int i=0;i<sequence.length;i++){
            long curVal = cur + (sequence[i] * curMul);
            if(curVal < 0){
                cur = 0;
            }else{
                cur = curVal;
                answer = Math.max(answer,cur);
            }
            curMul *= -1;
        }
        
        cur = 0;
        curMul = -1;
        //1부터 시작하는걸로 해서
        for(int i=0;i<sequence.length;i++){
            long curVal = cur + (sequence[i] * curMul);
            if(curVal < 0){
                cur = 0;
            }else{
                cur = curVal;
                answer = Math.max(answer,cur);
            }
            curMul *= -1;
        }
        
        return answer;
    }
}