import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    int N;
    int M;
    int[] arr;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr =  bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        arr = new int[N];
        dp = new int[N+1][M+1][3];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            arr[i] = num;
        }
        dfs(0,M,1);


        System.out.println(answer);
    }
    int answer = 0;
    int[][][] dp;

    private void dfs(int depth, int m,int curLoca) {
        answer = Math.max(dp[depth][m][curLoca],answer);
        if(depth == N) return;

        int cur = arr[depth];
        if(curLoca == cur){
            dp[depth][m][curLoca]++;
        }

        int curVal = dp[depth][m][curLoca];
        if(curVal >= dp[depth+1][m][curLoca]){
            dp[depth+1][m][curLoca] = dp[depth][m][curLoca];
            dfs(depth+1,m,curLoca);
        }

        if(cur != curLoca && m != 0){
            if((curVal+1) >= (dp[depth+1][m-1][cur])){
                dp[depth+1][m-1][cur] = dp[depth][m][curLoca]+1;
                dfs(depth+1,m-1,cur);
            }
        }

    }
}
