import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    int N;
    int M;

    int[][] visit;
    int[][] map;
    HashMap<Integer,Integer> idxCountMap = new HashMap<>();
    private int answer = 0;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        map = new int[N][M];
        visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visit[i][j] == 0 && map[i][j] == 1){
                    bfs(i,j,++idx);
                }
            }
        }



        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0) continue;
                int sum = 0;

                HashSet<Integer> set = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    int nY = i + dy[k];
                    int nX = j + dx[k];
                    if(!isValid(nY,nX)) continue;
                    int nextIdx = visit[nY][nX];
                    if(set.contains(nextIdx)) continue;
                    set.add(nextIdx);
                    sum += idxCountMap.getOrDefault(nextIdx,0);
                }
                answer = Math.max(sum+1,answer);
            }
        }

        System.out.println(answer);


    }

    class Node{
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    private void bfs(int r, int c,int idx) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(r,c));
        visit[r][c] = idx;
        int count = 0;

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nY = cur.r + dy[i];
                int nX = cur.c + dx[i];

                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX] != 0) continue;
                if (map[nY][nX] == 1) {
                    visit[nY][nX] = idx;
                    count++;
                    queue.add(new Node(nY,nX));
                }
            }
        }
        idxCountMap.put(idx,count+1);
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >=N || nX >=M) return false;
        return true;
    }
}
