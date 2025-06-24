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
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] sArr = bufferedReader.readLine().split(" ");

        int[] dp = new int[N+1];
        Arrays.fill(dp,1);
        dp[0] = 0;
        int[] position = new int[N+1];
        int[] arr = new int[N+1];
        int answer = 1;
        //내 앞에 몇명있는지?
        for (int i = 0; i < N; i++) {
            int curIdx = Integer.parseInt(sArr[i]);
            arr[i] = curIdx;
            position[curIdx] = i;
        }

        for (int i = 0; i < N; i++) {
            int curIdx = arr[i];
            if(curIdx != N && position[curIdx+1] > position[curIdx]){
                dp[curIdx+1] = dp[curIdx]+1;
                answer = Math.max(answer,dp[curIdx+1]);
            }
        }

        System.out.println(N - answer);
    }
}
