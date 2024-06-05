package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class B2206 {


    public void print(int i,int j,int nY,int nX){
        if(i == 0 && j == 1){
            System.out.println(nY + "," + nX);
        }
    }

    private int[][] map;

    public static void main(String[] args)  throws IOException {
        B2206 b2206 = new B2206();
        b2206.solve();

    }

    private void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(strings[0]);
        M = Integer.parseInt(strings[1]);
        map = new int[N][M];
        List<Node> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            String s =  bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
                if(map[i][j] == 1){
                    list.add(new Node(i,j));
                }
            }
        }

        dfs(-1,-1);
        for(Node node : list){
            dfs(node.y,node.x);
        }
        if(minTime == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(minTime+1);
        }
    }


    class Node{
        int y;
        int x;
        int time;
        Node(int x,int y ){
            this.x = x;
            this.y = y;
            this.time = 0;
        }


        Node(int x,int y,int time ){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    int minTime = Integer.MAX_VALUE;
    int N;
    int M;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    private void dfs(int passI, int passJ) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0));
        boolean[][] visit = new boolean[N][M];

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.y == N-1 && cur.x == M-1){
                minTime = Math.min(minTime, cur.time);
                continue;
            }

            for(int i = 0;i<4;i++){
                int nY =cur.y + dy[i];
                int nX =cur.x + dx[i];
                if(!isValid(nY,nX)) continue;
                //통과가능한 벽이거나 그냥 원래 통과가능한 벽임
                if(map[nY][nX] != 1 || (passI == nY) && (passJ == nX)){
                    if(visit[nY][nX]) continue;
                    visit[nY][nX] = true;
                    queue.add(new Node(nX,nY,cur.time+1));
//                    print(passI,passJ,nY,nX);
                }
            }
        }
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nY >= N || nX < 0 || nX >= M) return false;
        return true;
    }


}
