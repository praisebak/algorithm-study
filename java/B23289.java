import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        SolveB23289 solveB23289 = new SolveB23289();
        solveB23289.init();
        solveB23289.solve();
    }
}

class SolveB23289{

    class Pair{
        int y;
        int x;
        int v;
        int d;
        Pair(int y,int x,int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }

    }

    int R;
    int C;
    int K;

    int[][] temperature;
    List<Queue<Pair>> heartQueList = new ArrayList<>();
    //오왼상하
    int[] hDY = {0,0,-1,1};
    int[] hDX = {1,-1,0,0};

    //퍼져야하는 방향
    //온풍기 방향.(상,하 혹은 왼,오).거쳐야하는 방향
    int[][][] spreadDY = new int[4][2][2];
    int[][][] spreadDX = new int[4][2][2];

    //조사해야하는칸
    List<Pair> objPairList = new ArrayList<>();

    int chocolate= 0;

    List<Integer>[][] wall;
    private ArrayList<Pair> heatList;


    //오왼상하
    void init() throws IOException
    {

        int[][] dY = new int[][]{{-1,-1},{1,1}};
        int[][] dX = new int[][]{{0,1},{0,1}};
        spreadDY[0] = dY;
        spreadDX[0] = dX;

        dY = new int[][]{{-1,-1},{1,1}};
        dX = new int[][]{{0,-1},{0,-1}};
        spreadDY[1] = dY;
        spreadDX[1] = dX;

        dY = new int[][]{{0,-1},{0,-1}};
        dX = new int[][]{{-1,-1},{1,1}};
        spreadDY[2] = dY;
        spreadDX[2] = dX;

        //아래 보고있으면
        dY = new int[][]{{0,1},{0,1}};
        dX = new int[][]{{-1,-1},{1,1}};
        spreadDY[3] = dY;
        spreadDX[3] = dX;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(stringTokenizer.nextToken());
        C =  Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        temperature = new int[R][C];
        wall = new ArrayList[R][C];
        heatList = new ArrayList<>();

        for(int i=0;i<R;i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < C; j++)
            {
                wall[i][j] = new ArrayList<>();
                int v = Integer.parseInt(stringTokenizer.nextToken());
                if(v == 5) {
                    objPairList.add(new Pair(i,j,0));
                }else if(v > 0) {
                    heatList.add(new Pair(i,j,v-1));
                }
            }
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        //wall setting
        int W = Integer.parseInt(stringTokenizer.nextToken());
        //wall은 이동할려는 곳과 이동하기전 값이 0이아닌 같은 값이 있으면 벽이 있어서 못가는 것으로
        for(int i=1;i<=W;i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int y = Integer.parseInt(stringTokenizer.nextToken())-1;
            int x = Integer.parseInt(stringTokenizer.nextToken())-1;
            int t = Integer.parseInt(stringTokenizer.nextToken());
            if(t == 0){
                wall[y][x].add(i);
                wall[y-1][x].add(i);
            }else{
                wall[y][x].add(i);
                wall[y][x+1].add(i);
            }
        }
    }


    void solve(){
        do{
            initQueList();
            spreadHeater();
            temperatureAdjust();
            minusBorder();
            chocolate++;
            if(chocolate == 101) break;
        }while (!check());

        System.out.println(chocolate);
    }

    private void initQueList()
    {
        heartQueList = new ArrayList<>();
        for(Pair p : heatList){
            Queue<Pair> que = new LinkedList<>();
            int nY = p.y + hDY[p.d];
            int nX = p.x + hDX[p.d];
            Pair newPair = new Pair(nY,nX,p.d);
            newPair.v = 5;
            que.add(newPair);
            heartQueList.add(que);
        }
    }

    private void minusBorder()
    {
        //0,0 -> R,0 -> R,C -> 0,C
        for(int i=0;i<R;i++){
            if(temperature[i][0] != 0)
                temperature[i][0]--;
        }
        for(int i=1;i<C;i++){
            if(temperature[R-1][i] != 0)
                temperature[R-1][i]--;
        }
        for(int i=R-2;i>=0;i--){
            if(temperature[i][C-1] != 0)
                temperature[i][C-1]--;
        }
        for(int i=C-2;i>0;i--){
            if(temperature[0][i] != 0)
                temperature[0][i]--;
        }
    }

    int[][] tmp;

