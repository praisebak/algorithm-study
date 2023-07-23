import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B21609 {
    public static void main(String[] args) throws IOException{
        SolveB21609 solveB21609 = new SolveB21609();
        solveB21609.init();
        solveB21609.solve();
    }
}

class SolveB21609{
    class Point{
        int y;
        int x;
        int rainbow;

        int standardRow;
        int standardCal;

        Point(int y,int x){
            this.y = y;
            this.x = x;
            rainbow = 0;
        }
    }


    int N;
    int M;

    ArrayList<Point>[] blockGroup;

    boolean flag = false;

    boolean[][] isVisit;

    void solve(){
        int score = 0;
        while (!flag){
            for (int i = 1; i <= M; i++) {
                blockGroup[i] = new ArrayList<>();
            }
            isVisit = new boolean[N][N];

            for(int i=0;i<N;i++){
                for (int j = 0; j < N; j++) {
                    if(map[i][j] > 0 && !isVisit[i][j]){
                        bfs(map[i][j],i,j);
                    }
                }
            }

            if(!flag){
                break;
            }

            flag = false;

            int biggestBlockGroup = 1;
            int size = blockGroup[biggestBlockGroup].size();
            Point biggestBlockGroupPoint = size != 0 ?blockGroup[biggestBlockGroup].get(0) : null;

            //크기가 가장큰 블록그룹
            for(int i=2;i<=M;i++){
                if(blockGroup[i].size() == 0) continue;

                if(blockGroup[i].size() > size){
//                    System.out.println(biggestBlockGroup + "이기고 " + i + "선택됨");
                    size = blockGroup[i].size();
                    biggestBlockGroup = i;
                    biggestBlockGroupPoint = blockGroup[i].get(0);

                } else if(blockGroup[i].size() == size){
                    if(biggestBlockGroupPoint.rainbow > blockGroup[i].get(0).rainbow) {
                        continue;
                    }else if(biggestBlockGroupPoint.rainbow == blockGroup[i].get(0).rainbow) {
                        if(blockGroup[i].get(0).standardRow < biggestBlockGroupPoint.standardRow) {
                            continue;
                        }
                        else if(blockGroup[i].get(0).standardRow == biggestBlockGroupPoint.standardRow) {
                            if(blockGroup[i].get(0).standardCal < biggestBlockGroupPoint.standardCal) {
                                continue;
                            }
                        }
                    }

//                    System.out.println(biggestBlockGroup + "이기고 " + i + "선택됨2");
                    biggestBlockGroup = i;
                    biggestBlockGroupPoint = blockGroup[i].get(0);
                    size = blockGroup[biggestBlockGroup].size();
                }
            }

            score += Math.pow(blockGroup[biggestBlockGroup].size(),2);
            //모두 삭제한다
            for (Point p : blockGroup[biggestBlockGroup]) {
                map[p.y][p.x] = -2;
            }

            //중력
            gravity();

            //반시계 90도
            rotate();

            //중력
            gravity();


//            System.out.println(biggestBlockGroup);
//            print();
//            System.out.println(score);


        }

        System.out.println(score);


    }
    void print(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == -2){
                    System.out.print( "x ");
                }else if(map[i][j] != -1){
                    System.out.print(map[i][j]+ " ");
                }else{
                    System.out.print("- ");

                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void rotate() {
        int[][] tmp = new int[N][N];
        for(int i=0;i<N;i++){
            for (int j = 0; j < N; j++) {
                tmp[i][j] = map[j][N-1-i];
            }
        }

        map = tmp;



    }

    //중력 작용
    void gravity() {
        for (int L = N - 1; L > 0; L--) {
            for (int i = N - 1; i > 0; i--) {
                for (int j = 0; j < N; j++) {
                    int up = map[i - 1][j];
                    int cur = map[i][j];
                    if (cur != -2) continue;
                    if (up == -1) continue;
                    map[i][j] = up;
                    map[i-1][j] = -2;
                }
            }
        }
    }

    //상하좌우
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};

    private void bfs(int selectedColor,int row,int cal) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row,cal));
        isVisit[row][cal] = true;
        ArrayList<Point> groupPoint = new ArrayList<>();
        groupPoint.add(new Point(row,cal));

        boolean[][] rainbowVisit = new boolean[N][N];

        int standardRow = row;
        int standardCal = cal;
        int rainbowCount = 0;

        while (!queue.isEmpty()){
            Point cp = queue.poll();
            for(int i=0;i<4;i++){
                int nY = cp.y + dy[i];
                int nX = cp.x + dx[i];

                if(!isValid(nY,nX)) continue;
                if(isVisit[nY][nX]) continue;
                if(rainbowVisit[nY][nX]) continue;
                if(map[nY][nX] == selectedColor || map[nY][nX] == 0){
                    queue.add(new Point(nY,nX));
                    groupPoint.add(new Point(nY,nX));
                    if(map[nY][nX] != 0){
                        standardRow = Math.min(standardRow,nY);
                        standardCal = Math.min(standardCal,nX);
                    }
                    if(map[nY][nX] == 0){
                        rainbowCount++;
                        rainbowVisit[nY][nX] = true;
                    }else{
                        isVisit[nY][nX] = true;
                    }
                }
            }
        }

        if(groupPoint.size() >= 2){
            ArrayList<Point> selectedBlockGroup = blockGroup[selectedColor];
            if(selectedBlockGroup.size() < groupPoint.size()){
                blockGroup[selectedColor] = new ArrayList<>(groupPoint);
                selectedBlockGroup = blockGroup[selectedColor];
                selectedBlockGroup.get(0).rainbow = rainbowCount;
                selectedBlockGroup.get(0).standardCal = standardCal;
                selectedBlockGroup.get(0).standardRow = standardRow;
                flag = true;
            }
            else if(selectedBlockGroup.size() == groupPoint.size()){
                //크기가 같으면 rainbow 수에서 이전게 많은게 아니라면 큰 행 큰 열이 유리하므로 나중에 생긴 것을 추가한다
                if(selectedBlockGroup.get(0).rainbow > rainbowCount) return;

                blockGroup[selectedColor] = new ArrayList<>(groupPoint);
                selectedBlockGroup = blockGroup[selectedColor];
                selectedBlockGroup.get(0).rainbow = rainbowCount;
                selectedBlockGroup.get(0).standardCal = standardCal;
                selectedBlockGroup.get(0).standardRow = standardRow;
                flag = true;
            }
        }

    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= N){
            return false;
        }
        return true;
    }

    int[][] map;
    int color;

    void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        color = M;
        blockGroup = new ArrayList[color+1];
        for(int i=1;i<=M;i++){
            blockGroup[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}


