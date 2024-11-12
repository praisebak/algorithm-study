//https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&problemLevel=5&contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE&problemTitle=&orderBy=INQUERY_COUNT&selectCodeLang=ALL&select-1=5&pageSize=10&pageIndex=1&problemLevel=4%2C5&&&&&&&&&
import javax.swing.undo.CannotUndoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader=  new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(bufferedReader.readLine());

            Node[] nodeArr = new Node[N];
            for (int i = 0; i < N; i++) {
                nodeArr[i] = new Node(0,0);
            }

            String[] sArr= bufferedReader.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(sArr[i]);
                nodeArr[i].setX(x);
            }

            sArr= bufferedReader.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                int y = Integer.parseInt(sArr[i]);
                nodeArr[i].setY(y);
            }

            double weightRatio = Double.parseDouble(bufferedReader.readLine());

            //각 연결에서 생기는 edge 계산하기
            List<Edge> edgeList = new ArrayList<>();

            parent = new int[N];

            for (int i = 0; i < N; i++) {
                Node a = nodeArr[i];
                for (int j = i+1; j < N; j++) {

                    Node b = nodeArr[j];
                    double weight = Math.pow(a.y - b.y,2) + Math.pow(a.x-b.x,2);
                    edgeList.add(new Edge(i,j,weight));
                }
            }
            Collections.sort(edgeList, (o1,o2) -> Double.compare(o1.weight, o2.weight));
            System.out.println("#" + t + " " + Math.round(krucical(edgeList) * weightRatio));

        }
    }

    private double krucical(List<Edge> edgeList) {
        double result = 0.0;

        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }
        for(Edge edge: edgeList){
            int a = edge.a;
            int b = edge.b;
            if(find(a) == find(b)){
                continue;
            }
            union(a,b);
            result += edge.weight;
        }
        return result;
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a > b){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }

    int[] parent;
    private int find(int a) {
        if(parent[a] == a){
            return a;
        }
        return find(parent[a]);
    }
}
class Edge{
    int a;
    int b;
    double weight;

    public Edge(int a, int b, double weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
}

class Node{
    int y;
    int x;
    Node(int y,int x){
        this.y=y;
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }
    public void setX(int x){
        this.x=x;
    }
}
