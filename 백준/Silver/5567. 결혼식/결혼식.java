import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    boolean[] visit;
    List<List<Integer>> nodes = new ArrayList<>();
    int answer = 0;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int M= Integer.parseInt(bufferedReader.readLine());
        visit = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int left = Integer.parseInt(sArr[0]);
            int right = Integer.parseInt(sArr[1]);
            nodes.get(left).add(right);
            nodes.get(right).add(left);
        }

        visit[1] = true;

        dfs(1,0);
        for(int i=2;i<=N;i++){
            if(visit[i]){
                answer++;
            }
        }
        System.out.println(answer);
    }

    private void dfs(int idx,int length) {
        if(length == 2){
            return;
        }
        for(int next : nodes.get(idx)){
            visit[next] = true;
            dfs(next,length+1);
        }
    }
}
