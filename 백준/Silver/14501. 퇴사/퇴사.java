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
        int[] needTimes = new int[N+1];
        int[] moneys = new int[N+1];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int num = Integer.parseInt(sArr[0]);
            int val = Integer.parseInt(sArr[1]);
            needTimes[i] = num;
            moneys[i] = val;
        }

        int[] dp = new int[N+1];

        for (int i = 0; i < N; i++) {
            int nT = needTimes[i];
            if(i + nT <= N){
                dp[i+nT] = Math.max(dp[i] + moneys[i],dp[i+nT]);
            }
            dp[i+1] = Math.max(dp[i],dp[i+1]);
        }

        System.out.println(dp[N]);
    }
}
