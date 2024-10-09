import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    List<List<Integer>> graph = new ArrayList<>();

    public void solve()throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            graph = new ArrayList<>();
            int N = Integer.parseInt(bufferedReader.readLine());
            int M = Integer.parseInt(bufferedReader.readLine());
            boolean[] visit = new boolean[N+1];
            for (int j = 0; j <= N ; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < M; j++) {
                String[] sArr = bufferedReader.readLine().split(" ");
                int a = Integer.parseInt(sArr[0]);
                int b= Integer.parseInt(sArr[1]);
                graph.get(a).add(b);
                graph.get(b).add(a);
            }



            boolean flag = false;
            for (int j = 1; j <= N; j++) {
                if(visit[j]) continue;
                if(j != 1){
                    flag = true;
                    break;
                }
                boolean isCycleExists = dfs(j,visit,j);
                if(isCycleExists) {
                    flag = true;
                    break;
                }
            }
            if(flag) System.out.println("graph");
            else System.out.println("tree");

        }

    }

    private boolean dfs(int start, boolean[] visit,int parent) {
        visit[start] = true;
        boolean result = false;
        for(int next : graph.get(start)){
            if(next == parent) continue;

            if(visit[next]) {
                return true;
            }
            visit[next] = true;
            result = dfs(next,visit,start);
            if(result) return true;
        }
        return false;
    }

}
