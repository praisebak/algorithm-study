import java.util.*;

//bfs로 푸는것과 dfs로 푸는 방식의 차이점이 뭘지

class Solution {
    int answer = 0;
    boolean[] visit;
    int[][] dungeons;
    public int solution(int k, int[][] dungeons) {

        this.dungeons = dungeons;
        visit = new boolean[dungeons.length];
        
        dfs(k,0); 

        return answer;
    }
    public void dfs(int leftTired,int depth){
        answer = Math.max(depth,answer);
        
        for(int i=0;i<dungeons.length;i++){
            if(visit[i]) continue;
            
            int needTired = dungeons[i][0];
            int consumeTired = dungeons[i][1];
            
            if(leftTired >= needTired && leftTired - consumeTired >= 0){
                visit[i] = true;
                dfs(leftTired - consumeTired,depth+1);
                visit[i] = false;
            }
        }
    }
}