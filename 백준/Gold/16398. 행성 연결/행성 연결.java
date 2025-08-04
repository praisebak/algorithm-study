import org.w3c.dom.Node;

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

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int[] parent;

    class Node{
        int next;
        int weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }


    //크루시칼은 현재,다음,가중치가 필요하다
    class Edge implements Comparable<Edge> {
        int cur;
        int next;
        int val;

        public Edge(int cur, int next, int val) {
            this.cur = cur;
            this.next = next;
            this.val = val;
        }

        @Override
        public int compareTo(Edge edge2){
            return val - edge2.val;
        }

    }

    long answer = 0;
    public void solve() throws IOException{
        List<Edge> graphs = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                graphs.add(new Edge(i,j,Integer.parseInt(sArr[j])));
            }
        }
        Collections.sort(graphs);

        parent = new int[N+1];
        for (int i = 0; i < parent.length ; i++) {
            parent[i] = i;
        }

        for(int i=0;i< graphs.size();i++){
            Edge edge =graphs.get(i);

            int cur = edge.cur;
            int next = edge.next;
            int val = edge.val;

            if(parent[cur] == parent[next]) continue;
            if(find(cur) != find(next)){
                comb(cur,next,val);
            }

        }
        System.out.println(answer);
    }

    private void comb(int cur, int next, int val) {
        int a = find(cur);
        int b = find(next);
        if(a < b){
            parent[a]=b;
        }else{
            parent[b]=a;
        }
        answer += val;
    }

    private int find(int x) {
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
