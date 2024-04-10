package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;



class Node{
    int weight;
    int idx;
    Node(int i,int w){
        this.idx = i;
        this.weight = w;
    }
}


/**
 * 다익스트라
 */
class B5972 {

    List<List<Node>> graph;
    int N;
    int M;
    int[] dist = new int[N+1];

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);

        graph = new ArrayList<>();
        for(int i=0;i<N+1;i++){
            graph.add(new ArrayList<>());
        }


        for(int i=0;i<M;i++){
            sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            int weight = Integer.parseInt(sArr[2]);
            graph.get(a).add(new Node(b,weight));
            graph.get(b).add(new Node(a,weight));
        }

        dist = new int[N+1];

        //다익스트라자
        dijkstra();
        System.out.println(dist[N]);
    }

    private void dijkstra() {
        int start = 1;
        int end = N;

        for(int i=0;i<=N;i++){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        q.offer(new Node(start,0));
        dist[start] = 0;
        while (!q.isEmpty()){
            Node cur = q.poll();

            if(dist[cur.idx] < cur.weight){
                continue;
            }

            for(Node nextNode : graph.get(cur.idx)){
                if(dist[nextNode.idx] > nextNode.weight + cur.weight){
                    dist[nextNode.idx] = nextNode.weight + cur.weight;
                    q.offer(new Node(nextNode.idx,dist[nextNode.idx]));
                }
            }
        }
    }
}

