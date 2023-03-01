
import java.util.*;

class Solution {
    static public void main(String[] args) {
        Solve solve = new Solve();
        solve.start();
    }
}

class Pair {
    int y;
    int x;

    Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solve {
    // 좌 좌상 상 우상 우 우하 하 좌하
    int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
    int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    int[][] map;
    int N;
    int M;
    List<Pair> clouds = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void start() {
        init();
        solve();
    }

    public void init() {
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        clouds.add(new Pair(N, 1));
        clouds.add(new Pair(N, 2));
        clouds.add(new Pair(N - 1, 1));
        clouds.add(new Pair(N - 1, 2));
    }

    public void move(Pair p, int dir,int s) {

        // 격자가 연결된 경우
        if (dir != -1) {
          for(int i=0;i<s;i++){
            p.y += dy[dir];
            p.x += dx[dir];
            if(p.y == 0) p.y = N;
            if(p.x == 0) p.x = N;
            if(p.y == N+1) p.y = 1;
            if(p.x == N+1) p.x = 1;
          }

          map[p.y][p.x]++; 
        } else {
          int sum = 0;
          for(dir=1;dir<8;dir+=2){
            int tmpY = p.y + dy[dir];
            int tmpX = p.x + dx[dir];
            if(isRangeOk(tmpY,tmpX) && map[tmpY][tmpX] != 0){
              sum++;
            }
          }
          map[p.y][p.x]+= sum;
        }


    }

    private boolean isRangeOk(int tmpY, int tmpX) {
      if(tmpY == 0 || tmpX == 0 || tmpY == N+1 || tmpX == N+1) return false;
      return true;
    }

  public void solve() {

        for (int i = 0; i < M; i++) {
            boolean[][] visit = new boolean[N+1][N+1];
            int d = sc.nextInt() - 1;
            int s = sc.nextInt();
//1,2
            for(Pair p : clouds){
              move(p,d,s);
              visit[p.y][p.x] = true;
            }

            //4
            for(Pair p : clouds){
              move(p,-1,s);
            }

            //3
            //구름 삭제
            clouds = new ArrayList<>();


//바구니 물 2 이상이면 구름 생성
            for(int r=1;r<=N;r++){
              for (int c= 1; c<= N; c++) {
                if(map[r][c] >= 2 && !visit[r][c]){
                  clouds.add(new Pair(r,c));
                  map[r][c] -= 2;
                }
              }
            }

        }
        print();
    }

    public void print(){
      int sum = 0;
      for(int i=1;i<=N;i++){
        for(int j=1;j<=N;j++){
          sum += map[i][j];
        }
      }
      System.out.println(sum);
    }
}
