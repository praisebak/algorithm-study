import javax.management.NotificationEmitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{

    private int N;
    private int M;
    private int[][] arr;

    class Node{
        int y;
        int x;
        int time;

        public Node(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> Math.toIntExact(o1.time - o2.time));
    public void solve() throws IOException{
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < sArr.length; j++) {
                arr[i][j] = Integer.parseInt(sArr[j]);
                if(arr[i][j] != 0){
                    priorityQueue.add(new Node(i,j,0));
                }
            }
        }

        //전부 0인경우
        if (priorityQueue.isEmpty()) {
            System.out.println(0);
            return;
        }
        if(N * M == priorityQueue.size()){
            System.out.println(0);
            return;
        }
        bfs();
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    private void bfs() {
        int prevTime = 0;
        List<Node> nextMeltIceList = new ArrayList<>();
        while(!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            if(cur.time != prevTime){
                melt(nextMeltIceList);
                nextMeltIceList = new ArrayList<>();
                if(checkIsDevide()){
                    System.out.println(cur.time);
                    return;
                }
            }
            prevTime = cur.time;
            if(arr[cur.y][cur.x] == 0) continue;
            for (int i = 0; i < 4; i++) {
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(!isValid(nY,nX)) continue;
                if(arr[nY][nX] == 0){
                    if(arr[cur.y][cur.x] == 0){
                        break;
                    }
                    nextMeltIceList.add(new Node(cur.y,cur.x,0));
                }
            }
            priorityQueue.add(new Node(cur.y,cur.x,cur.time+1));
        }
        System.out.println(0);
    }

    private void melt(List<Node> nextMeltIceList) {
        for(Node node : nextMeltIceList){
            if(arr[node.y][node.x] == 0) continue;
            arr[node.y][node.x]--;
        }
    }

    private boolean checkIsDevide() {
        int curSize = getSize();
        boolean[][] visit = new boolean[N][M];
        int size = 0;

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            boolean flag = false;
            for (int j = 0; j < M; j++) {
                if(arr[i][j] != 0){
                    queue.add(new Node(i,j,0));
                    visit[i][j] = true;
                    size++;
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }


        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];
                if(!isValid(nY,nX)) continue;
                if(visit[nY][nX]) continue;
                if(arr[nY][nX] == 0) continue;
                visit[nY][nX] = true;
                queue.add(new Node(nY,nX,0));
                size++;
            }
        }
        if(size == curSize){
            return false;
        }
        return true;
    }

    private int getSize() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j] != 0 ){
                    sum++;
                }
            }
        }
        return sum;
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= M){
            return false;
        }
        return true;
    }
}
