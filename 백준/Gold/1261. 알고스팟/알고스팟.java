import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.NotYetBoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import org.w3c.dom.Node;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}


class Solve{

    private int N;
    private int M;
    private char[][] map;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        M = Integer.parseInt(sArr[0]);
        N = Integer.parseInt(sArr[1]);
        map = new char[N + 1][M + 1];
        dp = new int[N +1][M +1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char cur = s.charAt(j);
                map[i][j] = cur;
            }
        }
        dp[0][0] = 0;

        bfs();
    }

    class Node{
        int y;
        int x;
        int count;

        public Node(int y, int x,int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
    int[][] dp;
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    private void bfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> o1.count - o2.count);
        queue.add(new Node(0,0,0));
        boolean[][] visit = new boolean[N][M];
        visit[0][0] = true;
        while (!queue.isEmpty()){
            Node node = queue.poll();

            dp[node.y][node.x] = node.count;

            for (int i = 0; i < 4; i++) {
                int nY = node.y + dy[i];
                int nX = node.x + dx[i];
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX]) continue;
                visit[nY][nX] = true;

                if(map[nY][nX] == '1'){
                    queue.add(new Node(nY,nX,node.count+1));
                }else{
                    queue.add(new Node(nY,nX,node.count));
                }
            }
        }
        System.out.println(dp[N-1][M-1]);
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >=M){
            return false;
        }
        return true;
    }
}
