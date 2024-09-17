import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.MonitorInfo;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    StringBuilder stringBuilder = new StringBuilder();
    int[][] arr;
    int[][] pSum;
    int N;
    int M;
    private int answer = Integer.MIN_VALUE;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answerIdx = 1;

        while (true){
            String[] sArr = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(sArr[0]);
            if(N == 0){
                System.out.println(stringBuilder);
                return;
            }
            int idx = 1;

            arr = new int[N+1][N*2];
            pSum = new int[N+1][N*2];
            for(int i=1;i<=N;i++){
                int curCal = i * 2 -1;
                for (int j = 1; j <= curCal; j++) {
                    arr[i][j] = Integer.parseInt(sArr[idx++]);
                    pSum[i][j] = pSum[i][j-1] + arr[i][j];
                }
            }

            M = N*2-1;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j+=2) {
                    getUpper(i,j);
                }
            }

            for (int i = N; i >=1; i--) {
                for (int j = 2; j <= M ; j+=2) {
                    int curSum = 0;
                    for (int k = 0; k < Math.min(j /2,i - j / 2); k++) {
                        curSum += pSum[i-k][j] - pSum[i-k][j-2*k-1];
                        answer = Math.max(curSum,answer);
                    }
                }
            }

            stringBuilder.append(answerIdx+". " + answer + "\n");
            answerIdx++;
            answer = Integer.MIN_VALUE;
        }
    }


    private void getUpper(int r, int c) {
        int curSum = 0;
        int size = 0;
        for (int i = r; i <= N; i++) {
            //size가 2라고하면
            //1 + 2 = 3 > 2 * 2 -1 = 3
            //1 + 0 > 1 * 0
            if(c + size > i * 2-1) return;
            curSum += (pSum[i][c+size] - pSum[i][c-1]);
            size +=2;
            answer = Math.max(curSum,answer);
        }
    }
}
