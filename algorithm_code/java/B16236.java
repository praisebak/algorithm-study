import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
    int y;
    int x;
    int time;
    Point(int y,int x,int time){
        this.y = y;
        this.x = x;
        this.time = time;
    }
}

public class B16236 {
    public static void main(String[] args) throws IOException{
        SolveB16236 solveB16236 = new SolveB16236();
        solveB16236.init();
        solveB16236.solve();

    }
}

class SolveB16236{

    int[][] map;
    Point curPoint;
    int N;
    private int minTime = Integer.MAX_VALUE;

    void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(s.nextToken());
        map = new int[N][N];
        visit = new boolean[N][N];
        for (int i=0;i<N;i++){
            s = new StringTokenizer(bufferedReader.readLine());
            int sCountToken = s.countTokens();
            for (int j = 0; j < sCountToken; j++) {
                map[i][j] = Integer.parseInt(s.nextToken());
                if(map[i][j] == 9){
                    curPoint = new Point(i,j,0);
                    map[i][j] = 0;
                }
            }
        }
    }


    //상좌하우
    int[] dY = {-1,0,1,0};
    int[] dX = {0,-1,0,1};
    boolean[][] visit;



    void solve(){
        int time = 0;
        int sharkSize = 2;
        int sizeEatCount = 0;

        Queue<Point> q = new LinkedList<>();
        q.add(curPoint);

        while (true){
            //visit 겸 거리
            int[][] dist = new int[N][N];
            LinkedList<Point> fishes = new LinkedList<>();

            while (!q.isEmpty()){
                Point p = q.poll();
                for(int i=0;i<4;i++){
                    int nY = p.y + dY[i];
                    int nX = p.x + dX[i];
                    if(!isValid(nY,nX)) continue;
                    if(dist[nY][nX] != 0) continue;
                    if(map[nY][nX] <= sharkSize){
                        dist[nY][nX] = dist[p.y][p.x] + 1;
                        q.add(new Point(nY,nX,dist[nY][nX]));

                        if(sharkSize > map[nY][nX] && map[nY][nX] != 0){
                            fishes.add(new Point(nY,nX,dist[nY][nX]));

                        }
                    }

                }
            }

            if(fishes.size() == 0) {
                System.out.println(time);
                break;
            }

            Point curFish = null;
            for(Point p : fishes){
                if(curFish == null){
                    curFish = p;
                }else{
                    if(curFish.time > p.time){
                        curFish = p;
                    }else if(curFish.time == p.time){
                        //더 위에 있다
                        if(curFish.y > p.y){
                            curFish = p;
                        }else if(curFish.y == p.y && curFish.x > p.x){
                            curFish = p;
                        }
                    }
                }
            }

            time += curFish.time;
            sizeEatCount++;
            map[curFish.y][curFish.x] = 0;
            if(sizeEatCount == sharkSize){
                sharkSize++;
                sizeEatCount = 0;
            }
            q.add(new Point(curFish.y, curFish.x, 0));
        }
    }


    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= N){
            return false;
        }
        return true;
    }


}
