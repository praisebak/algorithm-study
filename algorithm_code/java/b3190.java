package com.pb;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point{
    int r;
    int c;
    int dir;
    Point(int r,int c,int dir){
        this.r = r;
        this.c = c;
        this.dir = dir;
    }
}


class Snake{
    int[] mRX = {1,0,-1,0};
    int[] mRY = {0,1,0,-1};

    public void solve()
    {
        Scanner scanner = new Scanner(System.in);
        int matrixN = scanner.nextInt();
        int appleN = scanner.nextInt();
        int[][] arr = new int[matrixN][matrixN];
        int appleRow;
        int appleCal;
        for(int i=0;i<appleN;i++){
            appleRow = scanner.nextInt();
            appleCal = scanner.nextInt();
            arr[appleRow-1][appleCal-1] = 1;
        }
        int moveN = scanner.nextInt();
        int[] moveSecArr = new int[moveN];
        char[] moveChArr = new char[moveN];
        for (int i=0;i<moveN;i++){
            moveSecArr[i] = scanner.nextInt();
            moveChArr[i] = scanner.next().charAt(0);
        }

        play(arr,moveSecArr,moveChArr);

    }

    private void play(int[][] arr, int[] moveSecArr, char[] moveChArr)
    {
        Point h = new Point(0,0,0);
        Point t = new Point(0,0,0);
        Queue<Point> tQ = new LinkedList<>();

        int sec = 0;
        int secI = 0;
        boolean[][] visit = new boolean[arr.length][arr.length];

        while (true){
            sec++;
            move(h);
            //일단 머리 움직이고 범위안인지 체크 - 벽 체크
            if(!isValid(h.r,h.c,arr)) break;
            //자신 몸에 충돌했는지 체크
            if(visit[h.r][h.c]) break;
            visit[h.r][h.c] = true;
            tQ.add(new Point(h.r,h.c,h.dir));

            //사과라면
            if(arr[h.r][h.c] == 1){
                //먹은다음
                arr[h.r][h.c] = 0;
                //꼬리 안움직임
                visit[t.r][t.c] = true;
            }
            //사과가 아니라면
            else{
                //자리 비우기
                visit[t.r][t.c] = false;
                //꼬리 움직이기
                t = tQ.poll();
            }
            //            casePrint(dir,sec);
            //방향 바꾸기

            if(secI < moveSecArr.length && sec == moveSecArr[secI]){
                if(moveChArr[secI++] == 'D') h.dir++;
                else h.dir--;
                h.dir = h.dir == -1 ? 3 : h.dir;
                h.dir = h.dir == 4 ? 0 : h.dir;
            }

        }
        System.out.println(sec);

    }

    private void move(Point p)
    {
        p.r += mRY[p.dir];
        p.c += mRX[p.dir];
    }

    private void casePrint(int dir,int sec)
    {
        System.out.print(sec + " 초 방향 : ");
        switch (dir){
            case 0:
                System.out.println("우");
                break;
            case 1:
                System.out.println("상");
                break;
            case 2:
                System.out.println("좌");
                break;
            case 3:
                System.out.println("하");
                break;
            default:
                break;
        }
    }

    private boolean isValid(int r, int c, int[][] arr)
    {
        if(r < 0 || c < 0) return false;
        return r < arr.length && c < arr.length;
    }
}

class Main {

    public static void main(String[] args) {
        Snake snake = new Snake();
        snake.solve();

    }
}
