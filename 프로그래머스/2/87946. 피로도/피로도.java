class Solution {
    int answer = 0; 
    boolean[] visit;
    public int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        //dfs할건데, 순서 상관있고, 최대 depth 찾아내는 문제
        
        dfs(k,dungeons,0);
        return answer;
    }
    
    public void dfs(int leftHealth,int[][] dungeons,int depth){
        answer = Math.max(depth,answer);
        
        for(int i=0;i<dungeons.length;i++){
            if(visit[i]) continue;
            if(dungeons[i][0] > leftHealth) continue; 

            visit[i] = true;
            dfs(leftHealth - dungeons[i][1],dungeons,depth+1);
            visit[i] = false;
        }
    }
}