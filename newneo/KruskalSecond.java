package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;



class B1922ThirdMain{
    public static void main(String[] args) throws IOException{
        KruskalSecond kruskalSecond = new KruskalSecond();
        kruskalSecond.solve();


    }
}


class KruskalSecond {
    private int N;
    private int M;

    int[][] graph;

    int[] parent;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(bufferedReader.readLine());
        M = Integer.parseInt(bufferedReader.readLine());

        graph = new int[M][3];

        for(int i=0;i<M;i++){
            String[] sSplit = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sSplit[0]);
            int b = Integer.parseInt(sSplit[1]);
            int w = Integer.parseInt(sSplit[2]);

            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = w;
        }

        parent = new int[N+1];
        for(int i=0;i<=N;i++){
            parent[i] = i;
        }
        Arrays.sort(graph,((o1, o2) -> Integer.compare(o1[2],o2[2])));

        int sum = 0;

        for(int i=0;i<M;i++){
            int a = graph[i][0]-1;
            int b = graph[i][1]-1;
            int w = graph[i][2];

            if(find(a) != find(b)){
                sum += w;
                union(a,b);
            }
        }


        System.out.println(sum);
    }


    public int find(int x){
        if(x == parent[x]) return x;
        else{
            return find(parent[x]);
        }
    }


    public void union(int a,int b){
        a = find(a);
        b = find(b);
        //더 작은애가 부모가됨
        if(a > b){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }

}




