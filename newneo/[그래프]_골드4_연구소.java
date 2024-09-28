
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    int[][] map;
    int N;
    int M;
    private List<Node> virusList = new ArrayList<>();

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
                if(map[i][j] == 0){
                    canWallList.add(new Node(i,j));
                }
                if(map[i][j] == 2){
                    virusList.add(new Node(i,j));
                }
            }
        }

        setWallByComb(-1,0);
        System.out.println(answer);
    }
    int answer = 0;

    class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y = y;
            this.x = x;
        }
    }
    List<Node> canWallList = new ArrayList<>();
    boolean[][] visit;
    private void setWallByComb(int prev, int depth) {
        if(depth == 3){
            visit = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 2){
                        if(visit[i][j]) continue;
                        visit[i][j] = true;
                        checkVisitByBfs(i,j);
                    }
                }
            }
            int count = 0;
            //count check
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 0 && !visit[i][j]){
                        count++;
                    }
                }
            }
            answer = Math.max(count,answer);
            return;
        }

        for (int i = prev+1; i < canWallList.size(); i++) {
            Node wall = canWallList.get(i);
            map[wall.y][wall.x] = 1;
            setWallByComb(i,depth+1);
            map[wall.y][wall.x] = 0;
        }
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    private void checkVisitByBfs(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(r,c));
        while (!queue.isEmpty()){
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX]) continue;
                visit[nY][nX] = true;
                if(map[nY][nX] == 2 || map[nY][nX] == 0){
                    queue.add(new Node(nY,nX));
                }
            }
        }


    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
    }
}
