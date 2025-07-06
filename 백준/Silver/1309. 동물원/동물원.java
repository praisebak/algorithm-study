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


        int[][][] dp = new int[100001][2][2];
        dp[0][0][1] = 1;
        dp[0][0][0] = 1;
        dp[0][1][1] = 1;

        for (int i = 1; i <= N; i++) {
            dp[i][1][1] = (dp[i-1][0][1] + dp[i-1][1][0]) % 9901;
            dp[i][0][1] = (dp[i-1][0][0] + dp[i-1][1][1]) % 9901;
            //i번째에 왼쪽에 설치안하는 케이스..?
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][1][0] + dp[i-1][1][1]) % 9901;
            //i번째에 오른쪽에 설치안하는 케이스..?
            dp[i][0][1] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][1][0] + dp[i-1][1][1]) % 9901;
        }

        System.out.println(dp[N][0][0] );


    }
}
