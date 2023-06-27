import java.util.Scanner;

public class B20057
{
    public static void main(String[] args) {
        Solve solve = new Solve();
        solve.init();
        solve.solve();



    }
}

class Solve{
    int N;
    int[][] sand;

    //좌하우상
    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};
    int dir = 0;

    int outSand = 0;


    void init(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sand = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sand[i][j] = sc.nextInt();
            }
        }
    }


    //x -> y로 이동하면 y에 있는 모래가 휘날린다 (y에 강조)
    void solve(){
        int cY = N/2;
        int cX = N/2;

        //방향 변경한 횟수
        int dirCount = 0;
        //움직여야하는 횟수
        int moveAmount = 1;
        //움직인 횟수
        int moveCount = 0;
        while (!(cY <= 0 && cX <= 0)){
//            move(cY,cX,moveAmount);
//            System.out.println( cY + "," + cX);
//            print();
            int nY = cY + dy[dir];
            int nX = cX + dx[dir];

            int ySand = sand[nY][nX];
            sand[nY][nX] = 0;
            wind(ySand,cY,cX);
            cY = nY;
            cX = nX;

            moveCount++;

            if(moveCount == moveAmount){
                dir = (dir + 1) % 4;
                dirCount++;
                moveCount = 0;
            }

            if(dirCount == 2){
                moveAmount++;
                dirCount = 0;
            }
        }
        System.out.println(outSand);
    }


    //좌하우상
    //1 1 7 7 10 10 2 2 5
    static int[][] dsy = {
            {-1,1,-1,1,-1,1,-2,2,0,0},
            {0,0,1,1,2,2,1,1,3,2},
            {-1,1,-1,1,-1,1,-2,2,0,0},
            {0,0,-1,-1,-2,-2,-1,-1,-3,-2}
    };

    static int[][] dsx = {
            {0, 0, -1, -1, -2, -2, -1, -1, -3,-2},
            {-1, 1, -1, 1, -1, 1, -2, 2, 0,0},
            {0, 0, 1, 1, 2, 2, 1, 1, 3,2},
            {1, -1, 1, -1, 1, -1, 2, -2, 0, 0}
    };

    static int[] vals = {1,1,7,7,10,10,2,2,5,0};

    private void print() {
//        System.out.println("sweap");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(sand[i][j] + " ");
//
//            }
//            System.out.println();
//        }
//        System.out.println("cur out : " + outSand);
    }

    private void wind(int ySand, int cY, int cX)
    {
        int percent = 0;
        int asand = 0;
        for (int j = 0; j < 10; j++) {
            int nY = cY + dsy[dir][j];
            int nX = cX + dsx[dir][j];

            int resVal = (int) Math.floor(ySand * 0.01 * vals[j]);

            if(isValid(nY,nX)){
                asand += resVal;
                if(j == 9)
                    resVal = (ySand - asand);

                sand[nY][nX] += resVal;
            }else{
                asand += resVal;
                if(j == 9)
                    resVal = (ySand - asand);
                outSand += resVal;
            }
        }



    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= N){
            return false;
        }
        return true;
    }


}
