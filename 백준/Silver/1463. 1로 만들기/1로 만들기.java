import javax.sql.RowSetInternal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{

    private int[] dp;
    private int MAX = 1000002;

    public void solve() throws IOException{
        dp = new int[1000001];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int N = Integer.parseInt(s);
        Arrays.fill(dp, MAX);
        perm(N,0);
        System.out.println(dp[1]);
    }

    private int perm(int n, int count) {
        if(dp[n] < count){
            return dp[n];
        }
        if(n == 1){
            dp[n] = Math.min(count,dp[n]);
            return dp[n];
        }
        if(dp[n] > count){
            dp[n] = count;
        }
        if(n % 3 == 0){
            dp[n/3] = Math.min(perm(n / 3,count+1),dp[n/3]);
        }
        if(n % 2 == 0){
            dp[n/2] = Math.min(perm(n / 2 ,count+1),dp[n/2]);
        }
        dp[n-1] = Math.min(perm(n-1,count+1),dp[n-1]);
        return dp[n];
    }
}
