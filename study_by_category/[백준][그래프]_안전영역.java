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
    boolean[][] visit;
    int N;
    int[][] map;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s =bufferedReader.readLine();
        N = Integer.parseInt(s);
        visit = new boolean[N][N];
        map = new int[N][N];
        String[] sArr;
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }
        int answer = 0;

        for (int val = 1; val <= 100; val++) {
            int count = 0;

            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] < val) continue;
                    if(visit[i][j]) continue;
                    visit[i][j] = true;
                    count++;
                    dfs(i,j,val);
                }
            }
            if(count == 0){
                break;
            }

            answer = Math.max(count,answer);
        }
        System.out.println(answer);
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    public void dfs(int i, int j, int val) {
        for (int k = 0; k < 4; k ++) {
            int nY = i + dy[k];
            int nX = j + dx[k];
            if(nY < 0|| nX < 0 || nY >=N || nX >= N) continue;
            if(visit[nY][nX]) continue;
            if(map[i][j] < val) continue;
            visit[nY][nX] = true;
            dfs(nY,nX, val);
        }

    }
}
