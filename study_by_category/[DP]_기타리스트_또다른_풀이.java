import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{

    class Music{
        int v;
        int depth;

        public Music(int v, int depth) {
            this.v = v;
            this.depth = depth;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr =bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int S = Integer.parseInt(sArr[1]);
        int M = Integer.parseInt(sArr[2]);

        int[] arr = new int[N];
        sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }

        boolean[][] dp = new boolean[N+1][M+1];
        dp[0][S] = true;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if(dp[i-1][j]){
                    int up = j + arr[i-1];
                    int down = j - arr[i-1];
                    if(up <= M){
                        dp[i][up] = true;
                    }
                    if(down >= 0){
                        dp[i][down] = true;
                    }
                }
            }
        }

        for (int val = M; val >=0; val--) {
            if(dp[N][val]){
                System.out.println(val);
                return;
            }
        }
        System.out.println(-1);
    }
}
