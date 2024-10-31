import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[][] dp = new int[N][N];
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < sArr.length; j++) {
                arr[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i],-1);
        }
        dp[0][0] = arr[0][0];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(dp[i-1][j] != -1){
                    dp[i][j] = Math.max(dp[i-1][j] + arr[i][j],dp[i][j]);
                }

                if(j != 0 && dp[i-1][j-1] != -1){
                    dp[i][j] = Math.max(dp[i-1][j-1] + arr[i][j],dp[i][j]);
                }
            }
        }

        int answer = 0;
        for (int j = 0; j < N; j++) {
            answer = Math.max(answer,dp[N-1][j]);
        }
        System.out.println(answer);
    }
}
