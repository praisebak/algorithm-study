import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}


class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String[] sArr = s.split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        int[] arr = new int[N+1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }


        int[][][] dp = new int[N+1][2][M+1];

        int oApple = arr[0] == 2 ? 1 : 0;
        int zApple = arr[0] == 1 ? 1 : 0;

        dp[0][0][M] = zApple;
        dp[0][1][M-1] = oApple;
        int answer = Math.max(dp[0][0][M],dp[0][1][M-1]);
        for (int i = 1; i < N; i++) {
            for (int move = 0; move <= M; move++) {
                int zeroApple = arr[i] == 2 ? 0 : 1;
                int oneApple = arr[i] == 2 ? 1 : 0;


                dp[i][1][move] = dp[i-1][1][move];
                dp[i][0][move] = dp[i-1][0][move];

                if(move+1 <= M){
                    dp[i][1][move] = Math.max(dp[i][1][move],dp[i-1][0][move+1]);
                    dp[i][0][move] = Math.max(dp[i][0][move],dp[i-1][1][move+1]);
                }

                dp[i][1][move] += oneApple;
                dp[i][0][move] += zeroApple;

                answer = Math.max(answer,dp[i][0][move]);
                answer = Math.max(answer,dp[i][1][move]);
            }
        }
        System.out.println(answer);
    }
}
