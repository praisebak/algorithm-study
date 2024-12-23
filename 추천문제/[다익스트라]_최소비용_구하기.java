//https://www.acmicpc.net/problem/1916

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private List<List<Node>> graph;

    class Node{
        int next;
        int weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            int weight = Integer.parseInt(sArr[2]);
            graph.get(a).add(new Node(b, weight));
        }
        String[] sArr = bufferedReader.readLine().split(" ");
        int start = Integer.parseInt(sArr[0]);
        int end = Integer.parseInt(sArr[1]);

        dijstra(start,end);
    }

    private void dijstra(int start, int end) {
        PriorityQueue<Node> queue  = new PriorityQueue<>((o1,o2) -> o1.weight - o2.weight);
        int[] dist = new int[graph.size()];
        Arrays.fill(dist,Integer.MAX_VALUE);
        queue.add(new Node(start,0));
        dist[start] = 0;
        boolean[] visit = new boolean[graph.size()];
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int curIdx = cur.next;
            if(visit[curIdx]) continue;

            visit[curIdx] = true;
            for(Node next : graph.get(curIdx)){
                if(visit[next.next]) continue;
                if(dist[next.next] > next.weight + cur.weight){
                    dist[next.next] = next.weight + cur.weight;
                    queue.add(new Node(next.next,dist[next.next]));
                }
            }
        }
        System.out.println(dist[end]);
    }
}
