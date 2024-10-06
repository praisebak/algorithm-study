import jdk.jshell.execution.JdiExecutionControlProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{
    class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    int N;
    int M;
    int K;
    Node obj;
    Node start;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        K = Integer.parseInt(sArr[2]);
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        visit = new boolean[N][M];
        obj = new Node(0,M-1);
        start = new Node(N-1,0);
        //bfs는 모든경우의수를 커버하지못한다. 맞나?

        //cur,depth
        if(map[start.y][start.x] == 'T') {
            System.out.println(0);
            return;
        }

        visit[start.y][start.x] = true;
        dfs(start,0);

        System.out.println(answer);


    }
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};


    boolean[][] visit;
    long answer = 0;
    char[][] map;

    private void dfs(Node curNode,int depth) {
        if(depth == K-1){
            if(curNode.y == obj.y  && curNode.x == obj.x) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nY = curNode.y + dy[i];
            int nX = curNode.x + dx[i];
            if(!isValid(nY,nX)) continue;
            if(map[nY][nX] == 'T') continue;
            if(visit[nY][nX]) continue;
            visit[nY][nX] = true;
            dfs(new Node(nY,nX),depth+1);
            visit[nY][nX] = false;
        }
    }

    private boolean isValid(int nY, int nX) {
        if(nY< 0 || nX < 0 || nY >= N || nX >=M) return  false;
        return true;
    }


}