    private void temperatureAdjust()
    {

        this.tmp = new int[R][C];
        for(int i=0;i<R;i++){
            for (int j = 0; j < C; j++)
            {
                bfs(i,j);
            }
        }


        for(int i=0;i<R;i++){
            for (int j = 0; j < C; j++)
            {
                temperature[i][j] += tmp[i][j];
                if(temperature[i][j] < 0) temperature[i][j] = 0;
            }
        }
    }

    boolean isThereWall(int y,int x,int v){
        for(Integer i : wall[y][x]){
            if(i == v){
                return true;
            }
        }
        return false;
    }

    private void bfs(int y, int x)
    {
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(y,x,0));

        int dir = 0;
        //오하로만 이동할려고
        for(int i=0;i<2;i++)
        {
            int nY = y + hDY[dir];
            int nX = x + hDX[dir];
            dir = 3;
            if(!isValid(nY,nX)) {
                continue;
            }
            boolean flag = false;
            for(int v : wall[y][x]){
                if(isThereWall(nY,nX,v)){
                    flag = true;
                    break;
                }
            }
            if(flag) continue;

            int v= Math.abs(temperature[y][x] - temperature[nY][nX]);
            int val = (v / 4);
            if (val == 0) continue;
            if(temperature[y][x] > temperature[nY][nX]){
                tmp[y][x] += val * -1;
                tmp[nY][nX] += val;
//                temperature[y][x] -= val;
//                temperature[nY][nX] += val;
            }else if(temperature[y][x] < temperature[nY][nX]){
                tmp[nY][nX] += val * -1;
                tmp[y][x] += val;
//                temperature[nY][nX] -= val;
//                temperature[y][x] += val;
            }
//            if(temperature[nY][nX] < 0) temperature[nY][nX] = 0;
//            if(temperature[y][x] < 0) temperature[y][x] = 0;
        }

    }

    private void spreadHeater()
    {
        for(Queue<Pair> que : heartQueList){
            //같은 온풍기가 방문한 곳이면 값 추가 안함
            boolean[][] visit = new boolean[R][C];

            int idx = 0;
            while (!que.isEmpty()){
                Pair p = que.poll();
                if(idx++ == 0){
                    temperature[p.y][p.x] += 5;
                }
                moveStraight(p,visit,que);
                moveGoThrough(p,visit,que);
            }
        }
    }

    private boolean isValid(int y,int x){
        if(y < 0 || x < 0 || y >= R || x >= C){
            return false;
        }
        return true;
    }

    private void moveGoThrough(Pair p, boolean[][] visit,Queue<Pair> que)
    {
        if(p.v <= 1) return;

        for(int i=0; i<2;i++){
            int pY = p.y;
            int pX = p.x;
            for (int j = 0; j < 2; j++)
            {
                int nY = spreadDY[p.d][i][j] + p.y;
                int nX = spreadDX[p.d][i][j] + p.x;
                if(!isValid(nY,nX)){
                    break;
                }

                if(wallCheck(pY,pX,nY,nX)){
                    break;
                }
                //다 통과
                if(j == 1){
                    if(!visit[nY][nX]){
                        visit[nY][nX] = true;
                        Pair newPair = new Pair(nY,nX,p.d);
                        newPair.v = p.v-1;
                        que.add(newPair);
                        temperature[nY][nX] += (newPair.v);
                    }
                }else{
                    pY = nY;
                    pX = nX;
                }

            }
        }

    }

    private boolean wallCheck(int y, int x, int nY, int nX)
    {
        for(int v : wall[y][x]){
            if(isThereWall(nY,nX,v)){
                return true;
            }
        }
        return false;
    }

    private void moveStraight(Pair p, boolean[][] visit,Queue<Pair> que)
    {
        if(p.v <= 1) return;
        int nY = p.y + this.hDY[p.d];
        int nX = p.x + this.hDX[p.d];

        if(isValid(nY,nX)){
            if(visit[nY][nX]) return;

            for (int v : wall[p.y][p.x]) {
                if(isThereWall(nY,nX,v)){
                    return;
                }
            }

            Pair newP = new Pair(nY,nX,p.d);
            newP.v = p.v-1;
            temperature[nY][nX] += newP.v;
            visit[nY][nX] = true;
            que.add(newP);
        }
    }

    private boolean check()
    {
        boolean flag = true;
        for(Pair p : objPairList){
            if(temperature[p.y][p.x] < K){
                flag = false;
                break;
            }
        }
        return flag;
    }

}
