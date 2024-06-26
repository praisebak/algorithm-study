package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206_2트 {

    private static int N;
    private static int M;
    private static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void solve()throws IOException {
        //최단경로 = bfs
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s =bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }

        bfs(0,0);
    }

    private static void bfs(int y,int x) {
        boolean[][][] visit = new boolean[N][M][2];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(y,x,false,0));
        int count = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.y == N-1 && cur.x == M-1){
                System.out.println(cur.dist+1);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(nY < 0 || nX <0 || nX >=M || nY >=N) continue;

                if(cur.isBreak){
                    if(map[nY][nX] == 1) continue;
                    //이미 부순 벽이면 생략
                    if(visit[nY][nX][1]) continue;
                    visit[nY][nX][1] = true;
                    queue.add(new Node(nY,nX, true,cur.dist+1));
                }else{
                    if(map[nY][nX] == 1){
                        if(visit[nY][nX][1]) continue;
                        queue.add(new Node(nY,nX,true,cur.dist+1));
                        visit[nY][nX][1] = true;
                    }else{
                        if(visit[nY][nX][0]) continue;
                        queue.add(new Node(nY,nX,false,cur.dist+1));
                        visit[nY][nX][0] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException{
        solve();
    }

    private static class Node {
        int y;
        int x;
        boolean isBreak;
        int dist;


        public Node(int y, int x, boolean isBreak,int dist) {
            this.y = y;
            this.x = x;
            this.isBreak = isBreak;
            this.dist = dist;
        }
    }
}
