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
        String s = bufferedReader.readLine();
        int N =Integer.parseInt(s);
        int[] arr = new int[N+1];
        String[] sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }


        long[][] dp = new long[N+1][21];


        dp[1][arr[0]] = 1;
        for (int i = 1; i < N; i++) {
            int curNum = arr[i];
            for (int num = 0; num <= 20; num++) {
                if(dp[i][num] == 0) continue;
                if(num - curNum >= 0){
                    dp[i+1][num - curNum] += dp[i][num];
                }
                if(num + curNum <= 20){
                    dp[i+1][num + curNum] += dp[i][num];
                }
            }
        }

        System.out.println(dp[N-1][arr[N-1]]);



    }
}
