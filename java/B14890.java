import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class B14890 {


    public static void main(String[] args) {
        SolveB14890 solve = new SolveB14890();
        solve.init();
        solve.solve();
    }

}

class SolveB14890
{
    int N;
    int L;
    int[][] map;

    void solve()
    {
        int count = 0;
        for(int i=0;i<N;i++) {
            if(cal(i) == 1) count++;
            if(row(i) == 1) count++;
        }
        System.out.println(count);
    }

    //위에서 아래
    private int cal(int cal) {
        boolean[] isVisit = new boolean[N];
        for (int i = 0; i < N-1; i++) {
            int cur = map[i][cal];
            int next = map[i+1][cal];
            int height = cur - next;
            if(height > 1 || height < -1) return 0;

            //내려가야함
            if(height == 1){
                for(int j = 1; j <=L;j++){
                    if(i + j >= N || isVisit[i + j]) return 0;
                    if(map[i][cal] != map[i+j][cal]+1) return 0;
                    isVisit[i + j] = true;
                }
            }
            //올라가야함
            else if(height == -1){
                for(int j = 0; j < L; j++){
                    if(i - j < 0 || isVisit[i - j] ) return 0;
                    if(map[i][cal] != map[i-j][cal]) return 0;
                    isVisit[i - j] = true;
                }
            }
        }
        return 1;
    }

    private int row(int row) {
        boolean[] isVisit = new boolean[N];
        for (int i = 0; i < N-1; i++) {
            int cur = map[row][i];
            int next = map[row][i+1];
            int height = cur - next;
            if(height > 1 || height < -1) return 0;

            //내려가야함
            if(height == 1){
                for(int j = 1; j <=L;j++){
                    if(i + j >= N || isVisit[i + j]) return 0;
                    if(map[row][i] != map[row][j + i]+1) return 0;
                    isVisit[i + j] = true;
                }
            }
            //올라가야함
            else if(height == -1){
                for(int j = 0; j < L; j++){
                    if(i - j < 0 || isVisit[i - j] ) return 0;
                    if(map[row][i] != map[row][i - j]) return 0;
                    isVisit[i - j] = true;
                }
            }
        }
        return 1;
    }


    public void init() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try{
            String input = in.readLine();
            N = Integer.parseInt(input.split(" ")[0]);
            L = Integer.parseInt(input.split(" ")[1]);
            map = new int[N][N];


            for (int i = 0; i < N; i++) {
                input = in.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(input.split(" ")[j]);
                }
            }
            in.close();
        }catch (IOException e){
        }
    }
}
