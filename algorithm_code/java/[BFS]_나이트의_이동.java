import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{

    private boolean[][] visit;
    private int L;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int T = N;
        for (int t = 0; t < T; t++) {
            L = Integer.parseInt(bufferedReader.readLine());
            visit = new boolean[L +1][L +1];
            String[] sArr = bufferedReader.readLine().split(" ");
            int curY = Integer.parseInt(sArr[0]);
            int curX  = Integer.parseInt(sArr[1]);

            sArr = bufferedReader.readLine().split(" ");
            int objY = Integer.parseInt(sArr[0]);
            int objX  = Integer.parseInt(sArr[1]);
            visit[curY][curX] = true;
            bfs(objY,objX,curY,curX);
        }
    }
    int[] dy = {-1,-2,-2,-1,1,2,2,1};
    int[] dx = {-2,-1,1,2,-2,-1,1,2};

    class Node{
        public int time;

        int y;
        int x;

        public Node(int y, int x,int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }
    private void bfs(int objY, int objX,int curY,int curX) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(curY,curX,0 ));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.y == objY && cur.x == objX){
                System.out.println(cur.time);
                return;
            }
            for (int i = 0; i < 8; i++) {
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(!isValid(nY, nX)) continue;
                if(visit[nY][nX]) continue;
                visit[nY][nX] = true;
                queue.add(new Node(nY,nX,cur.time+1));
            }
        }
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= L || nX >= L) return false;
        return true;
    }
}
