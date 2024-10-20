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
    int N;
    int[][] map;
    int[][][] dp;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        dp = new int[N][N][3];
        String[] sArr;
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //세로위
                if(i != 0){
                    if(map[i][j] != 1){
                        dp[i][j][1] += dp[i-1][j][1];
                        dp[i][j][1] += dp[i-1][j][2];
                    }
                }

                if(i != 0 && j != 0){
                    if(map[i][j] != 1 && map[i-1][j] != 1 && map[i][j-1] != 1){
                        dp[i][j][2] += dp[i-1][j-1][0];
                        dp[i][j][2] += dp[i-1][j-1][1];
                        dp[i][j][2] += dp[i-1][j-1][2];
                    }
                }

                //가로 가능
                if(j != 0){
                    if(map[i][j] != 1){
                        dp[i][j][0] += dp[i][j-1][0];
                        dp[i][j][0] += dp[i][j-1][2];

                    }
                }
            }
        }

        int answer = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
        System.out.println(answer);
    }

}
