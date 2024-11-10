import com.sun.jdi.IntegerType;

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
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int obj = Integer.parseInt(sArr[1])-1;
        int[][] score = new int[N][N];
        int answer = 1;

        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int idx = Integer.parseInt(sArr[0])-1;
            for (int j = 1; j < 4; j++) {
                score[idx][j] = Integer.parseInt(sArr[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            if(i == obj){
                continue;
            }
            for (int j = 1; j < 4; j++) {
                if(score[i][j] > score[obj][j]){
                    answer++;
                    break;
                }
                if(score[obj][j] > score[i][j]){
                    break;
                }
            }
        }

        System.out.println(answer);

    }
}
