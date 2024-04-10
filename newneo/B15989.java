package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Scanner;


class B15989
{

    int[][] dp = new int[10001][4];
    public void solve() throws IOException {

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for(int i=4;i<=10000;i++){
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }

        StringBuilder sb=  new StringBuilder();
        for(int i=0;i<N;i++){
            int tmp = Integer.parseInt(br.readLine());
            sb.append(dp[tmp][1] + dp[tmp][2] + dp[tmp][3] + "\n");
        }
        System.out.println(sb);
    }
}
