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
  
    private int[][] arr;  
    private int[][][] dp;  
    private int N;  
  
    private final StringBuilder stringBuilder = new StringBuilder();  
    public void solve() throws IOException{  
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));  
        int T = Integer.parseInt(bufferedReader.readLine());  
        for (int t = 0; t < T; t++) {  
            N = Integer.parseInt(bufferedReader.readLine());  
            arr = new int[2][N +1];  
            dp = new int[2][N +1][2];  
  
            for (int i = 0; i < 2; i++) {  
                String[] sArr = bufferedReader.readLine().split(" ");  
                for (int j = 0; j < N; j++) {  
                    arr[i][j] = Integer.parseInt(sArr[j]);  
                }  
            }  
            sol();  
        }  
        System.out.println(stringBuilder.toString());  
    }  
  
    private void sol() {  
        dp[0][0][1] = arr[0][0];  
        dp[1][0][1] = arr[1][0];  
  
        for (int j = 1; j <= N; j++) {  
            //현재 안뜯으면 가능한것중 가장 큰거  
            dp[0][j][0] = Math.max(dp[0][j-1][1],dp[1][j-1][1]);  
            dp[0][j][1] = Math.max(dp[0][j-1][0],dp[1][j-1][1]) + arr[0][j];  
  
            dp[1][j][0] = Math.max(dp[0][j-1][1],dp[1][j-1][1]);  
            dp[1][j][1] = Math.max(dp[0][j-1][1],dp[1][j-1][0]) + arr[1][j];  
        }  
  
        int upMax = Math.max(dp[0][N][0], dp[0][N][1]);  
        int max = Math.max(upMax,Math.max(dp[1][N][0], dp[1][N][1]));  
        stringBuilder.append(max).append("\n");  
  
    }  
}