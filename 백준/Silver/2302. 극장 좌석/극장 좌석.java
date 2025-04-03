import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        boolean[] arr = new boolean[N+1];
        int vipSize = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < vipSize; i++) {
            int num  = Integer.parseInt(bufferedReader.readLine());
            arr[num] = true;
        }

        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i-1];
            if(!arr[i] && !arr[i-1]){
                dp[i] = dp[i-1] + dp[i-2];
            }
        }
        System.out.println(dp[N]);

    }
}
