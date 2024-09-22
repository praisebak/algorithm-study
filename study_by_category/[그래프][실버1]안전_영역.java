import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    boolean[][] visit;
    int N;
    int M;
    int[][] map;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        int K = Integer.parseInt(sArr[2]);
        visit = new boolean[N][M];
        map = new int[N][M];
        for (int i = 0; i < K; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int x1 = Integer.parseInt(sArr[0]);
            int y1 = Integer.parseInt(sArr[1]);
            int x2   = Integer.parseInt(sArr[2]);
            int y2 = Integer.parseInt(sArr[3]);

            //4 0 6 2 입력을 받으면
            //N-1,4부터 N-3,5까지
            for (int row = N-1-y1; row > N-1-y2; row--) {
                for (int col = x1; col <x2; col++) {
                    map[row][col] = 1;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0 && !visit[i][j]){
                    visit[i][j] = true;
                    count = 1;
                    dfs(i,j);
                    answerList.add(count);
                }
            }
        }

        Collections.sort(answerList);
        StringBuilder stringBuilder = new StringBuilder(answerList.size() + "\n");
        for (int i = 0; i < answerList.size(); i++) {
            stringBuilder.append(answerList.get(i) + " ");
        }

        System.out.println(stringBuilder);
    }

    int count = 0;
    List<Integer> answerList = new ArrayList<>();

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    private void dfs(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nY = r + dy[i];
            int nX = c + dx[i];
            if(!isValid(nY,nX))continue;
            if(visit[nY][nX]) continue;
            if(map[nY][nX] == 1) continue;
            visit[nY][nX] = true;
            count++;
            dfs(nY,nX);
        }
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
    }

}
