import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Quest{
    int d;
    int s;
    Quest(int d,int s){
        this.d = d;
        this.s = s;
    }
}

class SolveB21611
{
    int N;
    int M;
    int[][] map;
    int[][][] wall;
    ArrayList<Quest> questList = new ArrayList<Quest>();
    ArrayList<Point> list = new ArrayList<>();

    //좌하우상
    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    void init() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        wall = new int[N][N][4];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            questList.add(new Quest(d,s));
        }
        //벽주가
        addWall();
    }

    private void print(){
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void addWall()
    {
        int y  = N/2;
        int x = N/2;
        int moveGroup = 1;
        map[y][x] = 9;

        int moveCount = 0;
        int modeChange = 2;
        int modeCount = 0;

        wall[y][x][1] = 1;
        //좌하우상
        int moveD = 0;

        while(true){
            moveCount++;

            y += (dy[moveD]);
            x += (dx[moveD]);

            if(moveCount == moveGroup){
                modeCount++;
                moveCount = 0;
                moveD =( moveD + 1 ) % 4;

                if(modeChange == modeCount){
                    modeCount = 0;
                    moveGroup++;
                }
            }
            list.add(new Point(y,x));
            if(y == 0 && x == 0) break;
        }
    }

    void solve()
    {
        //상하좌우
        int[] ddx = {0,0,-1,1};
        int[] ddy = {-1,1,0,0};

        for(Quest q : questList){
            int d = q.d-1;

            int y = N/2;
            int x = N/2;

            for(int i=0;i<q.s;i++){
                int nY = y + ddy[d];
                int nX = x + ddx[d];
                if(nY < 0 || nX < 0 || nX >= N || nY >= N) break;
                //구슬 부수기
                map[nY][nX] = 0;
                y = nY;
                x = nX;
            }

            move();
            while(boom()){
                move();
            }
            combine();
//            print();
        }
        System.out.println(answer);

    }

    private void combine()
    {
        ArrayList<ArrayList<Point> > combineGroupList = new ArrayList<>();
        ArrayList<Point> combineGroup = new ArrayList<>();
        int prevV = -1;

        for(int i=0;i<list.size();i++){
            Point curP = list.get(i);
            int curV = map[curP.y][curP.x];

            if(curV == 0) break;

            if(prevV == -1 || curV == prevV){
                combineGroup.add(new Point(curP.y,curP.x));
            }
            else{
                combineGroupList.add(combineGroup);
                combineGroup = new ArrayList<>();
                combineGroup.add(curP);
            }
            prevV = curV;
        }

        if(combineGroup.size() > 0) combineGroupList.add(combineGroup);

        ArrayList<Point> newList = new ArrayList<>();

        int idx = 0;
        for(ArrayList<Point> combineList : combineGroupList){
            Point p = combineList.get(0);
            int A = combineList.size();
            int B = map[p.y][p.x];
            for(int i=0;i<2;i++){
                if(newList.size() >= (N * N) -1) break;

                Point nP= list.get(idx++);
                Point newPoint = new Point(nP.y,nP.x);
                if(i == 0)
                    newPoint.v = A;
                else
                    newPoint.v = B;
                newList.add(newPoint);
            }
        }

        for (int i=idx;i < list.size();i++){
            Point p = list.get(i);
            map[p.y][p.x] = 0;
        }

        for(Point p : newList){
            map[p.y][p.x] = p.v;
        }
    }

    class Point{
        int y;
        int x;
        int v;
        Point(int y,int x)
        {
            this.y = y;
            this.x = x;
        }

    }

    private void move()
    {
        for(int i=0;i<list.size()-1;i++){
            Point p=list.get(i);
            if(map[p.y][p.x] == 0){
                Point n = find(i);
                map[p.y][p.x] = map[n.y][n.x];
                map[n.y][n.x] = 0;
            }
        }
    }

    private Point find(int i)
    {
        Point result = new Point(0,0);
        i++;
        for(;i<list.size();i++){
            Point point = list.get(i);
            if(map[point.y][point.x] != 0){
                result = point;
                break;
            }
        }
        return result;
    }

    long answer = 0;

    private boolean boom(){
        boolean flag = false;
        ArrayList<Point> boomList = new ArrayList<>();
        for(int i=0;i<list.size()-1;i++){

            Point curBoom = list.get(i);
            int curV = map[curBoom.y][curBoom.x];
            if(curV == 0) continue;

            boomList.add(curBoom);

            int j = i + 1;
            for(;j< list.size()-1;j++){
                Point next = list.get(j);
                int nextVal = map[next.y][next.x];

                if(nextVal == curV){
                    boomList.add(next);
                }else{
                    break;
                }
            }

            if(boomList.size() >= 4){
                i = j-2;
                flag = true;
                for(Point boom : boomList){
                    map[boom.y][boom.x] = 0;
                }
                answer += (long) boomList.size() * curV;
            }
            boomList = new ArrayList<>();
        }
        return flag;

    }
}

public class B21611
{

    public static void main(String[] args) throws IOException
    {
        SolveB21611 solve = new SolveB21611();
        solve.init();
        solve.solve();
    }
} 
