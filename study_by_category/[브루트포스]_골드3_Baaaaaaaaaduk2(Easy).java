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
    private int N;
    private int M;
    private char[][] map;
    private boolean isEmptySpaceExists = false;

    public void solve() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = br.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = s[j].charAt(0);
            }
        }


        comb(0,0,0);

        System.out.println(answer);
    }
    int answer = 0;

    private void comb(int depth,int prevR,int prevC) {
        if(depth == 2){
            playGame();
            return;
        }

        for(int i=prevR;i<N;i++){
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '0'){
                    char tmp = map[i][j];
                    map[i][j] = '1';
                    comb(depth+1,i,j);
                    map[i][j] = tmp;
                }
            }
        }
    }

    private void playGame() {
        boolean[][] visit = new boolean[N][M];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '2' && !visit[i][j]) {
                    isEmptySpaceExists = false;
                    visit[i][j] = true;
                    count = 1;
                    dfs(i,j,visit);

                    if(!isEmptySpaceExists) sum += count;
                }
            }
        }
//        if(sum > answer){
//            System.out.println(sum);
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    System.out.print(map[i][j] + " " );
//
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
        answer = Math.max(sum,answer);

    }

    int[] dy = {-1,1,0,0,};
    int[] dx = {0,0,-1,1};
    int count = 0;

    private void dfs(int r, int c, boolean[][] visit) {
        for (int i = 0; i < 4; i++) {
            int nY = dy[i] + r;
            int nX = dx[i] + c;
            if(!isValid(nY,nX)) continue;
            if(map[nY][nX] == '0'){
                isEmptySpaceExists = true;
            }

            if(visit[nY][nX]) continue;
            visit[nY][nX] = true;

            if(map[nY][nX] == '2'){
                count++;
                dfs(nY,nX,visit);
            }
        }
    }

    private boolean isValid(int nY, int nX) {
        if(nY <0 || nX < 0|| nY >= N || nX >= M) return false;
        return true;
    }
}Baaaaaaaaaduk2 (Easy)
