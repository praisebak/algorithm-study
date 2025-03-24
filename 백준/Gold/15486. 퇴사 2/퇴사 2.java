import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        int[] timeArr = new int[N+2];
        int[] moneyArr = new int[N+2];

        int[] dp = new int[N+2];

        for (int i = 1; i <= N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            timeArr[i] = Integer.parseInt(sArr[0]);
            moneyArr[i] = Integer.parseInt(sArr[1]);
        }

        for (int i = 1; i <= N+1; i++) {
            dp[i] = Math.max(dp[i-1],dp[i]);
            int time = timeArr[i];
            if(i + time >= N+2) continue;
            dp[i + time] = Math.max(dp[i+time],dp[i] + moneyArr[i]);
        }


        System.out.println(dp[N+1]);

    }
}