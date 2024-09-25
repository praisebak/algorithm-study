import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main{
    public static void main(String[] args)throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    class Node{
        int y;
        int x;
        int second;
        public Node(int y,int x,int second){
            this.y =y;
            this.x= x;
            this.second = second;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        char[][] map = new char[N][M];
        int[][] result = new int[N][M];
        Queue<Node> cloudQueue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                result[i][j] = -1;
                if(map[i][j] == 'c'){
                    result[i][j] = 0;
                    cloudQueue.add(new Node(i,j,0));
                }
            }
        }

        while (!cloudQueue.isEmpty()){
            Node cur = cloudQueue.poll();
            if(cur.x+1 != M){
                if(result[cur.y][cur.x+1] == -1){
                    result[cur.y][cur.x+1] = cur.second+1;
                    cloudQueue.add(new Node(cur.y,cur.x+1,cur.second+1));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

    }


}
