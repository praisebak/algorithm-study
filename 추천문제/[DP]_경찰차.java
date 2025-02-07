import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int[][] map;
    private int N;
    private int M;
    private int[][] dp;

    class Problem{
        int r;
        int c;

        public Problem(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    List<Problem> problems = new ArrayList<>();
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        M = Integer.parseInt(bufferedReader.readLine());
        problems.add(new Problem(1,1));
        for (int i = 0; i < M; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            problems.add(new Problem(a,b));
        }
        map = new int[N +1][N +1];
        dp = new int[1001][1001];
        System.out.println(dfs(0,0,1));
        StringBuilder stringBuilder =  new StringBuilder();
        int oneIdx = 0;
        int twoIdx = 0;
        for (int i = 1; i <= M; i++) {
            int curDist = diff(0,oneIdx,problems.get(i));
            if(dp[oneIdx][twoIdx] - curDist == dp[i][twoIdx]){
                oneIdx = i;
                stringBuilder.append("1\n");
            }else{
                twoIdx = i;
                stringBuilder.append("2\n");
            }
        }
        System.out.println(stringBuilder.toString());
    }


    private int dfs(int one,int two,int depth) {
        if(depth == M+1){
            return 0;
        }

        if(dp[one][two] != 0){
            return dp[one][two];
        }

        Problem problem = problems.get(depth);

        int resultA = dfs(depth,two,depth+1) + diff(0,one,problem);
        int resultB = dfs(one,depth,depth+1) + diff(1,two,problem);
        return dp[one][two] = Math.min(resultA,resultB);
    }

    private int diff(int type, int idx, Problem problem) {
        Problem cur = problems.get(idx);
        if(type == 1){
            if(idx == 0){
                cur = new Problem(N,N);
            }
        }

        int dist = Math.abs(problem.r - cur.r) + Math.abs(problem.c - cur.c);
        return dist;
    }
}
