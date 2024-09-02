//https://www.acmicpc.net/problem/1987

import javax.management.QueryEval;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    private int N;
    private int M;
    char[][] map;
    private int max = 0;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        N = Integer.parseInt(s.split(" ")[0]);
        M = Integer.parseInt(s.split(" ")[1]);

        boolean[] visit = new boolean[30];
        map = new char[N][M];



        for(int i=0;i<N;i++){
            s = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char ch = s.charAt(j);
                map[i][j] = ch;
            }
        }


        visit[map[0][0] - 62] = true;
        //depth visit cur
        dfs(1,0,0,visit);

        System.out.println(max);
    }

    int[] dy = {-1,1,0,0,};
    int[] dx = {0,0,-1,1};

    private void dfs(int depth, int r, int c, boolean[] visit) {
        max = Math.max(max,depth);
        for(int i=0;i<4;i++){
            int nY = dy[i] + r;
            int nX = dx[i] + c;

            if(!isValid(nY,nX)) continue;
            char cur = map[nY][nX];
            int idx = cur - 62;
            if(visit[idx]) continue;
            visit[idx] = true;
            dfs(depth+1,nY,nX,visit);
            visit[idx] = false;
        }

    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
    }
}
