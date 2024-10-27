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
    public void solve()throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[][] arr = new int[N+1][3];
        int[][] dp = new int[1001][3];
        for (int i = 1; i <=N ; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(sArr[j]);
            }
        }
        for (int i = 0; i < 1001; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 1; i <=N ; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if(j == k) continue;
                    dp[i][k] = Math.min(dp[i][k],dp[i-1][j] + arr[i][j]);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <3 ; i++) {
            answer = Math.min(dp[N][i],answer);
        }
        System.out.println(answer);
    }
}
