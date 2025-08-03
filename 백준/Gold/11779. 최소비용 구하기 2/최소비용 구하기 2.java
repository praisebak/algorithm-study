import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main{

    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private List<List<Node>> graphs;
    private int N;
    private int M;

    class Node{
        int idx;
        long weight;

        public Node(int idx, long weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        M = Integer.parseInt(bufferedReader.readLine());

        graphs = new ArrayList<>();

        for (int i = 0; i <= N ; i++) {
            graphs.add(new ArrayList<>());
        }
        String[] sArr;
        for (int i = 0; i < M; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(sArr[0]);
            int end = Integer.parseInt(sArr[1]);
            int weight = Integer.parseInt(sArr[2]);

            graphs.get(start).add(new Node(end,weight));
        }

        sArr = bufferedReader.readLine().split(" ");
        int start = Integer.parseInt(sArr[0]);
        int end = Integer.parseInt(sArr[1]);

        dij(start,end);
    }

    private void dij(int start, int end) {
        int[] traces = new int[N+1];
        long[] dist = new long[N+1];
        Arrays.fill(dist,Long.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> {
            return Math.toIntExact(o1.weight - o2.weight);
        });

        dist[start] = 0;

        queue.add(new Node(start,0));

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.idx == end){
                break;
            }
            for(Node next : graphs.get(cur.idx)){
                if(dist[next.idx] > cur.weight + next.weight){
                    dist[next.idx] = cur.weight + next.weight;
                    traces[next.idx] = cur.idx;
                    queue.add(new Node(next.idx,dist[next.idx]));
                }
            }
        }


        System.out.println(dist[end]);

        int next = end;
        int count = 1;

        StringBuilder stringBuilder = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        while (next != start){
            count++;
            list.add(next);
            next = traces[next];
        }

        stringBuilder.append(start).append(" ");
        for(int i=list.size()-1;i>=0;i--){
            stringBuilder.append(list.get(i)).append(" ");
        }




        System.out.println(count);
        System.out.println(stringBuilder);
    }
}
