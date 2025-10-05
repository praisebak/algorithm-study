class Solution {
    int[] stones;
    int k;
    public int solution(int[] stones, int k) {
        this.k=k;
        int answer = 0;
        this.stones = stones;
       
        int left = 1;
        int right = 200_000_000;
        
        while(left <= right){
            int mid = (left + right) / 2;
            
            if(isPossible(mid)){
                answer = Math.max(mid,answer);
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
       
        return answer;
    }
    
    public boolean isPossible(int mid){
        int skip = 0;
        for(int stone : stones){
            if(stone < mid){
                skip++;
            }else{
                skip = 0;
            }
            
            if(skip == k) return false;
        }
        return true;
    }
}
