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
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int K = Integer.parseInt(sArr[1]);

        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }

        int[] dp = new int[K+1];

        dp[0] = 1;
        for (int idx = 1; idx <= N; idx++) {
            int curVal = arr[idx];
            for (int cur = curVal; cur <= K; cur++) {
                dp[cur] = dp[cur-curVal] + dp[cur];
            }

        }
        System.out.println(dp[K]);

    }
}