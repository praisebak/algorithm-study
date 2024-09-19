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
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr =bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        int answer = 0;
        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                int up = 0;
                int left = 0;
                if(i != 0){
                    up = arr[i-1][j];
                }
                if(j != 0){
                    left = arr[i][j-1];
                }
                int bigger = Math.max(up,left);
                arr[i][j] = bigger + arr[i][j];
            }
        }
        System.out.println(arr[N-1][M-1]);
    }
}
