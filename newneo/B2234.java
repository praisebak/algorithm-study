package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class B2234 {

    private static int maxRoomSize = Integer.MIN_VALUE;
    private static int maxRoomSizeWithoutWall = Integer.MIN_VALUE;
    private static boolean[][][] breakWallVisit;


    static class Node{
        private final int idx;

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", y=" + y +
                    ", x=" + x +
                    ", isbreak=" + isbreak +
                    ", roomSize=" + roomSize +
                    '}';
        }

        public Node(int y, int x, boolean isBreak, int roomSize,int idx) {

            this.y = y;
            this.x = x;
            this.isbreak = isBreak;
            this.roomSize = roomSize;
            this.idx = idx;
        }

        int y;
        int x;
        boolean isbreak;
        int roomSize;
    }


    /**
     * N,M,상하좌우
     */
    static boolean[][][] map;
    static boolean[][] visit;

    //서북동남
    static int[] dx=  {-1,0,1,0};
    static int[] dy=  {0,-1,0,1};

    static int N;
    static int M;

    private static boolean[][] deepCopyArr(boolean[][] arr){
        boolean[][] newArr = new boolean[N][M];
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < M; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;

    }
    public static void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        N= Integer.parseInt(strings[1]);
        M= Integer.parseInt(strings[0]);
        map = new boolean[N][M][4];
        visit = new boolean[N][M];

        for(int i=0;i<N;i++){
            strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j < strings.length; j++) {
                inputMapAsParse(i,j,strings[j]);
            }
        }

        breakWallVisit = new boolean[N][M][2550];

        int roomCount = 0;
        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                if(breakWallVisit[i][j][0]) continue;
                roomCount++;
                bfs(i,j);
            }
        }


        System.out.println(roomCount);
        System.out.println(maxRoomSize);
        System.out.println(maxRoomSizeWithoutWall == Integer.MIN_VALUE ? maxRoomSize : maxRoomSizeWithoutWall );

    }

    private static void bfs(int y, int x) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(y,x,false,1,0));

        int[] val = new int[2550];
        int idx = 0;
        val[idx] = 1;
        breakWallVisit[y][x][idx] = true;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i=0;i<4;i++) {
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if (!isValid(nY, nX)) continue;

                if(breakWallVisit[nY][nX][cur.idx]) continue;
                if(breakWallVisit[nY][nX][0]) continue;

                //벽있는데 처음부숨
                if (map[cur.y][cur.x][i] && !cur.isbreak){
                    idx++;
                    val[idx]++;
                    queue.add(new Node(nY,nX,true, 1,idx));
                    breakWallVisit[nY][nX][idx] = true;
                    //벽이 없는 케이스라면
                    //벽을 부쉈든 안부쉈든 진행
                }else if(!map[cur.y][cur.x][i]){
                    val[cur.idx]++;
                    queue.add(new Node(nY,nX,cur.isbreak,val[cur.idx],cur.idx));
                    breakWallVisit[nY][nX][cur.idx] = true;
                }
            }
        }

        maxRoomSize = Math.max(maxRoomSize,val[0]);
        for (int i = 1; i < idx; i++) {
            maxRoomSizeWithoutWall = Math.max(maxRoomSizeWithoutWall,val[0] + val[i]);
        }
    }


    //뚫린걸 true잡고
    private static boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
    }


    private static void inputMapAsParse(int i, int j, String string) {
        int remainNum = Integer.parseInt(string);
        int idx = 3;
        for (int num=8; num > 0;num /=2){
            if(remainNum / num == 1){
                map[i][j][idx] = true;
            }
            remainNum %= num;
            idx--;
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
