import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solve{
    public void solve()throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        long[] dp = new long[1000001];
        dp[1]=  1;
        dp[2] = 2;
        dp[3] = 4;
        //111 12 21 3

        for (int j = 4; j <= 1000000 ; j++) {
            for (int i = 1; i <=3 ; i++) {
                dp[j] += dp[j-i];
            }
            dp[j] %= 1000000009;
        }

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            System.out.println(dp[num]);
        }

    }
}
class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}
