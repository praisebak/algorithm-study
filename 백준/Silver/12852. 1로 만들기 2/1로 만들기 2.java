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

class Solve{
    int[] dp;
    int[] trace;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        dp = new int[1000001];
        trace = new int[1000001];

        System.out.println(dfs(N,1));
        System.out.print(N + " ");
        while (trace[N] != 0){
            System.out.print(trace[N] + " ");
            N = trace[N];
        }



    }


    private int dfs(int n,int depth) {
        if(dp[n] != 0) return dp[n];
        if(n == 1) return 0;

        int minDepth = 2000000000;
        int minVal = 0;

        if(n % 3 == 0){
            int cur = dfs(n/3,depth+1)+1;
            if(minDepth > cur){
                minDepth = cur;
                minVal = n/3;
            }
        }

        if(n % 2 == 0){
            int cur = dfs(n / 2, depth + 1)+1;
            if (minDepth > cur) {
                minDepth = cur;
                minVal = n / 2;
            }
        }

        int cur = dfs(n-1,depth+1)+1;
        if(minDepth > cur){
            minDepth = cur;
            minVal = n-1;
        }

        dp[n] = minDepth;
        trace[n] = minVal;
        return dp[n];
    }
}