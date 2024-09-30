import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

/**
 *
 * 3 10
 *
 * 1
 * 2
 * 5
 *

 모든 경우의 수 일단 파악
 3 10인 경우
 이문제 조건상 조합임
 111111111
 111111112
 11111122
 1111222
 112222
 22222
 11111122
 111115
 11125

 dp[0][0] = 1;
 dp[0][1] = 1;
 dp[0][2] = 1;

 //이전꺼에서 5들 다 뽑아오기
 dp[5][2] = 1;

 dp[i][j] =

 fori{
 dp[i-num][0] ~ dp[i-num][N]
 }
 dp[i][i]


 111
 112


 1111
 112
 22

 11111
 1112
 122
 5

 * */

class Solve{
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N =Integer.parseInt(sArr[0]);
        int K =Integer.parseInt(sArr[1]);

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }

        int[] dp = new int[K+1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            int cur = arr[i];
            for (int num = arr[i]; num <= K; num++) {
                dp[num] += dp[num-cur];
            }
        }


        System.out.println(dp[K]);
    }
}
