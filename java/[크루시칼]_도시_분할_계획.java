import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{

    private int n;
    private int m;
    private int[] parent;

    class Edge implements Comparable<Edge>{
        int a;
        int b;
        int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    List<Edge> edgeList = new ArrayList<>();
    boolean[] visit;
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(sArr[0]);
        m = Integer.parseInt(sArr[1]);
        visit = new boolean[n +1];
        for (int i = 0; i < m; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            int weight = Integer.parseInt(sArr[2]);
            edgeList.add(new Edge(a,b,weight));
        }
        Collections.sort(edgeList);
        kruscal();
    }

    private void kruscal() {
        parent = new int[n+1];
        for(int i = 0; i< parent.length; i++){
            parent[i] = i;
        }

        int sum = 0;
        int last = 0;
        for(Edge edge : edgeList){
            if(find(edge.a) != find(edge.b)){
                union(edge.a,edge.b);
                sum += edge.weight;
                last = edge.weight;
            }
        }
        sum -= last;
        System.out.println(sum);
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }

    private int find(int a) {
        if(parent[a] == a){
            return a;
        }
        return find(parent[a]);
    }
}
