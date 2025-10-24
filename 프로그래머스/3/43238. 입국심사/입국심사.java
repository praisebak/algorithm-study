class Solution {
    int n;
    int[] times;
    public long solution(int n, int[] times) {
        this.n=n; 
        this.times=times;
        
        //범위 설정이 잘못되었다
        long answer = Long.MAX_VALUE;
         
        long left = 0;
        long right = Long.MAX_VALUE / 2 -2;
        
        while(left <= right){
            long mid = ((long)left  +right) / 2;
            if(isPossible(mid)){
                right = mid-1;
                answer=  Math.min(answer,mid);
            }else{
                left = mid+1;
            }
        }
        
        
        return answer;
    }
    
    public boolean isPossible(long mid){
        long sum = 0;
        //계산이 잘못되었다
        for(int i=0;i<times.length;i++){
            sum += Math.floor(((long)mid / times[i]));
        }
        
        return n > sum ? false : true;
    }
}