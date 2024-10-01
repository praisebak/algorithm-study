import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        int dist;

        public Node(int y, int x,int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
    List<Node> chikenList = new ArrayList<>();
    List<Node> homeList = new ArrayList<>();
    boolean[][] visit;

    char[][] map;
    int N;
    int M;
    int[][][] weight;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr= bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = sArr[j].charAt(0);
                if(map[i][j] == '1'){
                    homeList.add(new Node(i,j,0));
                } else if(map[i][j] == '2') {
                    chikenList.add(new Node(i,j,0));
                }
            }
        }
        weight = new int[chikenList.size()][N][N];

        for (int i = 0; i < chikenList.size(); i++) {
            visit = new boolean[N][N];
            Node node = chikenList.get(i);
            //System.out.println(node.y + "," + node.x);
            bfs(node.y,node.x,i);
        }

        for (int i = 0; i < chikenList.size(); i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    //System.out.print(weight[i][j][k] + " ");
                }
                //System.out.println();
            }
            //System.out.println();
            
        }

        depthArr = new int[M];
        //조합으로 이제 계산때리면됨
        comb(-1,0);

        System.out.println(answer);
        for(Node home : homeList){
            int curMin = Integer.MAX_VALUE;
            for (int i = 0; i < M; i++) {
                int chickenIdx = depthArr[i];
                curMin = Math.min(weight[chickenIdx][home.y][home.x],curMin);
            }
        }

    }

    int[] depthArr;
    private void comb(int prev, int depth) {
        if(depth == M){
            cal();
            return;
        }

        for (int i = prev+1; i < chikenList.size(); i++) {
            depthArr[depth] = i;
            comb(i,depth+1);
        }
    }
    int answer = Integer.MAX_VALUE;

    private void cal() {
        int sum = 0;
        //System.out.println("현재 M Arr= ");
        for (int i = 0; i < M; i++) {
            int chickenIdx = depthArr[i];
            //System.out.print(chickenIdx + " ");
        }

        for(Node home : homeList){
            int curMin = Integer.MAX_VALUE;
            for (int i = 0; i < M; i++) {
                int chickenIdx = depthArr[i];
                curMin = Math.min(weight[chickenIdx][home.y][home.x],curMin);
            }
            sum += curMin;
        }
        //System.out.println(sum);
        //System.out.println();
        answer = Math.min(answer,sum);
    }

    int[] dy= {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    public boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nY >= N || nX >=N) return false;
        return true;
    }
    private void bfs(int y, int x,int idx) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(y,x,0));
        visit[y][x] = true;
        weight[idx][y][x] = 0;


        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nY =dy[i] + cur.y;
                int nX =dx[i] + cur.x;
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX]) continue;
                visit[nY][nX] = true;
                weight[idx][nY][nX] = cur.dist+1;
                queue.add(new Node(nY,nX,cur.dist+1));
            }
        }
    }

}
