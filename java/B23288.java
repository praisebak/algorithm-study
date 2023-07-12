import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class B23288 {

    public static void main(String[] args) throws IOException {
        SolveB23288 b23288 = new SolveB23288();
        b23288.init();
        b23288.solve();
    }


}


class SolveB23288{
    ArrayList<Integer> hori = new ArrayList<>();
    ArrayList<Integer> virt = new ArrayList<>();

    int N;
    int M;
    int K;
    int[][] score;

    void init() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in) );
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        score = new int[N+1][M+1];

        hori.addAll(List.of(new Integer[]{4, 1, 3}));
        virt.addAll(List.of(new Integer[]{2, 1, 5,6}));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                score[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }
    }

    //우 하 좌 상
    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    int curR = 1;
    int curC = 1;
    int dir = 0;

    int sum = 0;
    void solve(){
        while (K != 0){
            K--;
            int nextR = curR + dy[dir];
            int nextC = curC + dx[dir];
            //만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
            if(!isValid(nextR,nextC)){
                dir = (dir + 2) % 4;
                nextR = curR + dy[dir];
                nextC = curC + dx[dir];
            }

            curR = nextR;
            curC = nextC;

            //굴렸으니 갱신
            //우 하 좌 상
            if(dir == 0){
                hori.add(0,hori.remove(2));
                int tmp = virt.get(3);
                virt.set(3,hori.get(0));
                hori.set(0,tmp);
                virt.set(1,hori.get(1));

            }else if(dir == 1){
                virt.add(0,virt.remove(3));
                hori.set(1,virt.get(1));
            }else if(dir == 2){
                hori.add(hori.remove(0));
                int tmp = virt.get(3);
                virt.set(3,hori.get(2));
                hori.set(2,tmp);

                virt.set(1,hori.get(1));
            }else{
                virt.add(3,virt.remove(0));
                hori.set(1,virt.get(1));
            }

            int a = virt.get(3);
            int b = score[nextR][nextC];


            if(a > b){
                dir = (dir + 1) % 4;
            }else if(a < b){
                dir = (dir - 1 + 4) % 4;
            }

            int curScore = score[nextR][nextC];
            boolean[][] isVisit = new boolean[N+1][M+1];
            isVisit[nextR][nextC] = true;


            int sameNumCount = dfs(nextR,nextC,b,isVisit,0);
            sameNumCount = tmpSameNumCount;
            tmpSameNumCount = 0;
            curScore = (sameNumCount+1) * curScore;
            sum += curScore;
        }

        System.out.println(sum);


    }

    int tmpSameNumCount = 0;

    private int dfs(int r, int c, int b, boolean[][] visit,int sameNumCount) {

        for(int i=0;i<4;i++){
            int nR = r + dy[i];
            int nC = c + dx[i];
            if(!isValid(nR,nC)) continue;
            if(visit[nR][nC]) continue;
            if(score[nR][nC] != b) continue;
            visit[nR][nC] = true;
            sameNumCount = dfs(nR,nC,b,visit,sameNumCount+1);
            tmpSameNumCount++;
        }
        return sameNumCount;
    }

    private boolean isValid(int nextR, int nextC) {
        if(nextR < 1 || nextC < 1 || nextR > N || nextC > M) return false;
        return true;
    }


}
