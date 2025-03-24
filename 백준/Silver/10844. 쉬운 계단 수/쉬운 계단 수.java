
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve {
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        long[][] dp = new long[N+1][10];

        Arrays.fill(dp[1], 1);
        dp[1][0] = 0; // 0은 계단 수가 될 수 없음

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][1]; // 0은 1에서만 올 수 있음
            for (int j = 1; j <= 9; j++) {
                dp[i][j] = (dp[i-1][j-1] + (j < 9 ? dp[i-1][j+1] : 0)) % 1000000000;
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum = (sum + dp[N][i]) % 1000000000;
        }

        System.out.println(sum);
    }
}
