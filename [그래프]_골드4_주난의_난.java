import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
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
    char[][] map;
    int N;
    int M;
    Node obj;
    Node cur;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr =bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        map = new char[N][M];
        sArr = bufferedReader.readLine().split(" ");
        int a =Integer.parseInt(sArr[0]);
        int b= Integer.parseInt(sArr[1]);
        cur = new Node(a-1,b-1,0);
        a =Integer.parseInt(sArr[2]);
        b= Integer.parseInt(sArr[3]);
        obj = new Node(a-1,b-1,0);

        for (int i = 0; i < N; i++) {
            String s =bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        bfs();
        System.out.println(answer);

    }
    int[] dy = {-1,1,0,0};
    int[] dx= {0,0,-1,1};


    public boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0|| nY >= N || nX >=M) return false;
        return true;
    }
    int answer = 0;

    private void bfs() {
        boolean[][] visit = new boolean[N][M];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> o1.time - o2.time);
        visit[cur.y][cur.x] = true;
        queue.add(new Node(cur.y,cur.x,0));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.y == obj.y && cur.x == obj.x){
                answer = cur.time;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nY= dy[i] + cur.y;
                int nX= dx[i] + cur.x;

                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX]) continue;

                if(map[nY][nX] == '0'){
                    queue.add(new Node(nY,nX,cur.time));
                }else{
                    queue.add(new Node(nY,nX,cur.time+1));
                }
                visit[nY][nX] = true;
            }
        }
    }

}
