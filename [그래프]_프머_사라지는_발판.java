class Solution {
    class Result{
        boolean isWin;
        int aMoveCount;
        int bMoveCount;

        public Result(boolean isWin,int aMoveCount,int bMoveCount){
            this.isWin = isWin;
            this.aMoveCount = aMoveCount;
            this.bMoveCount = bMoveCount;
        }
    }
    int[][] board;

    int N;
    int M;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        this.board = board;
        N = board.length;
        M = board[0].length;


        Result aWin = dfs(board,aloc,bloc,true,true,0,0);
//        Result bWin = dfs(board,aloc,bloc,false,true,0,0);

        if(aWin != null){
            System.out.println("aWin = " + aWin.aMoveCount + "," + aWin.bMoveCount);
        }

//        if(bWin != null){
//            System.out.println("bWin = " + bWin.aMoveCount + "," + bWin.bMoveCount);
//        }

//        if(aWin != null) {
//            System.out.println(aWin.aMoveCount + "," + aWin.bMoveCount);
//
//            return aWin.aMoveCount + aWin.bMoveCount;
////            }else if(bWin  != null){
////                return bWin.aMoveCount + bWin.bMoveCount;
////            }
//        }
        return -1;

    }

    public boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nY >= N || nX >=M){
            return false;
        }
        return true;
    }
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1,};

    private Result dfs(int[][] board, int[] aloc, int[] bloc, boolean aWin,boolean aTurn,int aCount, int bCount) {
        int[] cur = aTurn ? aloc : bloc;

        //이제 못움직임
        if(board[cur[0]][cur[1]] == 0){
            //현재 턴 진사람
            Result result = new Result(false,aCount,bCount);
            return result;
        }

        boolean moveFlag = false;

        int win = Integer.MAX_VALUE;
        int lose = Integer.MIN_VALUE;

        for(int i=0;i<4;i++){
            int nY = cur[0] + dy[i];
            int nX = cur[1] + dx[i];
            if(!isValid(nY,nX)) continue;
            if(board[nY][nX] == 1){
                board[cur[0]][cur[1]] = 0;

                if(aTurn) {
                    Result tmpResult = dfs(board,new int[]{nY,nX},bloc,aWin,!aTurn,aCount+1,bCount);
                    if(!tmpResult.isWin){
                        win = Math.min(win,tmpResult.aMoveCount + tmpResult.bMoveCount);
                    }else{
                        lose = Math.max(lose,tmpResult.aMoveCount + tmpResult.bMoveCount);
                    }

                }else{
                    Result tmpResult = dfs(board,aloc,new int[]{nY,nX},aWin,!aTurn,aCount,bCount+1);
                    if(!tmpResult.isWin){
                        win = Math.min(win,tmpResult.aMoveCount + tmpResult.bMoveCount);
                    }else{
                        lose = Math.max(lose,tmpResult.aMoveCount + tmpResult.bMoveCount);
                    }
                }
                moveFlag = true;
                board[cur[0]][cur[1]] = 1;

            }
        }
        //못움직이는 경우에 리턴
        if(!moveFlag){
            Result result = new Result(!aTurn,aCount,bCount);
            return result;
        }

        if(win != Integer.MAX_VALUE){
            return new Result(true,win,0);
        }else{
            return new Result(false,lose,0);
        }
    }
}
