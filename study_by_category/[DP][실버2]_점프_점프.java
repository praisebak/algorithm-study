import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{

    public static void main(String[] args)  throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N];
        String[] sArr = bufferedReader.readLine().split(" ");
        for(int i=0;i< N;i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        int[] dp = new int[N+1];

        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[1] = 0;
        for(int i=1;i<=N;i++){
            int num = arr[i-1];
            for (int j = 1; j <= num; j++) {
                if(dp[i] == Integer.MAX_VALUE) continue;
                if(i + j>= N) {
                    dp[N] = Math.min(dp[N],dp[i]+1);
                } else{
                    dp[i+j] = Math.min(dp[i+j],dp[i]+1);
                }
            }
        }


        System.out.println(dp[N] == Integer.MAX_VALUE ? -1 : dp[N]);
    }
}
