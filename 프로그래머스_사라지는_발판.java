import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    class Result{
        public Result(boolean win, int winnerMove, int loserMove) {
            this.win= win;
            this.winnerMove = winnerMove;
            this.loserMove = loserMove;
        }

        boolean win;
        int winnerMove;
        int loserMove;
    }

    class Node{
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;

        Node A = new Node(aloc[0],aloc[1]);
        Node B = new Node(bloc[0],bloc[1]);
        N = board.length;
        M = board[0].length;

        Result result = dfs(A,B,0,0,true,board);

        return result.winnerMove + result.loserMove;
    }

    int N;
    int M;

    public boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};

     int minWin = Integer.MAX_VALUE;
     int maxLose = 0;

    private Result dfs(Node a, Node b, int aMove, int bMove, boolean aTurn, int[][] map) {
        boolean move = false;
        int curY = aTurn ? a.y : b.y;
        int curX = aTurn ? a.x : b.x;


        if((map[a.y][a.x] == 0 && aTurn) || (map[b.y][b.x] == 0 && !aTurn))
            return new Result(false,aMove,bMove);

        Result result;
        int win = Integer.MAX_VALUE;
        int lose = 0;
        map[curY][curX] = 0;

        for (int i = 0; i < 4; i++) {
            int nY = curY + dy[i];
            int nX = curX + dx[i];
            if(!isValid(nY,nX)) continue;
            if(map[nY][nX] == 0) continue;
            move = true;
            if(aTurn) result = dfs(new Node(nY,nX),b,aMove+1,bMove,!aTurn,map);
            else result = dfs(a,new Node(nY,nX),aMove,bMove+1,!aTurn,map);

            if(result.win) lose = Math.max(result.loserMove +result.winnerMove,lose);
            else win = Math.min(win, result.loserMove + result.winnerMove);
        }
        map[curY][curX] = 1;

        //졌음
        if(!move){
            return new Result(false,aMove,bMove);
        }else if(win != Integer.MAX_VALUE){
            return new Result(true,win,0);
        }else{
            return new Result(false,lose,0);
        }

    }


}
