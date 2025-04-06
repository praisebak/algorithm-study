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
    int[][] dp;
    private int N;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        dp = new int[N +1][10];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i],-1);
        }
        System.out.println(comb(0,0,0));
    }

    private int comb(int numCount, int val,int prevSelectNum) {
        if(dp[numCount][prevSelectNum] != -1){
            return dp[numCount][prevSelectNum];
        }

        dp[numCount][prevSelectNum] = 0;
        if(numCount == N){
            dp[numCount][prevSelectNum] = 1;
            return 1;
        }

        for (int i = prevSelectNum; i <= 9; i++) {
            dp[numCount][prevSelectNum] += comb(numCount+1,val,i);
            dp[numCount][prevSelectNum] %= 10007;
        }

        return dp[numCount][prevSelectNum];
    }
}

