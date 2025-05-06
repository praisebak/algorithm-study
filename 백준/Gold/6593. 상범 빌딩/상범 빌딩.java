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

    private StringBuilder stringBuilder;

    class Node{
        int time;
        int demen;
        int row;
        int col;

        public Node(int demen, int row, int col, int time) {
            this.time = time;
            this.demen = demen;
            this.row = row;
            this.col = col;
        }
    }

    public void solve () throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        stringBuilder = new StringBuilder();


        int[] dz = {0,0,0,0,-1,1};
        int[] dy = {-1,1,0,0,0,0};
        int[] dx = {0,0,-1,1,0,0};
        while (true){
            String[] sArr = bufferedReader.readLine().split(" ");

            int N = Integer.parseInt(sArr[0]);
            int M = Integer.parseInt(sArr[1]);
            int K = Integer.parseInt(sArr[2]);
            if(N == 0 && M == 0 && K == 0){
                break;
            }
            char[][][] map = new char[N][M][K];
            boolean[][][] visit = new boolean[N][M][K];

            Node start = null;

            for (int dimenstion = 0; dimenstion < N; dimenstion++) {
                for (int row = 0; row < M; row++) {
                    String s = bufferedReader.readLine();
                    for (int col = 0; col < K; col++) {
                        map[dimenstion][row][col] = s.charAt(col);
                        if(map[dimenstion][row][col] == 'S'){
                            start = new Node(dimenstion, row, col,0);
                        }
                    }
                }
                bufferedReader.readLine();
            }

            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(((o1, o2) -> o1.time - o2.time));
            priorityQueue.add(start);
            visit[start.demen][start.row][start.col] = true;

            boolean isEscape = false;
            while (!priorityQueue.isEmpty()){
                Node node = priorityQueue.poll();
                if(map[node.demen][node.row][node.col] == 'E') {
                    stringBuilder.append("Escaped in " + node.time + " minute(s).");
                    isEscape = true;
                    break;
                }
                for (int i = 0; i < 6; i++) {
                    int nZ = node.demen + dz[i];
                    int nX = node.col + dx[i];
                    int nY = node.row + dy[i];
                    if(nZ < 0 || nZ >= N || nY < 0 || nY >= M || nX < 0 || nX >= K) continue;
                    if(visit[nZ][nY][nX]) continue;
                    if(map[nZ][nY][nX] == '#') continue;
                    priorityQueue.add(new Node(nZ,nY,nX,node.time+1));
                    visit[nZ][nY][nX] = true;
                }
            }

            if(!isEscape){
                stringBuilder.append("Trapped!");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
