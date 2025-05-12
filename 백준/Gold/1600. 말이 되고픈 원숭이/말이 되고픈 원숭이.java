
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int MAX_MOVE;
    private int ROW;
    private int COL;

    class Node{
        int time;
        int y;
        int x;
        int horseMoveTime;

        public Node(int y, int x,int time,int horseMoveTime) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.horseMoveTime = horseMoveTime;
        }
    }

    int[] horseMoveDy = {-1,-2,-1,-2,1,2,1,2};
    int[] horseMoveDx = {-2,-1,2,1,-2,-1,2,1};

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();

        MAX_MOVE = Integer.parseInt(s);

        String[] sArr = bufferedReader.readLine().split(" ");
        COL = Integer.parseInt(sArr[0]);
        ROW = Integer.parseInt(sArr[1]);

        char[][] map = new char[ROW +1][COL +1];
        for (int i = 0; i < ROW; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < COL; j++) {
                char c = sArr[j].charAt(0);
                map[i][j] = c;
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> o1.time - o2.time);

        queue.add(new Node(0,0,0,0 ));

        boolean[][][] visit = new boolean[ROW +1][COL +1][31];

        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node.y == ROW -1 && node.x == COL -1) {
                System.out.println(node.time);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nY = node.y + dy[i];
                int nX = node.x + dx[i];
                if(!isValid(nY,nX)) continue;
                if(map[nY][nX] == '1') continue;
                if(visit[nY][nX][node.horseMoveTime]) continue;
                visit[nY][nX][node.horseMoveTime] = true;
                queue.add(new Node(nY,nX,node.time+1, node.horseMoveTime));
            }

            if(node.horseMoveTime >= MAX_MOVE) continue;

            for (int i = 0; i < 8; i++) {
                int nY = node.y + horseMoveDy[i];
                int nX = node.x + horseMoveDx[i];
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX][node.horseMoveTime+1]) continue;
                if(map[nY][nX] == '1') continue;

                visit[nY][nX][node.horseMoveTime+1] = true;
                queue.add(new Node(nY,nX,node.time+1, node.horseMoveTime+1));
            }
        }
        System.out.println(-1);
    }


    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= ROW || nX >= COL){
            return false;
        }
        return true;
    }
}
