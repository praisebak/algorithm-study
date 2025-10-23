import java.util.*;

class Solution {
    class Node{
        int idx;
        int weight;
        public Node(int idx,int weight){
            this.idx=idx;
            this.weight=weight;
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
        
        List<List<Node>> list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }
        
        for(int i=0;i<roads.length;i++){
            int left = roads[i][0];
            int right = roads[i][1];
            list.get(left).add(new Node(right,1));
            list.get(right).add(new Node(left,1));
        }
        
        PriorityQueue<Node> que = new PriorityQueue<>((o1,o2) -> o1.weight - o2.weight);
        que.add(new Node(destination,0));
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        
        dist[destination] = 0;

        while(!que.isEmpty()){
            Node cur = que.poll();
            for(Node next : list.get(cur.idx)){
                if(next.weight + cur.weight < dist[next.idx]){
                    dist[next.idx] = next.weight+cur.weight;
                    que.add(new Node(next.idx,dist[next.idx]));
                }
            }
        }
        
        int[] result = new int[sources.length];
        
        for(int i=0;i<sources.length;i++){
            if(dist[sources[i]] == Integer.MAX_VALUE){
                result[i] = -1;
                continue;
            }
            result[i] = dist[sources[i]];
        }
        
        return result;
    }
}