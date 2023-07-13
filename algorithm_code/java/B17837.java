import java.nio.ShortBuffer;
import java.util.*;

public class B17837 {
    public static void main(String[] args) {
        SolveB17837 solveB17837 = new SolveB17837();
        solveB17837.init();
        solveB17837.solve();
    }
}

class SolveB17837{
    //우 좌 상 하
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,-1,1};
    int N;
    int K;

    int[][] map;

    ArrayList<Horse> horses = new ArrayList<>();
    ArrayList<Horse>[][] list;

    void init(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        map = new int[N+1][N+1];
        list = new ArrayList[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
                list[i][j] = new ArrayList<>();
            }
        }

        for (int j = 0; j < K; j++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int dir = sc.nextInt()-1;
            Horse horse = new Horse(r,c,dir,j);
            list[r][c].add(horse);
            horses.add(horse);
        }

    }


    void solve(){
        int turn = 0;
        while (++turn <= 1000){
            boolean flag = false;
            for(int idx = 0;idx<K;idx++){

                Horse curHorse = horses.get(idx);
                int r = curHorse.y;
                int c = curHorse.x;
                int dir = curHorse.dir;
                int nY = dy[dir] + r;
                int nX = dx[dir] + c;

                //파란색
                if(!isValid(nY,nX) || map[nY][nX] == 2){
                    if(dir == 0 || dir == 2){
                        curHorse.dir = dir + 1;
                    }else{
                        curHorse.dir = dir - 1;
                    }

                    nY = r + dy[curHorse.dir];
                    nX = c + dx[curHorse.dir];

                    if(!isValid(nY,nX) || map[nY][nX] == 2){
                        continue;
                    }

                }

                //sublist로 현재 말 포함 위에있는  말들 가져옴
                int curHorseIdx = 0;
                for (int horseIdx = 0; horseIdx < list[r][c].size(); horseIdx++) {
                    if(list[r][c].get(horseIdx).idx == idx){
                        curHorseIdx = horseIdx;
                        break;
                    }
                }

                //뒷부분
                List<Horse> horseList =  list[r][c].subList(curHorseIdx,list[r][c].size());
                list[r][c] = new ArrayList<>(list[r][c].subList(0,curHorseIdx));

                //흰색
                if(map[nY][nX] == 0){
                    list[nY][nX].addAll(horseList);
                    for (int i = 0; i < horseList.size(); i++) {
                        Horse nH = horseList.get(i);
                        nH.y = nY;
                        nH.x = nX;
                    }
                    
                    //빨간색
                }else if(map[nY][nX] == 1){
                    List<Horse> reversedHorseList = new ArrayList<>();
                    for (int i = horseList.size()-1; i != -1 ; i--) {
                        reversedHorseList.add(horseList.get(i));
                    }

                    for (int i = 0; i < reversedHorseList.size(); i++) {
                        Horse nH = reversedHorseList.get(i);
                        nH.y = nY;
                        nH.x = nX;
                    }

                    list[nY][nX].addAll(reversedHorseList);
                }


                if(list[nY][nX].size() >= 4){
                    flag = true;
                    break;
                }

            }
            if(flag) break;

        }
        if(turn > 1000) turn = -1;

        System.out.println(turn);



    }

    private boolean isValid(int nY, int nX) {
        if(nY < 1 || nX < 1 || nY > N || nX > N) return false;
        return true;
    }

}

class Horse{
    int y;
    int x;
    int dir;
    int idx;

    Horse(int y,int x,int dir,int idx){
        this.y =y;
        this.x=x;
        this.dir=dir;
        this.idx=idx;
    }
}
