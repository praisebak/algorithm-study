package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;





class B5972 {
    int N;
    int M;

    class Node{
        int idx;
        int w;
        Node(int idx, int w){
            this.w = w;
            this.idx = idx;
        }
    }
    List<List<Node>> graph = new ArrayList<>();
    int[] dist = new int[N+1];

    public void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        dist = new int[N+1];

        for(int i=0;i<=N;i++){
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0;i<N+1;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            tmp = br.readLine().split( " ");
            int cur = Integer.parseInt(tmp[0]);
            int next = Integer.parseInt(tmp[1]);
            int val = Integer.parseInt(tmp[2]);
            graph.get(cur).add(new Node(next,val));
            graph.get(next).add(new Node(cur,val));
        }

        dijstra();
        for(Integer v : dist){
            System.out.println(v);

        }

    }

    private void dijstra() {
        int start = 1;
        PriorityQueue<Node> que = new PriorityQueue<Node>((o1,o2) -> Integer.compare(o1.w,o2.w));

        que.offer(new Node(start,0));
        dist[start] = 0;
        while(!que.isEmpty()){
            Node cur = que.poll();

            if(dist[cur.idx] < cur.w){
                continue;
            }

            for(Node next : graph.get(cur.idx)){
                //최소값보다 작으면
                if(dist[next.idx] > next.w + cur.w){
                    dist[next.idx] = next.w + cur.w;
                    que.offer(new Node(next.idx,dist[next.idx]));
                }
            }
        }


    }


}



