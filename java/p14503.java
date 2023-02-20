import java.util.Scanner;

class p14503{
    Scanner sc = new Scanner(System.in);
    int N;
    int M;
    int curY;
    int curX;
    int d;
    //북 동 남 서
    int[] dx = {0,1,0,-1};
    int[] dy = {-1,0,1,0};
    int depth = 0;

    private int[][] board;

    public static void main(String[] args) {
        p14503 main = new p14503();
        main.init();
        main.solve(main.curY,main.curX,0);
        System.out.println(main.depth);
    }

    private void solve(int r,int c,int depth) {
        boolean flag = false;
        if(board[r][c] != -1){
            board[r][c] = -1;
            depth++;
            this.depth = Math.max(depth,this.depth);
        }
        //왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
        ///왼쪽방향 - 한칸이라고 가정
        for(int i=0;i<4;i++){   
            rotate();
            int nY = r + dy[d];
            int nX = c + dx[d];
            if(isRangeOk(nY, nX)){
                if(board[nY][nX] == 0){
                    solve(nY,nX,depth);
                    flag = true;
                    break;
                }
            }
        }

        if(flag){
            return;
        }

        //후진 - rotate 2번하면됨
        rotate();
        rotate();
        int nY = r;
        int nX = c;
        nY += dy[d];
        nX += dx[d];

        if(isRangeOk(nY,nX)){
            if(board[nY][nX] == 1){
                return;
            }
            else{
                rotate();
                rotate();
                solve(nY,nX,depth);
            }
        }
        

        
    }



    private boolean isRemainOnLeft(int r, int c) {
        int saveD = d;
        rotate();
        while(isRangeOk(r+dy[d],c+dx[d])){
            r += dy[d];
            c += dx[d];
            if(board[r][c] == 0){
                return true;
            }
        }

        d = saveD;
        return false;
    }

    private boolean isRangeOk(int r,int c) {
        if(r == -1 || c == -1 || r == N || c == M){
            return false;
        }
        return true;
    }

    private void rotate() {
        this.d = this.d-1 == -1 ? 3 : this.d-1;
    }

    private void init() {
        N = sc.nextInt();
        M = sc.nextInt();
        this.board = new int[N][M];
        curY = sc.nextInt();
        curX = sc.nextInt();
        d = sc.nextInt();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                board[i][j] = sc.nextInt();
            }
        }
    }

}