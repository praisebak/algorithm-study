class Solution {

    private int[] info;

    int answer = 0;
    private int[][] edges;

    public int solution(int[] info, int[][] edges) {
        //이진트리를 직접 구현할필요없이 그냥 dfs로 풀면되지않을까?
        int N = info.length;
        this.info = info;
        this.edges = edges;

        visit = new boolean[N];
        dfs(0,visit,0,0);
        return answer;
    }

    boolean[] visit;
    private void dfs(int cur, boolean[] visit, int sheep, int wolf) {
        visit[cur] = true;
        if(info[cur] == 0){
            sheep++;
            answer = Math.max(answer,sheep);
        }else{
            wolf++;
        }
        if(wolf >= sheep) return;

        for(int[] e : edges){
            if(visit[e[0]] && !visit[e[1]]){
                boolean[] nextVisited = new boolean[visit.length];
                for(int i=0;i<visit.length;i++){
                    nextVisited[i] = visit[i];
                }
                dfs(e[1],nextVisited,sheep,wolf);
            }
        }
    }


}
