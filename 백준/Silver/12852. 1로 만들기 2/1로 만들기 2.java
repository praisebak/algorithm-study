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
    private int[] trace;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        dp = new int[N+1];
        trace = new int[1000001];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[1]=1;
        sol(N);
        System.out.println(dp[N]-1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = N; i != 0; i = trace[i]) {
            stringBuilder.append(i).append(" ");
        }

        System.out.println(stringBuilder.toString());
    }

    private int sol(int num) {
        if(num == 1){
            return 1;
        }
        if(dp[num] != Integer.MAX_VALUE){
            return dp[num];
        }

        if(num % 3 == 0){
            int result = sol(num / 3);
            if(result < dp[num]){
                trace[num] = num/3;
                dp[num] = result+1;
            }
        }
        if(num % 2 == 0){
            int result = sol(num / 2);
            if(result < dp[num]){
                trace[num] = num/2;
                dp[num] = result+1;
            }
        }
        int result = sol(num-1);
        if(result < dp[num]){
            trace[num] = num-1;
            dp[num] = result+1;
        }
        return dp[num];
    }
}
