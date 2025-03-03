import javax.swing.border.LineBorder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());
        int num = 0;
        boolean[] fixed = new boolean[41];
        for (int i = 0; i < M; i++) {
            num = Integer.parseInt(bufferedReader.readLine());
            fixed[num] = true;
        }
        int[][] dp = new int[41][2];
        dp[1][0] = 1;
        dp[0][0] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            if(fixed[i] || fixed[i-1]) continue;
            dp[i][1] = dp[i-2][0] + dp[i-2][1];
        }
        System.out.println(dp[N][0] + dp[N][1]);
    }
}
