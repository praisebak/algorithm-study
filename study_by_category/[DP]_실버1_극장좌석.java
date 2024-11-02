import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] dp = new int[N+1];
        int vipN = Integer.parseInt(bufferedReader.readLine());
        HashSet<Integer> vip = new HashSet<>();
        for (int i = 0; i < vipN; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            vip.add(num);
        }

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            if(vip.contains(i-1) || vip.contains(i)){
                dp[i] = dp[i-1];
                continue;
            }
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[N]);

    }

}
//11
//2
//
//111
//12
//
//1111
//112
//22
//
//11111
//1112
//122
//
