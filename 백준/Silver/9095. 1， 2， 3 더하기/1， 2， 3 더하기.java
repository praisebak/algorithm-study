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
    long[] dp = new long[12];
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        perm(11);
        for (int i = 0; i < N; i++) {
            System.out.println(dp[Integer.parseInt(bufferedReader.readLine())]);
        }
    }

    private long perm(int num) {
        if(num == 0){
            return 1;
        }

        if(dp[num] != 0){
            return dp[num];
        }

        dp[num] += perm(num - 1);
        if(num -2 >= 0) dp[num] += perm(num - 2);
        if(num -3 >= 0) dp[num] += perm(num - 3);
        return dp[num];
    }
}
