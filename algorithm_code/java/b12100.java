package com.pb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*

4
0 0 2 0
0 0 0 0
2 0 0 0
0 0 0 0
 */

//0222
//easy 2048 b12100
class Easy2048
{
    //상하좌우
    //하,우면 N-1에서 시작해야하고 (1,3)
    //그 외에는 0부터 시작가능
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};
    int MAX = 0;
    public void solve(String s, int n, int[][] board)
    {
        for(int i=0;i<s.length();i++){
            int dir = s.charAt(i) - 48;
            move(dir,n,board);
        }
    }

    private void move(int dir, int n, int[][] board)
    {
        boolean[][] visit = new boolean[n][n];
        switch (dir)
        {
            case 0:
                for(int i=0;i<n;i++){
                    for(int j=1;j<n;j++){
                        int y = j;
                        while (y != 0){
                            //visit된 block은 못합침
                            if(board[y-1][i] == board[y][i] && !visit[y-1][i] && board[y][i] != 0){
                                board[y-1][i] *= 2;
                                visit[y-1][i] = true;
                                board[y][i] = 0;
                                MAX = Math.max(board[y-1][i],MAX);
                                break;
                            }
                            if(board[y-1][i] == 0){
                                board[y-1][i] = board[y][i];
                                board[y][i] = 0;
                            }else if(board[y-1][i] != 0){
                                break;
                            }
                            y+=dy[dir];
                        }
                    }
                }
                break;
            case 1:

                for(int i=0;i<n;i++){
                    for(int j=n-2;j>=0;j--){
                        int y = j;
                        while (y+1 != n){
                            if(board[y+1][i] == board[y][i] && !visit[y+1][i] && board[y][i] != 0){
                                board[y+1][i] *= 2;
                                visit[y+1][i] = true;
                                board[y][i] = 0;
                                MAX = Math.max(board[y+1][i],MAX);
                                break;
                            }
                            if(board[y+1][i] == 0){
                                board[y+1][i] = board[y][i];
                                board[y][i] = 0;
                            }else if(board[y+1][i] != 0){
                                break;
                            }
                            y+=dy[dir];
                        }
                    }
                }
                break;
            case 2:
                for(int i=0;i<n;i++){
                    for(int j=1;j<n;j++){
                        int x = j;
                        while (x-1 != -1){
                            if(board[i][x-1] == board[i][x] && !visit[i][x-1] && board[i][x] != 0){
                                board[i][x-1] *= 2;
                                visit[i][x-1] = true;
                                board[i][x] = 0;
                                MAX = Math.max(board[i][x-1],MAX);
                                break;
                            }
                            if(board[i][x-1] == 0){
                                board[i][x-1] = board[i][x];
                                board[i][x] = 0;
                            }else if(board[i][x-1] != 0){
                                break;
                            }
                            x+=dx[dir];
                        }
                    }
                }
                break;
            default:
                for(int i=0;i<n;i++){
                    for(int j=n-2;j>=0;j--){
                        int x = j;
                        while (x+1 != n){
                            if(board[i][x+1] == board[i][x] && !visit[i][x+1] && board[i][x] != 0){
                                board[i][x+1] *= 2;
                                board[i][x] = 0;
                                visit[i][x+1] = true;
                                MAX = Math.max(board[i][x+1],MAX);
                                break;
                            }
                            if(board[i][x+1] == 0){
                                board[i][x+1] = board[i][x];
                                board[i][x] = 0;
                            }else if(board[i][x+1] != 0){
                                break;
                            }
                            x+=dx[dir];
                        }
                    }
                }
                break;
        }



    }

}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] board = new int[N][N];
        int max = 0;

        for(int i=0;i<N;i++){
            for (int j = 0; j < N ; j++)
            {
                board[i][j] = sc.nextInt();
                max = Math.max(board[i][j],max);
            }
        }
        List<String> s = new ArrayList<>();
        getAllDir(s,"",N,new boolean[4]);
        Easy2048 easy2048 = new Easy2048();
        easy2048.MAX = max;
        for(String v : s){
            easy2048.solve(v,N,clone(board));
        }
        System.out.println(easy2048.MAX);

    }

    static int[][] clone(int[][] board){
        int[][] tmpBoard = new int[board.length][board.length];
        for (int i = 0; i <board.length ; i++)
        {
            for (int j = 0; j <board.length ; j++)
            {
                tmpBoard[i][j] = board[i][j];
            }
        }
        return tmpBoard;
    }

    private static void getAllDir(List<String> sList, String s, int N,boolean[] visit)
    {
        if(s.length() == 5){
            sList.add(s);
            return;
        }


        for(int i=0;i<4;i++){
            getAllDir(sList,s+i,N,visit);
        }
    }

}

