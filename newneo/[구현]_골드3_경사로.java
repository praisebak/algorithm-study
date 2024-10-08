import jdk.jshell.execution.JdiExecutionControlProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException{
        new Solve().solve();

    }
}

class Solve{
    int N;
    int L;
    int[][] map;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        L = Integer.parseInt(sArr[1]);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            //0,j로 아래로 확인
            downCheck(i);
        }


        for (int i = 0; i < N; i++) {
            //i,0으로 가로로 확인
            rightCheck(i);
        }


        System.out.println(answer);




    }

    private void downCheck(int cal) {
        boolean[] visit = new boolean[N];
        int prev = map[0][cal];
//        System.out.println(cal + " 열 시작");
        for (int i = 1; i < N; i++) {
            int cur = map[i][cal];
            int diff = Math.abs(prev - cur);
            if(diff <= 1){
                if(diff == 1){
                    //올라가야함
                    if(cur > prev){
                        int tmpI = i-1;
                        for (int j = 0; j < L; j++) {
                            if(!isValid(tmpI)) return;
                            if(map[tmpI][cal] != prev) return;
                            if(visit[tmpI]) return;
                            tmpI--;
                        }
                    }else{
                        int tmpI = i;
                        for (int j = 0; j < L; j++) {
                            if(!isValid(tmpI)) return;
                            if(map[tmpI][cal] != cur) return;
                            visit[tmpI] = true;
                            tmpI++;
                        }
                        i += L -1;
                    }
                }
            }else{
                return;
            }
            prev = cur;
        }
//        System.out.println(cal + " 열 통과");
        answer++;
    }

    private boolean isValid(int idx) {
        if(idx < 0 || idx >= N) return false;
        return true;

    }

    private void rightCheck(int row) {
        int prev = map[row][0];
        boolean[] visit = new boolean[N];
        for (int i = 1; i < N; i++) {
            int cur = map[row][i];
            int diff = Math.abs(prev - cur);
            if(diff <= 1){
                if(diff == 1){
                    //올라가야함
                    if(cur > prev){
                        int tmpI = i-1;
                        for (int j = 0; j < L; j++) {
                            if(!isValid(tmpI)) return;
                            if(map[row][tmpI] != prev) return;
                            if(visit[tmpI]) return;
                            tmpI--;
                        }
                    }else{
                        int tmpI = i;
                        for (int j = 0; j < L; j++) {
                            if(!isValid(tmpI)) return;
                            if(map[row][tmpI] != cur) return;
                            visit[tmpI] = true;
                            tmpI++;
                        }
                        i += L -1;
                    }
                }
            }else{
                return;
            }
            prev = cur;

        }
        answer++;
//        System.out.println(row + " 행 통과");

    }

    int answer = 0;
}
