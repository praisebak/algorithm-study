//https://www.acmicpc.net/problem/1987

import javax.management.QueryEval;
import javax.swing.text.html.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{


    private int N;
    private int M;

    char[][] map;
    private int answer = Integer.MAX_VALUE;

    class Node{
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        map = new char[N][M];
        Node red = null;
        Node blue = null;

        for(int i=0;i<N;i++){
            String s = bufferedReader.readLine();
            for(int j=0;j<M;j++){
                char cur = s.charAt(j);
                if(cur == 'B') blue = new Node(i,j);
                if(cur == 'R') red = new Node(i,j);
                map[i][j] = cur;
            }
        }

        dfs(blue,red,0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    public boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
    }

    int[] dy=  {-1,1,0,0};
    int[] dx=  {0,0,-1,1};

    private void dfs(Node blue, Node red, int depth) {
        if(answer <= depth){
            return;
        }
        if(depth == 10) return;

        for(int i=0;i<4;i++){
            int rY = red.y;
            int rX = red.x;

            int bY = blue.y;
            int bX = blue.x;

            boolean redGoal = false;
            boolean blueGoal = false;

            boolean secondMove = false;

            char cur = map[rY][rX];

            //red 이동시키기
            while (cur != '#' && isValid(rY,rX)){
                if(cur == 'O') {
                    redGoal = true;
                    cur = map[rY][rX];
                    break;
                }
                if(rY == bY && rX == bX){
                    rY -= dy[i];
                    rX -= dx[i];
                    secondMove = true;
                    cur = map[rY][rX];
                    break;
                }
                rY += dy[i];
                rX += dx[i];
                cur = map[rY][rX];
            }

            if(cur == '#'){
                rY -= dy[i];
                rX -= dx[i];
            }

            cur = map[bY][bX];
            while (cur != '#' && isValid(bY,bX)){
                if(cur == 'O'){
                    blueGoal = true;
                    cur = map[bY][bX];
                    break;
                }
                if(rY == bY && rX == bX){
                    bY -= dy[i];
                    bX -= dx[i];
                    cur = map[bY][bX];
                    break;
                }
                bY += dy[i];
                bX += dx[i];
                cur = map[bY][bX];
            }

            if(cur == '#'){
                bY -= dy[i];
                bX -= dx[i];
            }

            cur = map[rY][rX];

            if(secondMove){
                while (cur != '#' && isValid(rY,rX)){
                    if(cur == 'O') {
                        redGoal = true;
                        cur = map[rY][rX];
                        break;
                    }
                    if(rY == bY && rX == bX){
                        rY -= dy[i];
                        rX -= dx[i];
                        cur = map[rY][rX];
                        secondMove = true;
                        break;
                    }

                    rY += dy[i];
                    rX += dx[i];
                    cur = map[rY][rX];
                }

                if(cur == '#'){
                    rY -= dy[i];
                    rX -= dx[i];
                }
            }


            if(blueGoal) continue;
            if(redGoal){
                answer = Math.min(answer,depth+1);
                return;
            }else{
                dfs(new Node(bY,bX),new Node(rY,rX),depth+1);
            }
        }

    }
}
