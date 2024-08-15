
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    class Node{
        int idx;
        int weight;
        public Node(int next,int weight){
            this.idx = next;
            this.weight = weight;
        }
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        List<List<Node>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<paths.length;i++){
            int cur = paths[i][0];
            int next = paths[i][1];
            int val = paths[i][2];


            //시작점에서 끝점으로 가는데 있어서 다시 돌아올 필요는 없음
            if(isSummit(summits,next) || isGates(gates,cur)){
                graph.get(cur).add(new Node(next,val));
            }else if(isGates(gates,next ) || isSummit(summits,cur)){
                graph.get(next).add(new Node(cur,val));
            }else{
                graph.get(cur).add(new Node(next,val));
                graph.get(next).add(new Node(cur,val));
            }
        }
        int[] dist = dijkstra(graph,gates,summits);


        int resultIdx = Integer.MAX_VALUE;
        int resultWeight = Integer.MAX_VALUE;


        Arrays.sort(summits);

        for(int i: summits){

            int curResultWeight = dist[i];
            if(resultWeight >  curResultWeight){
                resultWeight = curResultWeight;
                resultIdx = i;
            }
        }

        return new int[]{resultIdx,resultWeight};
    }

    private int[] dijkstra(List<List<Node>> graph, int[] gates, int[] summits) {
        PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.weight,o2.weight));
        int[] dist = new int[graph.size()];
        Arrays.fill(dist,Integer.MAX_VALUE);

        for(int g : gates){
            queue.add(new Node(g,0));
            dist[g] = 0;
        }


        while (!queue.isEmpty()){
            Node cur = queue.poll();

            int curDist = dist[cur.idx];
            if(cur.weight > dist[cur.idx]) continue;

            for(Node nextNode : graph.get(cur.idx)){
                int minVal = Math.max(nextNode.weight,dist[cur.idx]);
                if(minVal < dist[nextNode.idx]){
                    dist[nextNode.idx] = minVal;
                    queue.add(new Node(nextNode.idx,minVal));
                }
            }
        }

        return dist;
    }
    private boolean isGates(int[] gates, int next) {
        for(int i : gates){
            if(i == next)
                return true;
        }
        return false;
    }

    private boolean isSummit(int[] summit, int next) {
        for(int i : summit){
            if(i == next)
                return true;
        }
        return false;
    }

}
