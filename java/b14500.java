package com.pb;

import java.util.Scanner;
//14500


public class Main {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int MAX = 0;
    static boolean[][] visit;
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int N,M;
        N = sc.nextInt();
        M = sc.nextInt();

        int[][] map = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = sc.nextInt();
            }
        }
        visit = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                int sum = map[i][j];
                visit[i][j] = true;
                dfs(map,j,i,1,sum);
                visit[i][j] = false;
            }
        }

        System.out.println(MAX);
        
    }

    private static void dfs(int[][] map, int x, int y, int depth, int sum)
    {
        if(depth == 4){
            MAX = Math.max(MAX,sum);
            return;
        }
        
        for(int i=0;i<4;i++){
            int newY = y + dy[i];
            int newX = x + dx[i];
            if(newY <= -1 || newY >= map.length || newX <= -1 || newX >= map[0].length) continue;
            if(visit[newY][newX]) continue;
            if(depth == 2){
                visit[newY][newX] = true;
                dfs(map,x,y, depth+1,sum + map[newY][newX]);
                visit[newY][newX] = false;

            }
            visit[newY][newX] = true;
            dfs(map,newX,newY, depth+1,sum + map[newY][newX]);
            visit[newY][newX] = false;
        }

    }


}

