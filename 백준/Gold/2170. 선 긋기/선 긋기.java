import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Node implements Comparable<Node> {
    int start;
    int next;
    public Node(int start, int next){
        this.start = start;
        this.next = next;
    }

    @Override
    public int compareTo(Node o){
        if(start - o.start == 0){
            return next - o.next;
        }
        return start - o.start;
    }

}

class Solve{



    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int N = Integer.parseInt(s);

        List<Node> nodeList = new ArrayList<>();
        for(int i=0;i<N;i++){
            s = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(s, " ");

            int cur = Integer.parseInt(stringTokenizer.nextToken());
            int next = Integer.parseInt(stringTokenizer.nextToken());
            Node node = new Node(cur,next);
            nodeList.add(node);
        }

        Collections.sort(nodeList);

        long answer = nodeList.get(0).next - nodeList.get(0).start;
        int prev = nodeList.get(0).next;
        for(int i=1;i<N;i++){
            Node cur = nodeList.get(i);

            if(prev <= cur.start){
                answer += Math.abs(cur.next - cur.start);
                prev = cur.next;
            }else{
                if(cur.next > prev){
                    answer += Math.abs(cur.next - prev);
                    prev = cur.next;
                }
            }
        }
        System.out.println(answer);
    }
}