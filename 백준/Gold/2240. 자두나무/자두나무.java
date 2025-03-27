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
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }

        long[][][] dp = new long[N+1][M+1][3];

        if(arr[0] == 1){
            dp[0][M][1] = 1;
        }else{
            dp[0][M-1][2] = 1;
        }

        long answer = 1;

        for (int i = 1; i < N; i++) {
            int cur = arr[i];
            for (int j = 0; j <= M; j++) {
                int a = 1;
                int b = 2;

                //현재 위치와 같다
                if(cur == 2){
                    a = 2;
                    b = 1;
                }

                //현재 위치와 같은거에서 가져오기
                dp[i][j][a] = Math.max(dp[i-1][j][a]+1,dp[i][j][a]);
                dp[i][j][b] = Math.max(dp[i-1][j][b],dp[i][j][b]);
                answer = Math.max(dp[i][j][a],answer);
                //M써서 현재 위치와 다른 이전거에서 가져오기
                if(j != 0){
                    dp[i][j-1][a] = Math.max(dp[i-1][j][b]+1,dp[i][j-1][a]);
                    answer = Math.max(dp[i][j-1][a],answer);
                }
            }
        }
        System.out.println(answer);
    }
}