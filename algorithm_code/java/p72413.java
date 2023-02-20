import java.util.*;
public class p72413 {
    class Solution {

        class Node implements Comparable<Node>
        {
            int idx;
            int cost;
            Node(int idx,int cost)
            {
                this.idx = idx;
                this.cost = cost;
            }
            @Override
            public int compareTo(Node i){
                return this.cost - i.cost;
            }
        }

        ArrayList<ArrayList<Node>> list = new ArrayList<>();

        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = Integer.MAX_VALUE;

            for(int i=0;i<=n;i++)
            {
                list.add(new ArrayList<>());
            }

            for(int[] fare : fares)
            {
                int from = fare[0];
                int to = fare[1];
                int cost = fare[2];
                list.get(from).add(new Node(to,cost));
                list.get(to).add(new Node(from,cost));

            }

            int[] distA = new int[n+1];
            int[] distB = new int[n+1];
            int[] distS = new int[n+1];

            distA = dij(a,distA);
            distB = dij(b,distB);
            distS = dij(s,distS);

            for(int i=1;i<=n;i++)
            {
                answer = Math.min(answer,distA[i] + distB[i] + distS[i]);
            }
            return answer;
        }

        int[] dij(int start,int[] dist)
        {
            for(int i = 0; i <= dist.length-1; i++) 
                dist[i] = Integer.MAX_VALUE;

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(start,0));

            dist[start] = 0;

            while(pq.size() != 0)
            {

                Node node = pq.poll();
                //이미 변경된 값임
                if(node.cost > dist[node.idx])
                {
                    continue;
                }

                //인근 값 갱신
                for(Node adj : list.get(node.idx))
                {
                    if(adj.cost +  dist[node.idx] < dist[adj.idx])
                    {
                        dist[adj.idx] = adj.cost + dist[node.idx];
                        pq.offer(new Node(adj.idx,dist[adj.idx]));
                    }
                }
            }

            return dist;
        }
    }

    // class Solution 
    // {
    //     public int solution(int n, int s, int a, int b, int[][] fares) 
    //     {
    //         int[][] node = new int[n+1][n+1];
    //         int idx = 0;
    //         for(int i = 1; i < n + 1; i++) {
    //             for(int j = 1; j < n + 1; j++) {
    //                 node[i][j] = 20000001; //200 * 100000 + 1
    //             }
    //             node[i][i] = 0;
    //         }

    //         for(int i=0;i<fares.length;i++)
    //         {
    //             int from = fares[i][0];
    //             int to = fares[i][1];
    //             int dist = fares[i][2];
    //             node[from][to] = dist;
    //             node[to][from] = dist;
    //         }

                
            
    //         for (int k = 1; k <= n; k++) {
    //             for (int i = 1; i <= n; i++) {
    //                 for (int j = 1; j <= n; j++) {
    //                     if(node[i][j] > node[i][k] + node[k][j])
    //                     {
    //                         node[i][j] = node[i][k] + node[k][j];
    //                     }
    //                 }
    //             }
                
    //         }

    //         int min = Integer.MAX_VALUE;
    //         for(int i=1;i<=n;i++)
    //         {
    //             min = Math.min(min,node[s][i] + node[i][a] + node [i][b]);
    //         }



    //         int answer = min;
    //         return answer;
    //     }
    // }
}
