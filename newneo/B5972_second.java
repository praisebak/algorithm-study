package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


class B5972_second {

    class Node{
        int idx;
        int weight;
        Node(int idx,int weight){
            this.idx = idx;
            this.weight = weight;
        }
    }

    int N;
    int M;
    //edge의 최소 합
    int[] dist;
    List<List<Node>> graph = new ArrayList<>();

    public void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitInput = br.readLine().split(" ");
        N = Integer.parseInt(splitInput[0]);
        M = Integer.parseInt(splitInput[1]);
        dist = new int[N+1];


        for(int i=0;i<N+1;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<N;i++){
            splitInput = br.readLine().split(" ");
            int a = Integer.parseInt(splitInput[0]);
            int b = Integer.parseInt(splitInput[1]);
            int w = Integer.parseInt(splitInput[2]);

            graph.get(a).add(new Node(b,w));
            graph.get(b).add(new Node(a,w));
        }

        for(int i=0;i<=N;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[1] = 0;
        dijstra();
        System.out.println(dist[N]);



    }

    private void dijstra() {
        PriorityQueue<Node> q= new PriorityQueue<>((o1,o2) -> Integer.compare(o1.weight,o2.weight));
        q.offer(new Node(1,0));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(Node next : graph.get(cur.idx)){
                if(next.weight + cur.weight < dist[next.idx]){
                    dist[next.idx] = next.weight + cur.weight;
                    q.offer(new Node(next.idx,dist[next.idx]));
                }
            }
        }


    }


}




