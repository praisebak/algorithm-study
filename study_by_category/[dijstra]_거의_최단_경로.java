//https://www.acmicpc.net/status?user_id=praisebak&problem_id=5719&from_mine=1
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve =new Solve();
        solve.solve();

    }
}


class Solve{

    private boolean[][] visit;
    private List<List<Node>> nodeList;
    private int[] dist;
    private int n;
    private int m;
    private List<Integer>[] visitList;

    class Node{
        int idx;
        int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String[] sArr = bufferedReader.readLine().split(" ");
            n = Integer.parseInt(sArr[0]);
            m = Integer.parseInt(sArr[1]);
            if(n == 0 && m == 0){
                return;
            }
            sArr = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(sArr[0]);
            int end = Integer.parseInt(sArr[1]);
            dist = new int[n +1];
            nodeList = new ArrayList<>();
            visit = new boolean[n +1][n +1];
            visitList = new ArrayList[n + 1];

            for (int i = 0; i <= n; i++) {
                nodeList.add(new ArrayList<>());
                visitList[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                sArr = bufferedReader.readLine().split(" ");
                int cur = Integer.parseInt(sArr[0]);
                int next = Integer.parseInt(sArr[1]);
                int w = Integer.parseInt(sArr[2]);
                nodeList.get(cur).add(new Node(next,w));
            }
            //idx로 가는 최단
            dijstr(start,end);
            removeVisitRoute(start,end);;
            int result = dijstr(start,end);
            if(result == Integer.MAX_VALUE){
                result = -1;
            }
            System.out.println(result);
        }

    }

    private void removeVisitRoute(int start,int end) {
        if(start == end) return;
        for (int next : visitList[end]){
            if(visit[next][end]) continue;
            visit[next][end] = true;
            removeVisitRoute(start,next);
        }
    }

    private int dijstr(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>(((o1,o2) -> o1.weight - o2.weight));
        queue.add(new Node(start,0));
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;


        while (!queue.isEmpty() ){
            Node cur = queue.poll();
            //두번째 try때 갱신되는 경우
            if(cur.weight > dist[cur.idx]){
                continue;
            }
            for(Node next : nodeList.get(cur.idx)){
                if(visit[cur.idx][next.idx]){
                    continue;
                }
                if(dist[next.idx] > cur.weight + next.weight){
                    dist[next.idx] = cur.weight + next.weight;
                    visitList [next.idx] = new ArrayList<>();
                    visitList [next.idx].add(cur.idx);
                    queue.add(new Node(next.idx,dist[next.idx]));
                }else if(dist[next.idx] == cur.weight + next.weight){
                    visitList [next.idx].add(cur.idx);
                }
            }
        }
        return dist[end];
    }
}


















