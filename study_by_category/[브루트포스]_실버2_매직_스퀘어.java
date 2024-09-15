import javax.swing.*;
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
    private boolean[] visit;
    int[][] map;
    int[][] origin;
    int answer = Integer.MAX_VALUE;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        origin = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                origin[i][j] = Integer.parseInt(sArr[j]);
            }
        }


        visit = new boolean[10];
        map = new int[3][3];
        dfs(0,0);
        System.out.println(answer);

    }

    private void dfs(int depth,int cost) {
        if(depth == 9){
            cal(cost);
            return;
        }
        for (int i = 1; i <=9 ; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            int y = depth/3;
            int x = depth % 3;
            map[y][x] = i;

            int newCost = cost + Math.abs(map[y][x] - origin[y][x]);
            dfs(depth+1,newCost);
            visit[i] = false;

        }
    }

    private void cal(int cost) {
        int sum = 0;
        //대각선
        int diagonalSum = 0;

        for (int i = 0; i < 3; i++) {
            //가로
            int curSum = 0;
            for (int j = 0; j < 3; j++) {
                curSum += map[i][j];

            }
            if(sum == 0) sum = curSum;
            if(sum != curSum) return;


            curSum = 0;
            for (int j = 0; j < 3; j++) {
                curSum += map[j][i];
            }
            if(sum != curSum) return;

            diagonalSum += map[i][i];
        }
        if(diagonalSum != sum) return;

        diagonalSum = 0;
        for (int i = 0; i <3 ; i++) {
            diagonalSum += map[i][2-i];
        }
        if(diagonalSum != sum) return;


        answer=  Math.min(cost,answer);


    }
}
