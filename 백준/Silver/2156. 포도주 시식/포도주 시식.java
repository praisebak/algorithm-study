
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
            int num = Integer.parseInt(bufferedReader.readLine());
            arr[i] = num;
        }
        //안먹고 바로 왼쪽 먹고 두개 왼쪽 먹고
        int[][] dp = new int[N+1][3];
        dp[0][1] = arr[0];
        dp[0][2] = arr[0];
        dp[1][0] = arr[0];
        dp[1][1] = arr[0] + arr[1];
        dp[1][2] = arr[1];

        for (int i = 2; i < N; i++) {
            dp[i][0] = Math.max(dp[i-1][1],dp[i-2][1]);
            //바로 왼쪽거먹기
            dp[i][1] = Math.max(dp[i-1][0],dp[i-1][2]) + arr[i];
            //2개 왼쪽거먹기
            dp[i][2] = Math.max(Math.max(dp[i-2][0],dp[i-2][1]),dp[i-2][2]) + arr[i];
        }
        System.out.println(Math.max(Math.max(dp[N-1][0],dp[N-1][1]),dp[N-1][2]));
    }
}
