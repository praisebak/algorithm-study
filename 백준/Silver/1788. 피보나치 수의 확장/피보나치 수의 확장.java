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
    int mod = 1000000000;
    private int[] dp;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        boolean isMinus = false;
        if(N < 0){
            isMinus = true;
        }

        if(N == 0){
            System.out.println(0+"\n"+0);
            return;
        }

        int K = Math.abs(N);
        StringBuilder stringBuilder = new StringBuilder();

        dp = new int[1000001];

        Arrays.fill(dp,-1);
        dp[0] = 0;
        dp[1] = 1;
        int answer = 0;
        if(isMinus){
            answer = minusFibo(K);
        }else{
            answer = fibo(K);
        }
        if(answer < 0){
            stringBuilder.append("-1\n");
        }else{
            stringBuilder.append("1\n");
        }
        stringBuilder.append(Math.abs(answer));
        System.out.print(stringBuilder.toString());
    }

    private int fibo(int k) {
        if(dp[k] != -1){
            return dp[k];
        }
        dp[k] = (fibo(k-2) + fibo(k-1)) % 1000000000;
        return dp[k];
    }

    private int minusFibo(int k) {
        if(dp[k] != -1){
            return dp[k];
        }
        dp[k] = (minusFibo(k-2) + (minusFibo(k-1) * -1)) % 1000000000;
        return dp[k];
    }
}
