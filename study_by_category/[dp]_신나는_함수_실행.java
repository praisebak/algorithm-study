import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    int[][][] dp;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        dp = new int[51][51][51];
        while (true){
            String[] sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            int c = Integer.parseInt(sArr[2]);
            if(a == -1 && b == -1 && c == -1) break;
            stringBuilder.append("w(" + a + ", " + b + ", " + c +") = " + w(a,b,c) + "\n");
            w(a,b,c);
        }
        System.out.println(stringBuilder);


    }

    private int w(int a, int b, int c) {
        if(a <= 0 || b <= 0 || c <= 0) return 1;
        if(dp[a][b][c] != 0) return dp[a][b][c];

        if(a > 20 || b > 20 || c > 20){
            return w(20,20,20);
        }

        if(a < b && b < c ){
            dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        }else{
            dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
        }

        return dp[a][b][c];

    }
}
