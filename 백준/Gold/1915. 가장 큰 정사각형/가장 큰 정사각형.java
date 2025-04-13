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
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        if(N == 1 && M == 1){
            System.out.println(1);
            return;
        }
        int[][] map = new int[N+1][M+1];
        int[][] dp = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = s.charAt(j-1) - '0';
            }
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if(i == 1 && j == 1){
                    dp[i][j] = map[i][j];
                }else{

                    if(map[i][j] == 1){
                        int min = Math.min(dp[i][j-1],dp[i-1][j]);
                        min = Math.min(min,dp[i-1][j-1]);
                        dp[i][j] = min+1;
                        answer = Math.max(dp[i][j],answer);
                    }
                }

            }
        }
        System.out.println(answer * answer);

    }
}