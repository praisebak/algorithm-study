import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{

    private int M;
    private int[] item;
    private int N;

    class Node{
        int next;
        int w;

        public Node(int next, int w) {
            this.next = next;
            this.w = w;
        }
    }
    List<List<Node>> nodeList = new ArrayList<>();
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        int R = Integer.parseInt(sArr[2]);
        item = new int[N +1];
        sArr = bufferedReader.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            item[i] = Integer.parseInt(sArr[i-1]);
        }
        for (int i = 0; i <= N; i++) {
            nodeList.add(new ArrayList<>());
        }
        for (int r = 0; r < R; r++) {
            sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            int w = Integer.parseInt(sArr[2]);
            nodeList.get(a).add(new Node(b,w));
            nodeList.get(b).add(new Node(a,w));
        }

        for (int i = 1; i <= N; i++) {
            bfs(i,0, item[i]);
        }

        System.out.println(answer);
    }

    private void bfs(int start, int weight, int initItem) {
        int curItem = 0;
        answer = Math.max(answer,curItem);
        boolean[] visit = new boolean[N+1];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        queue.add(new Node(start,0));

        int[] dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;


        visit[start] = true;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for(Node next : nodeList.get(cur.next)){
                if(dist[next.next] > cur.w + next.w){
                    if(visit[cur.next]) continue;
                    visit[cur.next] = true;
                    dist[next.next] = cur.w + next.w;
                    queue.add(new Node(next.next,dist[next.next]));
                }
            }
        }

        for (int i = 1;i<=N; i++) {
            if(dist[i] > M) continue;
            curItem += item[i];
        }
        answer = Math.max(curItem,answer);
    }


    int answer = 0;
}
