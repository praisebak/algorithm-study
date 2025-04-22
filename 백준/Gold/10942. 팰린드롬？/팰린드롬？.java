import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int N;
    private int[][] dp;
    private int[] arr;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        String[] sArr = bufferedReader.readLine().split(" ");
        arr = new int[N+1];
        dp = new int[N +1][N +1];
        for (int i = 0; i < N; i++) {
            arr[i+1] = Integer.parseInt(sArr[i]);
        }

        sol();

        StringBuilder stringBuilder = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            stringBuilder.append(dp[a][b]).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    private void sol() {
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        for (int row = N-1; row > 0; row--) {
            for (int col = row; col <= N; col++) {
                if(dp[row+1][col-1] == 1){
                    if(arr[col] == arr[row]){
                        dp[row][col] = 1;
                    }
                }
                if(col - row == 1){
                    if(arr[col] == arr[row]){
                        dp[row][col] = 1;
                    }
                }
            }
        }
    }
}
