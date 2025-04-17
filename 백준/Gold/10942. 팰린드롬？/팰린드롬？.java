import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        boolean[][] dp = new boolean[N+1][N+1];
        int[] arr = new int[N+1];
        String[] sArr = bufferedReader.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(sArr[i-1]);
        }

        for (int col = 1; col <= N; col++) {
            for (int row = 1; row <= col; row++) {
                if(col == row) dp[row][col] = true;
                else if(col - row == 1){
                    dp[row][col] = arr[col] == arr[row];
                }else{
                    dp[row][col] = (dp[row+1][col-1] && arr[col] == arr[row]);
                }
            }
        }


        int M = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            stringBuilder.append((dp[a][b] ? 1 : 0) + "\n");
        }

        System.out.println(stringBuilder);
    }

}