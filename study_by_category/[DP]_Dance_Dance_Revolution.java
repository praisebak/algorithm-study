import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    int N;
    int[] arr;
    //상하좌우
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = sArr.length-1;
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(sArr[i-1]);
        }

        //0은 상 좌 하 우
        int[][][] dp = new int[N+1][5][5];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j],Integer.MAX_VALUE);
            }
        }

        dp[0][0][0] = 2;
        for (int i = 1; i <= N; i++) {
            int cur = arr[i];
            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    int nextWeight = getWeight(right,cur);
                    if(right == cur){
                        nextWeight = 0;
                    }
                    if(dp[i-1][left][right] == Integer.MAX_VALUE) continue;
                    dp[i][left][cur] = Math.min(dp[i-1][left][right] + nextWeight,dp[i][left][cur]);
                }

                for (int right = 0; right< 5; right++) {
                    int nextWeight = getWeight(left,cur);
                    if(left == cur){
                        nextWeight = 0;
                    }
                    if(dp[i-1][left][right] == Integer.MAX_VALUE) continue;
                    dp[i][cur][right] = Math.min(dp[i-1][left][right] + nextWeight,dp[i][cur][right]);
                }
            }
        }

        int answer = 0;

        for (int l = 0; l <= N; l++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(dp[l][i][j] == Integer.MAX_VALUE) continue;
                    System.out.print(dp[l][i][j] + " ");
                    answer = Math.min(answer,dp[N-1][i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println(answer);
    }

    private int getWeight(int prev, int cur){
        if(prev == 0) return 2;
        //상좌
        if(prev == 1 || prev == 3){
            //좌우일때
            if (cur % 2 == 0) {
                return 3;
            }else{
                return 4;
            }
        }else{
            if(cur % 2 == 0){
                return 4;
            }
            return 3;
        }
    }
}

