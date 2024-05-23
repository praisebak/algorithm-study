
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        Queue<Node> queue = new LinkedList<>();

        for(int i=0;i<M;i++){
            splitStr = bufferedReader.readLine().split(" ");
            long K = Long.parseLong(splitStr[0]);
            int v = Integer.parseInt(splitStr[1]);
            queue.clear();
            queue.add(new Node(v,0));

            StringBuilder stringBuilder = new StringBuilder();
            boolean[] visit = new boolean[N+1];
            int answer = 0;

            while (!queue.isEmpty()){
                Node cur = queue.poll();
                for(Node next : graph.get(cur.idx)){
                    if(visit[next.idx]) continue;

                    if(next.weight > K){
                        queue.add(new Node(next.idx,next.weight));
                        visit[next.idx]  = true;
                        answer++;
                    }
                }
            }
            stringBuilder.append(answer + "\n");
            System.out.println(stringBuilder.toString());
        }


    }

    public static void main(String[] args) throws IOException {
        B15591_MooTube b15591MooTube = new B15591_MooTube();
        b15591MooTube.solve();
    }
}
