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
    class Node{
        int y;
        int x;
        int time;
        public Node(int y,int x,int time){
            this.y=y;
            this.x=x;
            this.time = time;
        }
    }

    int N;
    int M;
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);

        Node a = null;
        Node b = null;

        char[][] map = new char[N][M];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)-> o1.time-o2.time);

        int[][] visit = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char ch = s.charAt(j);
                map[i][j] = ch;
                if(map[i][j] == '.'){
                    queue.add(new Node(i,j,1));
                    visit[i][j] = 1;
                }
                if(map[i][j] == 'L'){
                    if(a == null){
                        a = new Node(i,j,1);
                    }else{
                        b = new Node(i,j,1);
                    }
                }
            }
        }
        queue.add(a);
        queue.add(b);
        visit[a.y][a.x] = 1;
        visit[b.y][b.x] = 1;
        int curTime = 1;
        List<Node> removeWallList = new ArrayList<>();
        while (!queue.isEmpty()){
            Node cur = queue.poll();

            if(curTime != cur.time){
                removeWall(removeWallList,map);
                removeWallList = new ArrayList<>();
                curTime = cur.time;
            }
            for (int i = 0; i < 4; i++) {
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX] != 0) continue;
                if(map[nY][nX] == '.'){
                    visit[nY][nX] = cur.time;
                    queue.add(new Node(nY,nX,cur.time));
                }else if(map[nY][nX] == 'X'){
                    removeWallList.add(new Node(nY, nX, cur.time+1));
                    visit[nY][nX] = cur.time+1;
                    queue.add(new Node(nY,nX,cur.time+1));
                }
            }
        }


//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(visit[i][j]);
//            }
//            System.out.println();
//        }

        if(moveBird(a,b,map,visit)){
            return;
        }


    }

    private boolean moveBird(Node a, Node b, char[][] map, int[][] weightMap) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        queue.add(new Node(a.y,a.x,1));

        boolean[][] visit = new boolean[N][M];
        int answer =  Integer.MIN_VALUE;
        visit[a.y][a.x] = true;

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            answer = Math.max(answer,cur.time);
            for (int i = 0; i < 4; i++) {
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX]) continue;
                visit[nY][nX] = true;

                if(map[nY][nX] == 'X') continue;
                queue.add(new Node(nY,nX,weightMap[nY][nX]));
                //B만났음
                if(map[nY][nX] == 'L'){
                    System.out.println(answer-1);
                    return true;
                }
            }
        }
        return false;
    }

    private void removeWall(List<Node> removeWallList,char[][] map) {
        for (Node node : removeWallList){
            map[node.y][node.x] = '.';
        }
    }

    private boolean isValid(int nY, int nX) {
        if(nY<0 || nX<0 || nY >=N || nX >=M) return  false;
        return true;
    }
}
