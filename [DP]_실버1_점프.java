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

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[][] arr = new int[N][N];
        long[][] dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            String[] sArr= bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == N-1 && j == N-1) continue;
                int nextMove = arr[i][j];
                if(nextMove == 0) continue;
                if(dp[i][j] == 0) continue;

                if(j + nextMove < N ){
                    dp[i][j+nextMove] += dp[i][j];
                }

                if(i + nextMove < N){
                    dp[i + nextMove][j] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N-1][N-1]);


    }
}
