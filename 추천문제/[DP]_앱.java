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
    int[] onActivate;
    int[] onDeactivate;
    private int n;
    private int m;
    private int answer  = Integer.MAX_VALUE;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(sArr[0]);
        m = Integer.parseInt(sArr[1]);
        onActivate = new int[n +1];
        onDeactivate = new int[n +1];

        sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < sArr.length; i++){
            int num = Integer.parseInt(sArr[i]);
            onActivate[i] = num;
        }

        sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < sArr.length; i++){
            int num = Integer.parseInt(sArr[i]);
            onDeactivate[i] = num;
        }

        int[][] dp = new int[101][10001];

        for (int i = 0; i < n; i++) {
            int curActivate = onActivate[i];
            int curDeactivate = onDeactivate[i];
            for (int j = 0; j <= 10000; j++) {
                if(i == 0){
                    if(curDeactivate <= j){
                        dp[i][j] = curActivate;
                    }
                }else{
                    if(curDeactivate  <= j){
                        dp[i][j] = Math.max(dp[i-1][j-curDeactivate] + curActivate,dp[i-1][j]);
                    }else{
                        dp[i][j] = dp[i-1][j];
                    }
                }
                if(dp[i][j] >= m){
                    answer = Math.min(answer,j);
                }
            }
        }

        System.out.println(answer);
    }
}

