//https://www.acmicpc.net/problem/3197

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    static char[][] arr;
    static class Pair{

        public Pair(int y, int x,int weight) {
            this.y = y;
            this.x = x;
            this.weight =weight;

        }

        int weight = 0;
        int y;
        int x;

    }

    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int[][] visit;
    static int N;
    static int M;
    static Pair start = null;
    static Pair end = null;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        visit = new int[N][M];
        arr = new char[N][M];
        PriorityQueue<Pair> queue = new PriorityQueue<>(((o1,o2) -> o1.weight - o2.weight));

        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
                if(arr[i][j] == '.'){
                    queue.add(new Pair(i,j,1));
                    visit[i][j] = 1;
                }
                if (arr[i][j] == 'L' && start == null) {
                    start = new Pair(i, j, 1);
                } else if (arr[i][j] == 'L') {
                    end = new Pair(i, j, 1);
                }
            }
        }
        bfs(queue);
        System.out.println(answerBfs()-1);

    }

    private static int answerBfs() {
        PriorityQueue<Pair> queue = new PriorityQueue<>(((o1,o2) -> o1.weight - o2.weight));
        queue.add(start);

        boolean[][] moveVisit = new boolean[N][M];
        int answer = 0;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            answer = Math.max(cur.weight,answer);
            if(cur.y == end.y && cur.x == end.x){
                return answer;
            }
            for (int i = 0; i < 4; i++) {
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(!isValid(nY,nX)) continue;
                if(moveVisit[nY][nX]) continue;
                queue.add(new Pair(nY,nX,visit[nY][nX]));
                moveVisit[nY][nX] = true;
            }
        }
        return answer;
    }

    private static void bfs(PriorityQueue<Pair> queue) {
        visit[start.y][start.x] = 1;
        visit[end.y][end.x] = 1;
        queue.add(start);
        queue.add(end);
        ArrayList<Pair> removeWallList = new ArrayList<>();
        int curTime = 1;
        while (!queue.isEmpty()){
            Pair cur = queue.poll();
            if(cur.weight != curTime){
                curTime = cur.weight;
                removeWall(removeWallList);
            }

            for (int i = 0; i < 4; i++) {
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX] != 0) continue;
                if(arr[nY][nX] == '.'){
                    queue.add(new Pair(nY,nX,cur.weight));
                    visit[nY][nX] = cur.weight;
                    removeWallList.add(new Pair(nY,nX,0));
                }else if(arr[nY][nX] == 'X'){
                    queue.add(new Pair(nY,nX,cur.weight+1));
                    visit[nY][nX] = cur.weight+1;
                }
            }
        }
    }

    private static boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >=N || nX >= M) return false;
        return true;
    }

    private static void removeWall(ArrayList<Pair> removeWallList) {
        for(Pair pair : removeWallList){
            arr[pair.y][pair.x] = '.';
        }
    }
}
