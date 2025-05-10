import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solve{

    private static final int N = 12;
    private static final int M = 6;
    private char[][] map;
    private int[] dy = {-1,1,0,0};
    private int[] dx = {0,0,-1,1};
    private boolean[][] visit;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int explodeCount = -1;
        while (explodeCount != 0){
            explodeCount = 0;
            visit = new boolean[12][7];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if(visit[i][j]) continue;
                    if(map[i][j] == '.') continue;
                    explodeCount += findTogether(i,j) ? 1 : 0;

                }
            }

            if(explodeCount != 0){
                answer++;
            }
            for (int col = 0; col < 6; col++) {
                for (int repeat=0; repeat<12;repeat++) {
                    for (int row = 0; row < 11; row++) {
                        if(map[row+1][col] != '.') continue;
                        map[row+1][col] = map[row][col];
                        map[row][col] = '.';
                    }
                }
            }
        }
        System.out.println(answer);
    }

    int answer = 0;
    private boolean findTogether(int i, int j) {
        char curColor = map[i][j];
        Queue<Node> queue = new LinkedList<>();
        visit[i][j] = true;
        queue.add(new Node(i,j));
        int count = 1;
        List<Node> sameBlocks = new ArrayList<>();
        sameBlocks.add(new Node(i,j));


        while (!queue.isEmpty()){
            Node poll = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nY = dy[k] + poll.y;
                int nX = dx[k] + poll.x;
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX]) continue;
                if(map[nY][nX] != curColor) continue;
                visit[nY][nX] = true;
                count++;
                queue.add(new Node(nY,nX));
                sameBlocks.add(new Node(nY,nX));
            }
        }

        if(count < 4){
            return false;
        }

        for(Node node : sameBlocks){
            map[node.y][node.x] = '.';
        }

        return true;
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= M){
            return false;
        }
        return true;
    }
}
