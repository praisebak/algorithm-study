import java.util.*;

class Solution {
    class Node{
        int left;
        int right;
        public Node(int left,int right){
            this.left=left;
            this.right=right;
        }
    }
    
    List<List<Integer>> graph = new ArrayList<>();
    int rl = 0;
    int rr = 1;
    int N;
    boolean[] visit;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        N = n;
        
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0;i<wires.length;i++){
            graph.get(wires[i][0]).add(wires[i][1]);
            graph.get(wires[i][1]).add(wires[i][0]);
        }
        
        //edge
        for(int i=0;i<n-1;i++){
            rl = wires[i][0];
            rr = wires[i][1];
            visit = new boolean[N+1];
            int left = 0;
            int right = 0;
            for(int j=1; j<=n;j++){
                left = bfs(j);
                break;
            }
            
            for(int j=1; j<=n;j++){
                if(visit[j]) continue;
                right = bfs(j);
                break;
            }
            
            
            answer = Math.min(answer,Math.abs(right - left));
            
        }
        
        return answer;
    }
    
    public int bfs(int start){
        
        Queue<Integer> que = new LinkedList<>();
        
        visit[start] = true;
        que.add(start);
        int sum = 1;
        
        while(!que.isEmpty()){
            int cur = que.poll();
            
            for(int next : graph.get(cur)){
                if(visit[next]) continue;
                if((cur == rl && next == rr) || cur == rr && next == rl) continue;
                que.add(next);
                visit[next] = true;   
                sum++;
            }
        }
        return sum;
    }
}