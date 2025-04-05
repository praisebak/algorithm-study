import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;

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
        String[] sArr = bufferedReader.readLine().split(" ");
        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(sArr[i-1]);
        }

        int[] dp = new int[N+1];
        for (int i = 0; i < N; i++) {
            for (int j = 1; i+j<= N; j++) {
                dp[i+j] = Math.max(dp[i+j],dp[i] + arr[j]);
            }
        }
        System.out.println(dp[N]);
    }
}
