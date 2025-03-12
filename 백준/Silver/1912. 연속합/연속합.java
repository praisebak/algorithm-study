import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import javax.print.DocFlavor.INPUT_STREAM;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int MIN;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] sArr = bufferedReader.readLine().split(" ");
        int[] arr = new int[N+1];
        for (int i = 0; i < sArr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }

        MIN = -100000001;
        long[] dp = new long[N+1];
        long prev = MIN;

        Arrays.fill(dp, MIN);
        for (int i = 1; i <= N; i++) {
            long cur = arr[i-1];
            if(prev + cur < cur){
                prev = cur;
            } else{
                prev += cur;
            }
            dp[i] = Math.max(prev,dp[i-1]);
        }

        System.out.println(dp[N]);
    }
}
