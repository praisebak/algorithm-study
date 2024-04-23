package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;


class Main{

    public static void main(String[] args) throws IOException{
        B1238 b1238 = new B1238();
        b1238.solve();
    }
}


class B1238 {

    class Node{

        int idx;
        int weight;
        Node(int idx,int weight){
            this.idx = idx;
            this.weight =weight;
        }
    }
    int N;
    int M;
    int X;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sTmp = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(sTmp[0]);
        M = Integer.parseInt(sTmp[1]);
        X = Integer.parseInt(sTmp[2]);

        ArrayList<ArrayList<Node>> go = new ArrayList<>();
        ArrayList<ArrayList<Node>> from = new ArrayList<>();

        for(int i=0;i<N+1;i++){
            go.add(new ArrayList<>());
            from.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            sTmp = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sTmp[0]);
            int b = Integer.parseInt(sTmp[1]);
            int w = Integer.parseInt(sTmp[2]);

            go.get(a).add(new Node(b,w));
            from.get(b).add(new Node(a,w));
        }

        int sum = 0;
        int[] distA = dijstra(go,X);
        int[] distB = dijstra(from,X);
        for(int i = 1;i<=N;i++){
            sum = Math.max(sum,distA[i] + distB[i]);
        }

        System.out.println(sum);
    }

    private int[] dijstra(ArrayList<ArrayList<Node>> graph,int start) {

        int[] dist = new int[N+1];
        for(int i=1;i<=N;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.weight,o2.weight));
        pq.add(new Node(start,0));

        while(pq.size() != 0){
            Node cur = pq.poll();

            for(Node next : graph.get(cur.idx)){
                if(next.weight + cur.weight < dist[next.idx]){
                    dist[next.idx] = next.weight + cur.weight;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        return dist;
    }

}



