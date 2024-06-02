package newneo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijstraReplicant
{
    class Node{
        int idx;
        int weight;
        Node(int idx,int weight){
            this.idx = idx;
            this.weight = weight;
        }
    }
    public void solve() throws IOException{
        int N = 50;
        Scanner scanner = new Scanner(System.in);
        Node[][] nodeList = new Node[N][N];
        List<List<Node>> list = new ArrayList<>();
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<N;i++){
            for (int j = 0; j < N; j++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int w = scanner.nextInt();
                list.get(a).add(new Node(b,w));
                list.get(b).add(new Node(a,w));
            }
        }

        int[] dist = new int[N+1];
        for(int i = 0;i<N;i++){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0,0));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for(Node next : list.get(cur.idx)){
                if(dist[next.idx] > dist[cur.idx] + cur.weight){
                    dist[next.idx] = dist[cur.idx] + cur.weight;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            min = Math.min(min,dist[i]);
        }
        System.out.println(min);


    }

    public static void main(String[] args) throws IOException {
        DijstraReplicant dijstraReplicant = new DijstraReplicant();
        dijstraReplicant.solve();

    }
}
