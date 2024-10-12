import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    int[][] visit;
    List<Node> sharkList = new ArrayList<>();
    class Node{
        int y;
        int x;
        int depth;
        public Node(int y,int x,int depth){
            this.y=y;
            this.x=x;
            this.depth = depth;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        visit = new int[N][M];
        for (int i = 0; i <N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            Arrays.fill(visit[i],Integer.MAX_VALUE);
            for (int j = 0; j < M; j++) {
                if(Integer.parseInt(sArr[j]) == 1){
                    sharkList.add(new Node(i,j,1));
                    visit[i][j] = Integer.MIN_VALUE;
                }
            }
        }

        for(Node node : sharkList){
            bfs(node);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer,visit[i][j]);
            }

        }
        System.out.println(answer);
    }
    int N;
    int M;

    int[] dy = {-1,1,0,0,-1,1,-1,1};
    int[] dx = {0,0,-1,1,-1,1,1,-1};

    public boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
    }
    int answer = 0;
    private void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX] <= cur.depth) continue;
                visit[nY][nX] = cur.depth;
                queue.add(new Node(nY,nX,cur.depth+1));
            }
        }

    }
}
