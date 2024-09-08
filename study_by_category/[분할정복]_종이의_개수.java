//https://www.acmicpc.net/problem/1780

import java.io.*;
import java.util.*;

class Solve{
    private int N;
    private int[][] map;
    public void solve()throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        N = Integer.parseInt(s);

        map = new int[N][N];
        for(int i=0;i<N;i++){
            s = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(s," ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        divideAndMerge(0,0,N);
        for(int i=0;i<3;i++){
            System.out.println(resultArr[i]);
        }
    }

    int[] resultArr = new int[3];

    private void divideAndMerge(int startR, int startC,int size) {
        if(check(startR,startC,size)){
            int val = map[startR][startC];
            resultArr[val+1]++;
        }else{
            int newSize = size / 3;
            for(int i=startR;i<startR + size;i+= newSize){
                for (int j = startC; j <startC + size; j+=newSize) {
                    divideAndMerge(i,j,newSize);
                }
            }
        }
    }

    private boolean check(int startR, int startC, int size) {
        int cur = map[startR][startC];
        for(int i=startR;i<startR + size;i++){
            for (int j = startC; j < startC + size ; j++) {
                int next = map[i][j];
                if(next != cur) return false;
            }
        }
        return true;
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
