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
        String[] sArr =  bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        int[] coin = new int[N];

        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(bufferedReader.readLine());
        }
        //현재 돈 가치,동전 인덱스
        int[] dp = new int[M+1];
        Arrays.fill(dp,100001);
        dp[0] = 0;


        for (int i = 1; i <= M; i++) {
            //현재 시점에서 가능하다면
            for (int j = 0; j < N; j++) {
                int c = coin[j];
                if(c <= i){
                    dp[i] = Math.min(dp[i],dp[i-c]+1);
                }
            }
        }

        System.out.println(dp[M] == 100001 ? -1 : dp[M]);
    }
}
