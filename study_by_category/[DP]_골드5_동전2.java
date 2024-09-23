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
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int K = Integer.parseInt(sArr[1]);

        int[] arr = new int[N+1];
        int[] dp = new int[100001];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Arrays.fill(dp,Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 1; i <= K ; i++) {
            for (int j = 1; j <= N; j++) {
                if(i >= arr[j]){
                    if(dp[i-arr[j]] == Integer.MAX_VALUE) continue;
                    dp[i] = Math.min(dp[i-arr[j]]+1,dp[i]);
                }
            }

        }

        System.out.println(dp[K] == Integer.MAX_VALUE ? -1 : dp[K]);

    }
}
