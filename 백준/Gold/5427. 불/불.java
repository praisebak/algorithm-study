import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Node{
    int y;
    int x;
    int time;
    public Node(int y,int x,int time){
        this.y = y;
        this.x = x;
        this.time = time;
    }
}

class Solve{

    private int N;
    private int M;
    private char[][] map;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < T; t++) {
            boolean skip = false;
            String[] sArr = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(sArr[1]);
            M = Integer.parseInt(sArr[0]);
            Node start = null;

            map = new char[N][M];
            for (int i = 0; i < N; i++) {
                String s = bufferedReader.readLine();
                for (int j = 0; j < M; j++) {
                    char ch = s.charAt(j);
                    map[i][j] = ch;

                    if(map[i][j] == '@') {
                        if(i == N-1 || j == M-1 || i == 0 || j == 0){
                            skip = true;
                        }
                        map[i][j] = '.';
                        start = new Node(i,j,0);
                    }
                }
            }

            if(skip){
                System.out.println(1);
                continue;
            }
            bfs(start);
            //불을 먼저 옮길건데, 움직이는거랑 동시에 가능하다
            //end 조건에서 고려하지않아도 된다
        }
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    private void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start.y,start.x,0));
        boolean[][] visit = new boolean[N][M];
        visit[start.y][start.x] = true;

        PriorityQueue<Node> fires = new PriorityQueue<>((o1,o2) -> o1.time - o2.time);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '*'){
                    fires.add(new Node(i,j,0));
                }
            }
        }

        int time = 0;
        while (!queue.isEmpty()){
            while (!fires.isEmpty()){

                if(fires.peek().time != time){
                    break;
                }
                Node fire = fires.poll();
                for (int i = 0; i < 4; i++) {
                    int nY = fire.y + dy[i];
                    int nX = fire.x + dx[i];
                    if(nY < 0 || nY >= N || nX < 0 || nX >= M){
                        continue;
                    }
                    if(map[nY][nX] != '*' && map[nY][nX] != '#'){
                        fires.add(new Node(nY,nX,fire.time+1));
                    }
                    map[nY][nX] = '*';
                }
            }

            while (!queue.isEmpty()){
                if(queue.peek().time != time){
                    break;
                }
                Node node = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nY = node.y + dy[i];
                    int nX = node.x + dx[i];

                    if(nY < 0 || nY >= N || nX < 0 || nX >= M){
                        continue;
                    }
                    if(map[nY][nX] == '#' || map[nY][nX] == '*') continue;

                    if(map[nY][nX] == '.' && (nY == N-1 || nY == 0 || nX == 0 || nX == M-1)){
                        System.out.println(node.time+1+1);
                        return;
                    }
                    if(visit[nY][nX]) continue;
                    visit[nY][nX] = true;
                    queue.add(new Node(nY,nX,node.time+1));

                }
            }
            time++;
        }
        System.out.println("IMPOSSIBLE");
    }
}
