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
        int[] pSum = new int[N+1];
        String[] sArr = bufferedReader.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            pSum[i] = pSum[i-1] + Integer.parseInt(sArr[i-1]);
        }

        int K = Integer.parseInt(bufferedReader.readLine());



        int[][] dp = new int[4][N+1];
        for (int i = 1; i <= 3; i++) {
            for (int j = i*K; j <=N; j++) {
                dp[i][j] = Math.max(dp[i-1][j-K] + pSum[j] - pSum[j-K],
                        dp[i][j-1]);

            }

        }
//        long answer = Integer.MIN_VALUE;
//        for (int i = 0; i < N - (K + K); i++) {
//            for (int j = i+K; j <= N - (K + K) ; j++) {
//                for (int k = j+K; k <= N-K; k++) {
//                    int a = pSum[i + K] - pSum[i];
//                    int b = pSum[j + K] - pSum[j];
//                    int c = pSum[k + K] - pSum[k];
//                    answer= Math.max(answer,a + b + c);
//                }
//            }
//        }

        System.out.println(dp[3][N]);
    }
}
