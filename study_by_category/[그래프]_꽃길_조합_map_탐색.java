
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
        String s = bufferedReader.readLine();
        N = Integer.parseInt(s);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }
        visit = new boolean[N][N];
        dfs(0,-1,0,0,"");
        System.out.println(answer);

    }

    int answer=  Integer.MAX_VALUE;
    int N;
    boolean[][] visit;

    private void dfs(int prevR,int prevC, int depth,int sum,String s) {
        if(depth == 3){
            answer = Math.min(sum,answer);
            return;
        }


        /**
         * map에서 조합으로 접근하기
         */
        for (int i = prevR; i < N; i++) {
            int curJ = i == prevR ? prevC+1 : 0;
            for (int j = curJ; j < N; j++) {
                int curSum = check(i,j);
                if(curSum == -1) continue;
                dfs(i,j,depth+1,curSum + sum,s);
                unCheck(i,j);
            }
        }

    }

    private void unCheck(int i, int j) {
        visit[i][j] = false;

        for (int k = 0; k < 4; k++) {
            int nY = dy[k] + i;
            int nX = dx[k] + j;
            visit[nY][nX] = false;
        }

    }

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int[][] map;

    private int check(int i, int j) {
        if(visit[i][j]) return -1;
        for (int k = 0; k < 4; k++) {
            int nY = dy[k] + i;
            int nX = dx[k] + j;
            if(nY < 0 || nX < 0 || nY >= N || nX >= N) return -1;
            if(visit[nY][nX]) return -1;
        }
        int sum = map[i][j];
        visit[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nY = dy[k] + i;
            int nX = dx[k] + j;
            sum += map[nY][nX];
            visit[nY][nX] = true;
        }
        return sum;

    }
}
