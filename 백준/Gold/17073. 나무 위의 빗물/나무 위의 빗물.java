
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private List<List<Integer>> tree;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int W = Integer.parseInt(sArr[1]);
        visit = new boolean[N+1];

        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++){
            sArr =bufferedReader.readLine().split(" ");
            int left = Integer.parseInt(sArr[0]);
            int right = Integer.parseInt(sArr[1]);
            tree.get(left).add(right);
            tree.get(right).add(left);
        }


        if(N == 1){
            System.out.println(W);
            return;
        }

        for(int i=2;i<=N;i++){
            if(tree.get(i).size() == 1){
                leaf++;
            }
        }

        if(leaf == 0){
            System.out.println(W);
            return;
        }

        System.out.println((double) W / leaf);
    }
    int leaf = 0;

    private void dfs(int idx) {
        int moveCount = 0;
        for(int next : tree.get(idx)){
            if(visit[next]) continue;
            visit[next] = true;
            moveCount++;
            dfs(next);
        }

        if(moveCount == 0){
            leaf++;
        }
    }

    boolean[] visit;
}
