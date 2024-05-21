package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class B15591_MooTube {

    class Node{
        int idx;
        long weight;
        Node(int idx,long weight){
            this.idx = idx;
            this.weight = weight;
        }
    }

    int N;
    int M;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] splitStr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(splitStr[0]);
        M = Integer.parseInt(splitStr[1]);

        List<List<Node>> graph = new ArrayList<>();
        for(int i=0;i<=N;i++) graph.add(new ArrayList<>());

        for(int i=0;i<N-1;i++){
            splitStr = bufferedReader.readLine().split(" ");
            int p = Integer.parseInt(splitStr[0]);
            int q = Integer.parseInt(splitStr[1]);
            long r = Integer.parseInt(splitStr[2]);
            graph.get(p).add(new Node(q,r));
            graph.get(q).add(new Node(p,r));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o1.weight,o2.weight));

        for(int i=0;i<M;i++){
            splitStr = bufferedReader.readLine().split(" ");
            long K = Long.parseLong(splitStr[0]);
            int v = Integer.parseInt(splitStr[1]);
            queue.clear();

            queue.add(new Node(v,0));

            long[] dist = new long[N+1];

            Arrays.fill(dist,Long.MAX_VALUE);


            while (!queue.isEmpty()){
                Node cur = queue.poll();
                for(Node next : graph.get(cur.idx)){
                    if(next.idx == v) continue;

                    if(dist[next.idx] > cur.weight + next.weight){

                        if(cur.idx == v){
                            dist[next.idx] = next.weight;
                        }else{
                            dist[next.idx] = Math.min(cur.weight,next.weight);
                        }

                        queue.add(new Node(next.idx,dist[next.idx]));
                    }
                }
            }

            int answer = 0;
            for(int j =1;j<=N;j++){
                if(j == v) continue;
                if(dist[j] < K) continue;
                answer++;
            }

            System.out.println(answer);
        }


    }

    public static void main(String[] args) throws IOException {
        B15591_MooTube b15591MooTube = new B15591_MooTube();
        b15591MooTube.solve();
    }
}
