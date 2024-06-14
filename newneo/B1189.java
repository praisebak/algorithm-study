package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1189 {

    char[][] map;

    class Node{
        public Node(int y, int x,int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        int y;
        int x;

        int dist;

    }

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int R;
    int C;
    int K;
    int count = 0;
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String[] sArr = s.split(" ");
        R = parseStringToInteger(sArr[0]);
        C = parseStringToInteger(sArr[1]);
        K = parseStringToInteger(sArr[2]);
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            s = bufferedReader.readLine();
            for (int j = 0; j < C; j++) {
                map[i] = s.toCharArray();
            }
        }

        boolean[][] visit = new boolean[R][C]
        visit[R-1][0] = true;

        //R,C,depth
        dfs(R-1,0,1,visit);
        System.out.println(count);


    }

    private void dfs(int r, int c, int depth,boolean[][] visit) {

        if(r == 0 && c == C-1){
            if(depth == K) {
                count++;
            }
            return;
        }

        for(int i=0;i<4;i++){
            int nY = r + dy[i];
            int nX = c + dx[i];
            if(!isValid(nY,nX)) continue;
            if(visit[nY][nX]) continue;
            if(map[nY][nX] == 'T') continue;

//            System.out.println(nY  + "," + nX + " depth = " + depth);
            visit[nY][nX] = true;
            dfs(nY,nX,depth+1,visit);
            visit[nY][nX] = false;
        }


    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= R || nX >= C){
            return false;
        }
        return true;
    }

    private int parseStringToInteger(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws IOException {
        B1189 b1189 = new B1189();
        b1189.solve();
    }
}
