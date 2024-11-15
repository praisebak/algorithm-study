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

    int[][] maxDP;
    int N;
    private int[][] minDP;
    int MAX = 987654321;

    int[][] map;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        map = new int[N][3];

        maxDP = new int[N][3];
        minDP = new int[N][3];
        for (int i = 0; i < N; i++) {
            Arrays.fill(minDP[i],MAX);
        }

        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        maxDP[0][0] = minDP[0][0] = map[0][0];
        maxDP[0][1] = minDP[0][1] = map[0][1];
        maxDP[0][2] = minDP[0][2] = map[0][2];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int cur = map[i][j];
                if(j != 0){
                    maxDP[i][j] = Math.max(maxDP[i][j],maxDP[i-1][j-1] + cur);
                    minDP[i][j] = Math.min(minDP[i][j],minDP[i-1][j-1] + cur);
                }
                if(j != 2){
                    maxDP[i][j] = Math.max(maxDP[i][j],maxDP[i-1][j+1] + cur);
                    minDP[i][j] = Math.min(minDP[i][j],minDP[i-1][j+1] + cur);
                }
                maxDP[i][j] = Math.max(maxDP[i][j],maxDP[i-1][j] + cur);
                minDP[i][j] = Math.min(minDP[i][j],minDP[i-1][j] + cur);
            }
        }
        int maxAnswer = 0;

        for (int i = 0; i < 3; i++) {
            maxAnswer = Math.max(maxAnswer,maxDP[N-1][i]);
        }

        int minAnswer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            minAnswer = Math.min(minAnswer,minDP[N-1][i]);
        }
        System.out.println(maxAnswer + " " + minAnswer);
    }


}
