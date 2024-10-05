import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SignedObject;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    int[][] map;
    int N;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        visit = new boolean[N][N];
        bfs(-1,-1,0,"");

        System.out.println(answer);


    }

    boolean[][] visit;
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    int answer = Integer.MAX_VALUE;
    private void bfs(int pR, int pC, int depth,String s) {
        if(depth == 3){
            boolean[][] flowerVisit =  new boolean[N][N];
            String[] sArr = s.split(" ");

            int sum = 0;
            for (String string : sArr){
                String[] flower = string.split(",");
                int r = Integer.parseInt(flower[0]);
                int c = Integer.parseInt(flower[1]);
                flowerVisit[r][c] = true;
                for (int i = 0; i < 4; i++) {
                    int nY = r + dy[i];
                    int nX = c + dx[i];
                    if(!isValid(nY,nX)){
                        return;
                    }
                    if(flowerVisit[nY][nX]){
                        flowerVisit[nY][nX] = true;
                        return;
                    }
                    sum += map[nY][nX];
                }
            }
            answer = Math.min(sum,answer);

        }
        for (int i = pR+1; i < N; i++) {
            for (int j = pC+1; j < N ; j++) {
                if(visit[i][j]) continue;
                visit[i][j] = true;
                bfs(i,j,depth,s + i + "," + j + " ");
                visit[i][j] = false;

            }
            
        }
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0|| nX < 0 || nY >=N || nX >=N) return false;
        return true;
    }

}
