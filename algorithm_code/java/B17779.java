import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class B17779 {
    public static void main(String[] args) throws IOException {
        SolveB17779 solveB17779 = new SolveB17779();
        solveB17779.init();
        solveB17779.solve();
    }



}

class SolveB17779 {
    int N;
    private int[][] map;
    boolean[][][][] isVisit;
    int peopleSum = 0;

    int answer = Integer.MAX_VALUE;
    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer( br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                peopleSum += map[i][j];
            }
        }
        isVisit = new boolean[N+1][N+1][N+1][N+1];
    }

    public void solve() {
        //여기선 x y d1 d2 int[x][y] x로 row 접근
        permutation(1,1,1,1,0);
        System.out.println(answer);
    }


    boolean isValid(int x,int y){
        if(x < 1 || y < 1 || x > N || y > N) return false;
        return true;
    }

    private void permutation(int x, int y, int d1, int d2,int depth) {
        if( x > N || y > N || d1 > N || d2 > N){
            return;
        }

        if(isVisit[x][y][d1][d2]) return;
        isVisit[x][y][d1][d2] = true;

        if(x + d1 + d2 <= N && 1 <= y-d1 && y-d1 < y && y+d2 <=N ){
            makeLine(x,y,d1,d2);
        }

        permutation(x+1,y,d1,d2,depth+1);
        permutation(x,y+1,d1,d2,depth+1);
        permutation(x,y,d1+1,d2,depth+1);
        permutation(x,y,d1,d2+1,depth+1);


    }

    private void makeLine(int x, int y, int d1, int d2) {
        //경계선
        int[][] line = new int[N+1][N+1];


        //경계선 만든다
        //1번,4번 경계선
        for (int i = 0; i <= d1; i++) {
            if(isValid(x + i, y - i)){
                line[x + i][y - i] = 5;
            }
            if(isValid(x + d2 + i, y + d2 - i)){
                line[x + d2 + i][y + d2 - i] = 5;
            }
        }

        for (int i = 0; i <= d2; i++) {
            if(isValid(x + d1 + i,y - d1 + i)){
                line[x + d1 + i][y - d1 + i] = 5;
            }
            if(isValid(x + i, y + i)){
                line[x + i][y + i] = 5;
            }

        }

        int[] tmpSum = new int[5];

        for(int i=1;i< x + d1;i++){
            for(int j=1;j<=y;j++){
                if(line[i][j] == 5) break;
                line[i][j] = 1;
                tmpSum[0] += map[i][j];
            }
        }


        for(int i=1;i<= x + d2;i++){
            for(int j=N;j > y;j--){
                if(line[i][j] == 5) break;
                line[i][j] = 2;
                tmpSum[1] += map[i][j];
            }
        }

        for(int i=x+d1; i <= N;i++){
            for(int j=1;j<y-d1+d2;j++){
                if(line[i][j] == 5) break;
                line[i][j] = 3;
                tmpSum[2] += map[i][j];
            }
        }



        for (int i = N; i >  x+d2  ; i--) {
            for (int j = N; j >= y-d1+d2 ; j--) {
                if(line[i][j] == 5) break;
                line[i][j] = 4;
                tmpSum[3] += map[i][j];
            }
        }

        tmpSum[4] = peopleSum;

        for (int i = 0; i < 4; i++) {
            tmpSum[4] -= tmpSum[i];
        }

        Arrays.sort(tmpSum);

        answer = Math.min(tmpSum[4] - tmpSum[0],answer);
    }

}

