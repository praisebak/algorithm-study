package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B2638_치즈 {


    private int[][] visit;
    private int[] dx = {-1,1,0,0};
    private int[] dy = {0,0,-1,1};
    private int time = 0;


    public static void main(String[] args) throws IOException {
        B2638 b2638 = new B2638();
        b2638.solve();
    }
    class Node{
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        int y;
        int x;

    }


    int N;
    int M;
    int[][] map;
    int cheeseCount = 0;
    private void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        map = new int[N][M];
        for(int i=0;i<N;i++){
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
                if(map[i][j] == 1){
                    cheeseCount++;
                }
            }
        }
//        System.out.println("start 치즈카운트 = " + cheeseCount);

        Queue<Node> queue = new LinkedList<>();
        while (cheeseCount != 0) {
            queue.add(new Node(0,0));
            visit = new int[N][M];

            while (!queue.isEmpty()){
                Node cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nY = cur.y + dy[i];
                    int nX = cur.x + dx[i];

                    if (!isValid(nY, nX)) continue;
                    //방문한 적 있는데
                    if (visit[nY][nX] != 0) {
                        //치즈인 경우
                        if (map[nY][nX] == 1) {
                            visit[nY][nX]++;
                        }
                        //방문한 적 없으면
                    } else if (visit[nY][nX] == 0) {
                        //방문표시함
                        visit[nY][nX]++;
                        if(map[nY][nX] == 0){
                            queue.add(new Node(nY,nX));
                        }
                    }
                }
            }

            for(int i=0;i<N;i++){
                for (int j = 0; j < M; j++) {
                    //치즈인데
                    if(map[i][j] == 1){
                        if(visit[i][j]  >= 2){
                            map[i][j] = 0;
                            cheeseCount--;
                        }
                    }
                }
            }

            //한라운드 끝나면
//            print();
//            System.out.println("치즈 카운트 = " + cheeseCount);
            time++;
        }
        System.out.println(time);
    }

    private void print() {
        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nY >=N || nX >= M) return false;
        return true;
    }
}
