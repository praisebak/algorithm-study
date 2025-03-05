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
    public void solve()throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] cards = bufferedReader.readLine().split(" ");
        int[] arr = new int[N];
        int[] dp = new int[N+1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(cards[i]);
        }

        //현재 산 개수
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i],dp[i-j] + arr[j-1]);
            }
        }
        System.out.println(dp[N]);
    }
}
