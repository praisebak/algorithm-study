
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[][] dp = new int[10001][4];
        dp[0][1] = 1;

        for (int i = 1; i <= 10000; i++) {
            dp[i][1] = dp[i-1][1];
            if(i >= 2){
                dp[i][2] = dp[i-2][1] + dp[i-2][2];
            }
            if(i >= 3){
                dp[i][3] = dp[i-3][3] + dp[i-3][2] + dp[i-3][1];
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            stringBuilder.append(dp[num][1] + dp[num][2] + dp[num][3] + "\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
