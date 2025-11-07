import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitOption;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}

class Node{
    public Node(int y, int x, int move, int weight) {
        this.y = y;
        this.x = x;
        this.move = move;
        this.weight = weight;
    }

    int y;
    int x;
    int move;
    int weight;

}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        char[][] map = new char[N][M];

        for(int i=0;i<N;i++){
            String s = bufferedReader.readLine();
            for(int j=0;j<M;j++){
                char ch = s.charAt(j);
                map[i][j] = ch;
            }
        }
        boolean[][][] visit = new boolean[N][M][2];

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0,1,0));

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.y == N-1 && cur.x == M-1){
                System.out.println(cur.move);
                return;
            }

            for(int i=0;i<4;i++){
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                //already
                if(map[nY][nX] == '1'){
                    if(cur.weight == 1) continue;
                    if(visit[nY][nX][1]) continue;
                    visit[nY][nX][1] = true;
                    queue.add(new Node(nY,nX,cur.move+1,1));
                }else{
                    if(visit[nY][nX][cur.weight]) continue;
                    visit[nY][nX][cur.weight] = true;
                    queue.add(new Node(nY,nX,cur.move+1,cur.weight));
                }
            }
        }

        System.out.println(-1);
    }

    int[] dy=  {-1,1,0,0};
    int[] dx=  {0,0,-1,1};
}
