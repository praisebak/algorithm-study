import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{
    class Candy{
        int c;
        int p;
        public Candy(int c,int p){
            this.c=c;
            this.p=p;
        }

    }

    long[] dp;
    List<Candy> candyList = new ArrayList<>();
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        while (true){
            String s = bufferedReader.readLine();
            if(s.equals("0 0.00")) break;
            dp = new long[10001];

            String[] sArr = s.split(" ");
            int N = Integer.parseInt(sArr[0]);
            int curMoney = (int) (Double.parseDouble(sArr[1]) * 100 + 0.5);
            candyList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                sArr = bufferedReader.readLine().split(" ");
                int c = Integer.parseInt(sArr[0]);
                int p = (int) (Double.parseDouble(sArr[1]) * 100 + 0.5);
                candyList.add(new Candy(c,p));
            }

            dfs(curMoney);
            stringBuilder.append(dp[curMoney]+"\n");
        }
        System.out.print(stringBuilder);


    }

    private long dfs(int curMoney) {
        if(dp[curMoney] != 0){
            return dp[curMoney];
        }

        for (Candy candy : candyList){
            if(curMoney >= candy.p){
                dp[curMoney] = Math.max(dfs(curMoney - candy.p) +candy.c,dp[curMoney]);
            }
        }
        return dp[curMoney];
    }
}
