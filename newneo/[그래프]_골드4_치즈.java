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
    int[][] map;
    int N;
    int M;
    boolean[][] visit;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N =  Integer.parseInt(sArr[0]);
        M =  Integer.parseInt(sArr[1]);
        map = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            sArr =bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        initVisit = new boolean[N][M];
        initBFS(0,0);
    }
    PriorityQueue<Node> que = new PriorityQueue<>((o1,o2) -> o1.time - o2.time);

    boolean[][] initVisit;
    private void initBFS(int r,int c) {

        int time = 0;
        int prev = 0;
        while (true){
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(r,c,0));

            List<Node> cheeseList = new ArrayList<>();

            initVisit = new boolean[N][M];
            while (!queue.isEmpty()){
                Node cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nY = cur.y + dy[i];
                    int nX = cur.x + dx[i];
                    if(!isValid(nY,nX)) continue;
                    if(initVisit[nY][nX]) continue;
                    initVisit[nY][nX] = true;
                    if(map[nY][nX] == 0){
                        queue.add(new Node(nY,nX,0));
                    }
                    //치즈면 치즈큐에
                    if(map[nY][nX] == 1){
                        cheeseList.add(new Node(nY,nX,0));
                    }
                }
            }
            if(cheeseList.size() == 0) {
                System.out.println(time);
                System.out.println(prev);
                return;
            }else{
                time++;
                prev = cheeseList.size();
                for(Node node : cheeseList){
                    map[node.y][node.x] = 0;
                }
            }

        }

    }

    class Node{
        int y;
        int x;
        int time;

        public Node(int y, int x, int time){
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};


    private boolean isValid(int nY, int nX) {
        if(nY< 0 || nX< 0 || nY >= N || nX >= M) return  false;
        return true;
    }

    private void addCheeseInQueue(PriorityQueue<Node> queue){
        //위에서 아래로
        for (int col = 0; col < M; col++) {
            for (int row = 0; row < N; row++) {
                if (map[row][col] == 1) {
                    queue.add(new Node(row, col, 0));
                    map[row][col] = 0;
                    break;
                }
            }
            for (int row = N-1; row >= 0; row--) {
                if(map[row][col] == 1){

                    queue.add(new Node(row,col,0));
                    map[row][col] = 0;
                    break;
                }
            }
        }

        for(int row = 0; row < N; row++) {
            for (int col = 0; col <M; col++) {
                if (map[row][col] == 1) {
                    queue.add(new Node(row, col, 0));
                    map[row][col] = 0;
                    break;
                }
            }

            for (int col = M-1; col >= 0; col--) {
                if (map[row][col] == 1) {
                    queue.add(new Node(row, col, 0));
                    map[row][col] = 0;
                    break;
                }
            }
        }
    }

}
