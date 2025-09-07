import java.util.*;

class Solution {
    class Node{
        int cur;
        int w;
        public Node(int cur,int w){
            this.cur=cur;
            this.w=w;
        }
    }
    
    List<List<Node>> graph = new ArrayList<>(); 
    int N;
     
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        this.N=N;
        
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0;i<road.length;i++){
            int l = road[i][0];
            int r = road[i][1];
            int w = road[i][2];

            graph.get(l).add(new Node(r,w));
            graph.get(r).add(new Node(l,w));
        }
        
        int[] dist = dij(1);
        

        for(int i=2;i<dist.length;i++){
            
            if(dist[i] > K) continue;
            
            answer++;
        }
        
        return answer+1;
    }
    
    public int[] dij(int start){
        int[] dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Node> que = new PriorityQueue<>((o1,o2) -> o1.w - o2.w);
        que.add(new Node(start,0));
        while(!que.isEmpty()){
            Node cur =que.poll();
            for(Node next : graph.get(cur.cur)){

                if(dist[next.cur] > cur.w + next.w){
                    dist[next.cur] = cur.w+next.w;
                    que.add(new Node(next.cur,dist[next.cur]));
                }
            } 
        }
        
        return dist;
    }
}