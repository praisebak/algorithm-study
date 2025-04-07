import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();


    }
}


class Solve{

    private StringBuilder stringBuilder = new StringBuilder();

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;
        while (true){
            int N = Integer.parseInt(bufferedReader.readLine());
            if(N == 0){
                System.out.println(stringBuilder.toString());
                return;
            }
            int[][] dp = new int[N][3];

            for (int i = 0; i < N; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                dp[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                dp[i][1] = Integer.parseInt(stringTokenizer.nextToken());
                dp[i][2] = Integer.parseInt(stringTokenizer.nextToken());
            }
            dp[0][2] += dp[0][1];

            for (int i = 1; i < N ; i++) {
                if(i == 1){
                    dp[i][0] += dp[i-1][1];
                    dp[i][1] += Math.min(Math.min(dp[i-1][1],dp[i-1][2]),dp[i][0]);
                    dp[i][2] += Math.min(dp[i][1],Math.min(dp[i-1][1],dp[i-1][2]));
                }else{
                    dp[i][0] += Math.min(dp[i-1][0],dp[i-1][1]);
                    dp[i][1] += Math.min(
                            Math.min(dp[i-1][0],dp[i-1][1]),
                            Math.min(dp[i-1][2],dp[i][0]));
                    dp[i][2] += Math.min(
                            Math.min(dp[i-1][1],dp[i-1][2]),
                            dp[i][1]);
                }
            }

            stringBuilder.append(count++ +". " + dp[N-1][1]+"\n");
        }
    }
}
