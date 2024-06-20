package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2234 {

    private static int maxRoomSize = Integer.MIN_VALUE;
    private static int maxRoomSizeWithoutWall = Integer.MIN_VALUE;


    class Node{
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        int y;
        int x;
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

        /**
         * 7 4
         * 11 6 11 6 3 10 6
         * 7 9 6 13 5 15 5
         * 1 10 12 7 13 7 5
         * 13 11 10 8 10 12 13
         */
        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(map[i][j][k] + " ");
                }
                System.out.println();
            }
        }


        int roomCount = 0;
        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                //이성에 있는 방의 개수
                if(!visit[i][j]) {
                    roomCount++;
                }
                visit[i][j] = true;
                dfs(i,j,1,false);
            }
        }

        System.out.println(roomCount);
        System.out.println(maxRoomSize);
        System.out.println(maxRoomSizeWithoutWall);

    }

    private static void dfs(int y,int x,int roomSize,boolean removeWall) {
        System.out.println(y + "," + x + " = roomSize" + roomSize);
        if(removeWall){
            maxRoomSizeWithoutWall = Math.max(roomSize, maxRoomSizeWithoutWall);
        }else{
            maxRoomSize = Math.max(roomSize, maxRoomSize);
        }

        for(int i=0;i<4;i++){
            int nY = dy[i] + y;
            int nX = dx[i] + x;
            if(!isValid(nY,nX)) continue;
            if(visit[nY][nX]) continue;
            //벽이있으면
            if(map[y][x][i]) continue;
            visit[nY][nX] = true;
            System.out.println(nY + "," + nX);

            dfs(nY,nX,roomSize+1,removeWall);
        }
    }

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
