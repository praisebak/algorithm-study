import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Main{

    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    class Node implements Comparable<Node>{
        int start;
        int end;
        public Node(int start,int end){
            this.start= start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o){
            if(start == o.start) return end - o.end;
            return start - o.start;

        }
    }
    public void solve()throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(sArr[0]);
            int end = Integer.parseInt(sArr[1]);
            nodeList.add(new Node(start,end) );
        }
        Collections.sort(nodeList);

        int answer = 0;
        int prevEnd = Integer.MIN_VALUE;
        for (int i = 0; i < N ; i++) {
            Node cur= nodeList.get(i);

            if(cur.start >= prevEnd){
                answer++;
                prevEnd = cur.end;
            } else if(cur.end < prevEnd){
                prevEnd = cur.end;
            }
        }
        System.out.println(answer);
    }
}
