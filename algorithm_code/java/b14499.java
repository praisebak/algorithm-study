package com.study;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N,M,X,Y,order;
        N = sc.nextInt();
        M = sc.nextInt();
        Y = sc.nextInt();
        X = sc.nextInt();
        order = sc.nextInt();
        int[][] map = new int[N][M];
        ArrayList<Integer> horiList = new ArrayList<>();
        ArrayList<Integer> vertList = new ArrayList<>();
        init(horiList,vertList);
        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++)
            {
                map[i][j] = sc.nextInt();
            }
        }

        int[] orderArr = new int[order];
        for(int i=0;i<order;i++){
            orderArr[i] = sc.nextInt();
        }
        move(horiList,vertList,map,orderArr,X,Y);

    }


    private static void move(ArrayList<Integer> horiList, ArrayList<Integer> vertList, int[][] map, int[] orderArr,int curX,int curY)
    {
        for (int i = 0; i < orderArr.length ; i++)
        {
            int order = orderArr[i] -1;
            int newX = curX + dx[order];
            int newY = curY + dy[order];

            if(newX == -1 || newX == map[0].length) continue;
            if(newY == -1 || newY == map.length) continue;
            curX = newX;
            curY = newY;
            //동서북남
            //중앙값 관리해줘야함에 주의
            //지도 조건 추가 및 지도 갱신도 해야해요잉
            switch (orderArr[i]){
                case 1:
                    int left = horiList.remove(0);
                    //add는 끝에 붙임
                    horiList.add(vertList.remove(3)) ;
                    vertList.add(left);
                    vertList.set(1, horiList.get(1));
                    break;
                case 2:
                    int right = horiList.remove(horiList.size()-1);
                    horiList.add(0,vertList.remove(3));
                    vertList.add(right);
                    vertList.set(1, horiList.get(1));
                    break;
                case 3:
                    vertList.add(0,vertList.remove(vertList.size()-1));
                    horiList.set(1, vertList.get(1));
                    break;
                case 4:
                    vertList.add(vertList.remove(0));
                    horiList.set(1, vertList.get(1));

                    break;
                default:
                    break;
            }

            if(map[curY][curX] == 0){
                map[curY][curX] = horiList.get(1);
            }else{
                int curVal = map[curY][curX];
                horiList.set(1,curVal);
                vertList.set(1,curVal);
                map[curY][curX] = 0;
            }

            System.out.println(vertList.get(3));
        }
    }

    private static void init(ArrayList<Integer> horiList,ArrayList<Integer> vertList)
    {
        for(int i=0;i<3;i++){
            horiList.add(0);
        }
        for(int i=0;i<4;i++){
            vertList.add(0);
        }

    }
}
