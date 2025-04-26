import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.util.Arrays;
import javax.print.DocFlavor.STRING;

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
        int maxWeight = Integer.parseInt(sArr[1]);

        int[] weights = new int[N+1];
        int[] values = new int[N+1];
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int weight = Integer.parseInt(sArr[0]);
            int value = Integer.parseInt(sArr[1]);
            weights[i]=  weight;
            values[i] = value;
        }

        int answer = 0;
        int[] dp = new int[maxWeight+1];
        for (int i = 0; i < N; i++) {
            int curWeight = weights[i];
            int curValue = values[i];

            int[] tmpDp = Arrays.copyOf(dp, dp.length);

            for (int j = 0; j <= maxWeight; j++) {
                if(j + curWeight > maxWeight) break;
                tmpDp[j+curWeight] = Math.max(dp[j+curWeight],dp[j] + curValue);
                answer = Math.max(tmpDp[j+curWeight],answer);
            }
            dp = tmpDp;
        }
        System.out.println(answer);
    }


}
