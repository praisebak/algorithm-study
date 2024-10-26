import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{
    long[][] dp;
    int[] bike;
    int[] bikeMoney;
    int[] walk;
    int[] walkMoney;
    private int N;
    private int TIME;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr= bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        TIME = Integer.parseInt(sArr[1]);

        bike = new int[N+1];
        bikeMoney = new int[N+1];
        walk = new int[N+1];
        walkMoney = new int[N+1];
        for (int i = 1; i <=  N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            bike[i] = Integer.parseInt(sArr[0]);
            bikeMoney[i] = Integer.parseInt(sArr[1]);
            walk[i] = Integer.parseInt(sArr[2]);
            walkMoney[i] = Integer.parseInt(sArr[3]);
        }
        dp = new long[N+1][TIME+1];
        dp[1][bike[1]] = bikeMoney[1];
        dp[1][walk[1]] = Math.max(dp[1][walk[1]],walkMoney[1]);
        long answer = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= TIME; j++) {
                if(dp[i-1][j] == 0) continue;
                if(walk[i] + j <= TIME){
                    dp[i][j + walk[i]] = Math.max(dp[i][walk[i] + j],dp[i-1][j] + walkMoney[i]);
                }
                if(bike[i] + j <= TIME){
                    dp[i][j + bike[i]] = Math.max(dp[i][bike[i] + j],dp[i-1][j] + bikeMoney[i]);
                }
            }
        }
        for (int i = 0; i <= TIME; i++) {
            answer=  Math.max(answer,dp[N][i]);
        }

        System.out.println(answer);
    }

}
//3 1650
//500 200 200 100
//800 370 300 120
//700 250 300 90
/**
 * 3 1650
 * 500 200 200 100
 * 800 370 300 120
 * 700 250 300 90
 */
