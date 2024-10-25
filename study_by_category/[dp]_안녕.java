import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solve{
    int N;
    int M;
    int[][] dp = new int[101][21];
    int[] hp = new int[101];
    int[] joy = new int[101];
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        String s = bufferedReader.readLine();
        String[] sArr = s.split(" ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(sArr[i]);
            hp[i] = num;
        }
        sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(sArr[i]);
            joy[i] = num;
        }
        System.out.println(dfs(100,0));
    }

    private int dfs(int curHP, int prev) {
        if(curHP <= 0) return Integer.MIN_VALUE;
        if(dp[curHP][prev] != 0) return dp[curHP][prev];
        for (int j = prev; j < N ; j++) {
            int next = dfs(curHP-hp[j],j+1);
            if(next == Integer.MIN_VALUE) continue;
            dp[curHP][prev] = Math.max(next + joy[j],dp[curHP][prev]);
        }
        return dp[curHP][prev];
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
