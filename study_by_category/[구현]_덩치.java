import javax.naming.PartialResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s =bufferedReader.readLine();
        int N =Integer.parseInt(s);
        int[][] weight = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            weight[i][0] = Integer.parseInt(sArr[0]);
            weight[i][1] = Integer.parseInt(sArr[1]);
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int curRank = N;
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                int sum = 0;
                if (weight[i][0] >= weight[j][0]){
                    sum++;
                }
                if(weight[i][1] >= weight[j][1]){
                    sum++;
                }
                if(sum >= 1){
                    curRank--;
                }
            }
            stringBuilder.append(curRank + " ");
        }
        System.out.println(stringBuilder);
    }

}
