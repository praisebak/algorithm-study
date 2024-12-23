import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int[][] dp;
    private int maxLen;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String obj = bufferedReader.readLine();
        maxLen = Math.max(s.length(),obj.length());
        dp = new int[maxLen +1][maxLen +1];
        makeTable(s,obj);
        lcs(s,s.length(),obj.length());
    }

    private void lcs(String s, int i, int j) {
        StringBuilder stringBuilder = new StringBuilder();
        while (i >= 1 && j >= 1){

            if(dp[i][j] == dp[i-1][j]){
                i--;
            }else if(dp[i][j] == dp[i][j-1]){
                j--;
            }else{
                stringBuilder.append(s.charAt(i-1));
                i--;
                j--;
            }
        }
        System.out.println(stringBuilder.reverse().toString());

    }


    private void makeTable(String s, String obj) {
        System.out.println();
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= obj.length(); j++) {
                if(obj.charAt(j-1) == s.charAt(i-1)){
                    dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j-1])+1;
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        System.out.println(dp[s.length()][obj.length()]);
    }

}
