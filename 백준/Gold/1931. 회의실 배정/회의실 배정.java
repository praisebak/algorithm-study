import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Node implements Comparable<Node>{
    int start;
    int end;
    public Node(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Node o) {
        if(o.end == this.end) return this.start - o.start;
        return this.end - o.end;
    }
}
class Solve{
    private int N;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(s);
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            list.add(new Node(a,b));
        }

        Collections.sort(list);

        int prev = 0;
        int count = 0;

        for(Node node : list){
            if(node.start >= prev){
                prev = node.end;
                count++;
            }
        }
        System.out.println(count);

    }

}