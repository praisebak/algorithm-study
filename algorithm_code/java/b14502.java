
import java.util.Scanner;


class Solve{

    int N;
    int M;
    int[][] map;
    int MAX = 0;
    //상하좌우
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};

    public Solve(int n, int m, int[][] map)
    {
        this.N = n;
        this.M = m;
        this.map = map;
    }
    void solve(){
        makeWall(0,0,0,map);

        System.out.println(MAX);
    }


    private void mapCopy(int[][] map, int[][] tmpMap)
    {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                tmpMap[i][j] = map[i][j];
            }
        }
    }

    private void makeWall(int r, int c, int depth,int[][] map)
    {
        if(depth == 3){
            int[][] tmpMap = new int[N][M];
            mapCopy(map,tmpMap);
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(tmpMap[i][j] == 2){
                        virusMove(i,j,tmpMap);
                    }
                }
            }
            checkResult(tmpMap);
            return;
        }

        for(int i=r;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    makeWall(i,j,depth+1,map);
                    map[i][j] = 0;
                }
            }
        }
    }


    private void checkResult(int[][] map)
    {
        int sum = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == 0){
                    sum++;
                }
            }
        }
        MAX = Math.max(sum,MAX);
    }

    private void virusMove(int y,int x,int[][] map)
    {
        for(int i=0;i<4;i++){
            int nY = y + dy[i];
            int nX = x + dx[i];
            if(!checkRange(nY,nX)) continue;
            if(map[nY][nX] != 0) continue;
            map[nY][nX] = 2;
            virusMove(nY,nX,map);
        }
    }

    private boolean checkRange(int nY, int nX)
    {
        if(nY == -1 || nY == N || nX == -1 || nX == M) return false;
        return true;
    }

}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N,M;
        int[][] map;
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = sc.nextInt();
            }
        }

        com.study.Solve solve = new com.study.Solve(N,M,map);
        //        solve.test();
        solve.solve();



        //        test("",0);
    }

    private static void test(String result,int depth)
    {
        if(depth == 3){
            System.out.print(result);
            System.out.println();
            return;
        }
        for(int i=0;i<3;i++){
            test(i + " ",depth+1);
        }
    }
}
