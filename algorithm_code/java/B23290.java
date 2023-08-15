import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.attribute.AclEntryFlag;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B23290 {
    public static void main(String[] args) throws IOException{
        SolveB23289 b23289 = new SolveB23289();
        b23289.init();
        b23289.solve();
    }
}

class Fish implements Comparable<Fish>{



    int y;
    int x;
    int d;
    int i;

    public Fish(int y, int x, int d,int i) {
        this.y = y;
        this.x = x;
        this.d = d;
        this.i = i;
    }

    @Override
    public int compareTo(Fish o) {
        if(o.i == i) return 0;
        return 1;
    }
}

class SolveB23289 {
    int M;
    int S;
    ArrayList<Fish>[][] map = new ArrayList[4][4];
    ArrayList<Fish> fishList = new ArrayList<Fish>();

    int[][] smell = new int[4][4];

    int idx = 0;
    Fish shark;
    int[] dx = {-1,-1,0,1,1,1,0,-1};
    int[] dy = {0,-1,-1,-1,0,1,1,1};
    List<Integer[]> permuList = new ArrayList<>();
    private int answer = 0;

    void init() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        for(int i=0;i<4;i++){
            for (int j = 0; j < 4; j++) {
                map[i][j] = new ArrayList();
            }
        }

        for(int i=0;i<M+1;i++){
            st = new StringTokenizer(bufferedReader.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x=  Integer.parseInt(st.nextToken())-1;
            int d =  0;
            Fish fish = new Fish(y,x,d,idx++);
            if(i == M){
                shark = fish;
                break;
            }
            d = Integer.parseInt(st.nextToken())-1;
            fish.d = d;
            fishList.add(fish);
        }
    }

    void solve(){
        permu(0,"");

        for(int i=0;i<S;i++){
            List<Fish> dupliFishList = duplicate();
            move();
            setMap();
            moveShark();
            for(Fish f : dupliFishList){
                map[f.y][f.x].add(f);
            }
            prepare();
        }

        System.out.println(answer);
    }

    private void prepare() {
        fishList.clear();
        int result = 0;
        for(int i=0;i<4;i++){
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < map[i][j].size(); k++) {
                    fishList.add(map[i][j].get(k));
                    result++;
                }
                map[i][j].clear();
            }
        }
        this.answer= result;
    }

    private void setMap() {
        for(Fish f : fishList){
            map[f.y][f.x].add(f);
        }
    }

    private void permu(int len, String s) {
        if(len == 3){
            Integer[] resultArr = new Integer[3];
            for(int i=0;i<s.length();i++){
                resultArr[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
            }
            permuList.add(resultArr);
            return;
        }
        for(int i=0;i<4;i++){
            permu(len+1,s + (i + ""));
        }
    }

    private void moveShark() {
        int[] sDY = {-1,0,1,0};
        int[] sDX = {0,-1,0,1};

        int maxFish = Integer.MIN_VALUE;
        Integer[] removeDArr = null;

        for(Integer[] dList : permuList) {
            int y = shark.y;
            int x = shark.x;
            boolean flag = true;
            //물고기 얼마나 없어지는지
            boolean[][] visit = new boolean[4][4];
            int curFishRemove = 0;

            for (Integer d : dList) {
                y += sDY[d];
                x += sDX[d];

                if (!isValid(y, x)) {
                    flag = false;
                    break;
                }

                if(!visit[y][x]){
                    curFishRemove += map[y][x].size();
                    visit[y][x] = true;
                }
            }

            if (flag) {
                if (maxFish < curFishRemove) {
                    maxFish = curFishRemove;
                    removeDArr = dList;
                }
            }
        }

        int y = shark.y;
        int x = shark.x;

        for (Integer d : removeDArr) {
            y += sDY[d];
            x += sDX[d];

            if(map[y][x].size() > 0){
                map[y][x].clear();
                smell[y][x] = 3;
            }
        }

        shark.y = y;
        shark.x = x;

        for(int i=0;i<4;i++){
            for (int j = 0; j < 4; j++) {
                if(smell[i][j] == 0) continue;
                smell[i][j]--;
            }
        }
    }

    private void move() {
        int size = fishList.size();
        //한칸 이동
        for(int fI =0;fI<size;fI++){
            Fish f = fishList.get(fI);
            int y = f.y;
            int x = f.x;
            for(int i=f.d;i>f.d-8;i--){
                int tmpD = i < 0 ? i + 8 : i;

                int nY = y + dy[tmpD];
                int nX = x + dx[tmpD];

                if(!isValid(nY,nX)) continue;
                if(shark.y == nY && shark.x == nX) continue;
                if(smell[nY][nX] > 0) {
                    continue;
                }
                f.y = nY;
                f.x = nX;
                f.d = tmpD;
                break;
            }
        }
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nX >= 4 || nY >= 4) return false;
        return true;
    }

    private List<Fish> duplicate() {
        List<Fish> dupliFishList = new ArrayList<>();
        for(Fish f : fishList){
            dupliFishList.add(new Fish(f.y,f.x,f.d,idx++));
        }
        return dupliFishList;
    }
}
