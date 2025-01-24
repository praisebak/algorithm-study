import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int N;
    private char[][] map;
    private boolean[][] visit;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        int countA = 0;
        int countB = 0;

        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]) continue;
                countA++;
                bfs(i,j,false, map[i][j]);
            }
        }

        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]) continue;
                countB++;
                bfs(i,j,true, map[i][j]);
            }
        }
        System.out.println(countA + " " + countB);
    }

    class Node{
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};

    public boolean isSameColor(int nY, int nX, boolean isThat, char cur){
        if(nY < 0 || nX < 0 || nY >= N || nX >= N){
            return false;
        }
        if(visit[nY][nX]) return false;

        //적록색맹
        if(isThat){
            if((cur == 'G' || cur == 'R') && (map[nY][nX] == 'G' || map[nY][nX] == 'R')){
                return true;
            }
        }
        return cur == map[nY][nX];
    }

    private void bfs(int i, int j, boolean isColorBlind, char curCh) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(i,j));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nY = cur.y + dy[k];
                int nX = cur.x + dx[k];
                if(isSameColor(nY,nX,isColorBlind,curCh)){
                    queue.add(new Node(nY,nX));
                    visit[nY][nX] = true;
                }
            }
        }
    }
}
