package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


class MainB5972{
    public static void main(String[] args) throws IOException{
        B5972_third b5972Third = new B5972_third();
        b5972Third.solve();
    }
    
}
class B5972_third {
    class Node{
        int idx;
        int w;
        Node(int idx,int w){
            this.idx =idx;
            this.w = w;
        }
    }
    int N;
    int M;

    int[] dist;

    List<List<Node>> graph = new ArrayList<>();
    public void solve() throws IOException{
        BufferedReader bufferedReader =   new BufferedReader(new InputStreamReader(System.in));
        String[] splited = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());

        }
        for(int i=0;i<M;i++){
            splited = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(splited[0]);
            int b = Integer.parseInt(splited[1]);
            int w = Integer.parseInt(splited[2]);

            graph.get(a).add(new Node(b,w));
            graph.get(b).add(new Node(a,w));
        }

        dist = new int[N+1];

        for(int i=0;i<=N;i++){
            dist[i] = Integer.MAX_VALUE;
        }

        dist[1] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w,o2.w));
        priorityQueue.add(new Node(1,0));

        while (!priorityQueue.isEmpty()){
            Node cur = priorityQueue.poll();

            for(Node next : graph.get(cur.idx)){
                if(next.w + cur.w < dist[next.idx]){
                    dist[next.idx] = cur.w + next.w;
                    priorityQueue.add(new Node(next.idx,dist[next.idx]));
                }
            }
        }


        System.out.println(dist[N]);
    }
    
    

}




