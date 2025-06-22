
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solve{

    class Node implements Comparable<Node>{
        int start;
        int end;


        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if(start == o.start) return end - o.end;
            return (start - (o.start));
        }
    }

    public void solve()throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int N = Integer.parseInt(s);
        List<Node> nodeList = new ArrayList<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int cur = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            Node curNode = new Node(cur,next);
            nodeList.add(curNode);
        }

        Collections.sort(nodeList);

        PriorityQueue<Integer> endTimeQueue = new PriorityQueue<>();
        endTimeQueue.add(nodeList.get(0).end);

        for(int i=1;i<N;i++){
            Node cur = nodeList.get(i);
            if( endTimeQueue.peek() <= cur.start) {
                endTimeQueue.poll();
            }
            endTimeQueue.add(cur.end);
        }
        System.out.println(endTimeQueue.size());

    }



}

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();

        solve.solve();
    }
}
