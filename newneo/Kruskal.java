package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;



class B1922_second {

    int N;
    int M;

    int[][] graph;
    int[] parent;
    public void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[M][3];
        parent = new int[N+1];
        for(int i=0;i<M;i++){
            String[] inputSplit = br.readLine().split(" ");
            int a = Integer.parseInt(inputSplit[0]);
            int b = Integer.parseInt(inputSplit[1]);
            int w = Integer.parseInt(inputSplit[2]);
            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = w;
        }
        int sum = 0;
        Arrays.sort(graph, (o1,o2) -> Integer.compare(o1[2],o2[2]));

        for(int i=0;i<=N;i++){
            parent[i] = i;
        }

        for(int i=0;i<M;i++){
            int a =graph[i][0]-1;
            int b =graph[i][1]-1;
            int weight = graph[i][2];
            if(find(a) != find(b)){
                union(a,b);
                sum += weight;
            }
        }
        System.out.println(sum);
    }

    public int find(int x ){
        if(parent[x] == x){
            return x;
        }else{
            return find(parent[x]);
        }
    }

    public void union(int a,int b){
        a = find(a);
        b = find(b);
        if(a > b){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }
}




