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

    private Pair[] arr;
    private int n;
    private int[][] dp;

    class Pair{
        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        int left;
        int right;
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s= bufferedReader.readLine();
        n = Integer.parseInt(s);
        arr = new Pair[n +1];
        for (int i = 0; i < n; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int r = Integer.parseInt(sArr[0]);
            int c = Integer.parseInt(sArr[1]);
            arr[i] = new Pair(r,c);
        }

        //idx번째에 선택을 하고 안하고
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        System.out.println(dfs(0,n-1));
    }

    private int dfs(int left,int right) {
        if(left == right){
            return 0;
        }

        if(dp[left][right] != Integer.MAX_VALUE){
            return dp[left][right];
        }

        for (int i = left; i < right; i++) {
            int result = dfs(left,i) + dfs(i+1,right) + (arr[left].left * arr[i].right * arr[right].right);
            dp[left][right] = Math.min(result,dp[left][right]);
        }
        return dp[left][right];
    }
}
