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
        int[] arr = new int[N+1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }

        if(N == 1){
            System.out.println(arr[0]);
            return;
        }
        if(N == 2){
            System.out.println(arr[0] + arr[1]);
            return;
        }
        int[][] dp = new int[N+1][2];
        dp[1][0] = arr[0];
        dp[1][1] = arr[0];
        dp[2][0] = arr[1];
        dp[2][1] = arr[0] + arr[1];

        for (int i = 3; i <= N; i++) {
            dp[i][0] = dp[i-2][1] + arr[i-1];
            dp[i][1] = Math.max(dp[i][0],dp[i-1][0] + arr[i-1]);
        }
        System.out.println(Math.max(dp[N][0],dp[N][1]));
    }
}
