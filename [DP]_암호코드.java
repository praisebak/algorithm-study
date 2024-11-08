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
    int[][] dp = new int[5001][27];
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String s = bufferedReader.readLine();
//        for (int i = 0; i < 5000; i++) {
//            Arrays.fill(dp[i],-1);
//        }
//        System.out.println(dfs(s,0));
//        System.out.println(answer);
        
        dfs2(0);
    }

    private void dfs2(int i) {
        if(i == 5000){
            return;
        }
        dfs2(i+1);
    }

    int answer = 0;
    private int dfs(String s, int idx) {
        if(idx == s.length()){
            answer++;
        }

        if(idx >= s.length()) {
            return 0;
        }

        int chIdx = s.charAt(idx) - '0';
        if(dp[idx][chIdx] != -1){
            return dp[idx][chIdx];
        }

        dp[idx][chIdx] = 0;
        if(s.length() > idx+2){
            int num = Integer.parseInt(s.substring(idx,idx+2));
            if(num <= 26){
                int next = dfs(s,idx+2);
                if(next != -1){
                    dp[idx][chIdx] += (next+1);
                }
                if(idx == 0){

                }
            }
        }


        int next = dfs(s,idx+1);
        dp[idx][chIdx] += (next+1);

        return dp[idx][chIdx];
    }
}
