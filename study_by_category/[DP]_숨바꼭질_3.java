
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
    int[] dp;
    private int MAX = 100001;
    private int end;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int start = Integer.parseInt(sArr[0]);
        end = Integer.parseInt(sArr[1]);
        dp = new int[MAX * 2 + 1];
        //for문으로 구현할려고하니까 -1,+1로가야하고 맨끝으로 이동했을때 등 너무 번거롭다
        //dfs사용하는 방식

        Arrays.fill(dp,Integer.MAX_VALUE);
        dfs(start,1);
        System.out.println(dp[end]-1);
    }

    int minWeight = Integer.MAX_VALUE;

    private void dfs(int cur,int time) {
        if(cur < 0 || cur > MAX * 2){
            return;
        }

        if(time >= dp[end]){
            return;
        }

        if(dp[cur] <= time){
            return;
        }

        dp[cur] = time;
        if(cur == end){
            return;
        }

        if(cur > end){
            dp[end] = Math.min(dp[end],time + cur - end);
            return;
        }else{
            dfs(cur*2,time);
            dfs(cur-1,time+1);
            dfs(cur+1,time+1);
        }
    }
}
