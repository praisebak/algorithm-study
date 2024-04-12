package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class B1922 {
    int N;
    int M;


    class Node{
        int v;
        int w;

        Node(int v,int w){
            this.v = v;
            this.w = w;
        }
    }


    int[] parent;
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[][] graph = new int[M][3];
        int cost = 0;

        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i] = i;
        }

        for(int i=0;i<M;i++){
            String s = br.readLine();
            String[] tmp =s.split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            int weight = Integer.parseInt(tmp[2]);
            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = weight;
        }

        Arrays.sort(graph,(o1,o2) -> Integer.compare(o1[2],o2[2]));

        for(int i=0;i<M;i++){
            if(find(graph[i][0]) != find(graph[i][1])){
                union(graph[i][0],graph[i][1]);
                cost += graph[i][2];
            }
        }
        System.out.println(cost);
    }

    public int find(int v){
        if(parent[v] == v) return v;
        else{
            return find(parent[v]);
        }
    }

    //통상적으로 작은값을 부모로둔다
    public void union(int a,int b){
        int parentA = find(a);
        int parentB = find(b);
        if(parentA > parentB){
            parent[parentA] = parentB;
        }else{
            parent[parentB] = parentA;
        }
    }


}




