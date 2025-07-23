class Solution {
    int answer = 0;
    int n;
    public int solution(int n) {
        this.n = n;
        for(int i=1;i<=n;i++){
            dfs(i,0);
        }

        
        return answer;
    }
    
    public void dfs(int curNum,int sum){
        if(sum >= n|| curNum > n){
            if(sum == n){
                answer++; 
            }
            return;
        }
        dfs(curNum+1,sum +curNum);
    }
}