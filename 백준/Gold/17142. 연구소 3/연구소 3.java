import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        SolveB17142 solveB17142 = new SolveB17142();
        solveB17142.init();
        solveB17142.solve();
    }


}

class Virus{
    int y;
    int x;

    int time;
    Virus(int y,int x){
        this.y = y;
        this.x = x;
    }

    Virus(int y,int x,int time){
        this.y = y;
        this.x = x;
        this.time = time;
    }
}


class SolveB17142{
    int N;
    int M;
    int[][] map;
    ArrayList<Virus> virusList = new ArrayList<>();
    private int minTime = Integer.MAX_VALUE;
    private int zeroCount;

    void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 1){
                    virusList.add(new Virus(i,j));
                }
                if(map[i][j] == 0){
                    zeroCount++;
                }

            }
        }
    }


    //0 1 2
    //0 1 3
    //0 1 4...이런식으로 가능한 바이러스 추가해서 최소값 출력하면 되지않을까.
    void solve(){
        boolean[] isVisit = new boolean[virusList.size()];
        bfs(new ArrayList<>(),isVisit,0);
        if(minTime == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minTime);
    }
    //상하좌우
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};

    private void bfs(ArrayList<Integer> virusIdxArr, boolean[] isVisit,int idx) {
        if(virusIdxArr.size() == M){
            //bfs
            boolean[][] visit = new boolean[N][N];
            Queue<Virus> q = new LinkedList<>();
            for (Integer i : virusIdxArr){
                Virus virus = virusList.get(i);
                q.add(virus);
                visit[virus.y][virus.x] = true;
            }

            int value = 0;
            int zeroRemoveCount = 0;

            while (!q.isEmpty()){
                Virus v = q.poll();
                //활성 바이러스

                for (int i = 0; i < 4; i++) {
                    int nY = v.y + dy[i];
                    int nX = v.x + dx[i];
                    if(!isValid(nY,nX,null)) continue;
                    if(map[nY][nX] == 1) continue;
                    if(visit[nY][nX]) continue;

                    if(map[nY][nX] == 0){
                        zeroRemoveCount++;
                        value = v.time + 1;
                    }

                    visit[nY][nX] = true;
                    q.add(new Virus(nY,nX,v.time + 1));
                }
            }

            if(zeroRemoveCount == zeroCount){
                minTime = Math.min(value,minTime);
            }

            return;
        }

        for(int i=idx;i<virusList.size();i++){
            virusIdxArr.add(i);
            bfs(virusIdxArr, isVisit,i+1);
            virusIdxArr.remove(virusIdxArr.size()-1);
        }
    }

    private boolean check(int[][] tmpMap) {
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(tmpMap[i][j] == 0) flag = false;
            }
        }
        return flag;
    }

    private boolean isValid(int nY, int nX, int[][] tmpMap) {
        //벽이 아니어야함 이미 활성화된 바이러스면 안됨
        if(nY < 0 || nX < 0 || nX >= N || nY >= N){
            return false;
        }
        return true;
    }

}