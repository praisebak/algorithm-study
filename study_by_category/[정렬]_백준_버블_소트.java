import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Node implements Comparable<Node>{
     int i;
     int val;
     public Node(int i, int val){
         this.i = i;
         this.val = val;
     }

     @Override
    public int compareTo(Node node){
         return val - node.val;
     }


}
class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    int N;
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt( bufferedReader.readLine());
        List<Node> nodeList = new ArrayList<>();
        for(int i=1;i<=N;i++){
            int val = Integer.parseInt(bufferedReader.readLine());
            nodeList.add(new Node(i,val));
        }
        Collections.sort(nodeList);
        int max = 0;
        for(int i=0;i<N;i++){
            Node cur = nodeList.get(i);
            max = Math.max(max,cur.i - i);
        }
        System.out.println(max);
        


    }
}
