import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    int N;
    int M;
    char[][] map;
    private boolean flag;

    class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y =y;
            this.x =x;
        }
    }
    List<Node> doorList = new ArrayList<>();
    List<Node> humenList = new ArrayList<>();
    int answer;
    StringBuilder answerStringBuilder = new StringBuilder();
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(sArr[0]);
            M = Integer.parseInt(sArr[1]);
            answer = 0;
            map = new char[N][M];
            doorList = new ArrayList<>();
            humenList = new ArrayList<>();

            for (int j = 0; j <N; j++) {
                String s =bufferedReader.readLine();
                for (int k = 0; k <M ; k++) {
                    map[j][k] = s.charAt(k);
                    if(map[j][k] == '#'){
                        doorList.add(new Node(j,k));
                    }
                    if(map[j][k] == '$'){
                        humenList.add(new Node(j,k));
                    }
                }
            }

            flag = false;
            for (int j = 0; j <= doorList.size(); j++) {
                doorDfs(0,j,0);
                if(flag){
                    answerStringBuilder.append(j + "\n");
                    break;
                }
            }
        }
        System.out.println(answerStringBuilder);


    }

    private void doorDfs(int depth, int doorN,int prev) {
        if(flag) return;
        if(doorN == depth){
            if(humanDfs(humenList.get(0),humenList.get(1))){
                flag =true;
            }
            return;
        }
        for (int i = prev; i < doorList.size(); i++) {
            Node node = doorList.get(i);
            map[node.y][node.x] = '.';
            doorDfs(depth+1,doorN,i+1);
            map[node.y][node.x] = '#';
        }

    }
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};


    //a는 그냥 dfs하게하고
    //b가 a의 visit과 일치하면 a 결과를 따르게한다
    boolean aFlag;
    boolean[][] aVisit;
    boolean[][] visit;
    private boolean humanDfs(Node startA, Node startB) {
        visit = new boolean[N][M];
        visit[startA.y][startA.x] = true;
        aFlag = dfs(startA,true);
        if(!aFlag) return false;
        aVisit = visit;
        visit = new boolean[N][M];
        visit[startB.y][startB.x] = true;

        if(dfs(startB,false)){
            return true;
        }
        return false;

    }

    private boolean dfs(Node node, boolean isA) {
        if(node.y == 0 || node.x == 0 || node.y == N-1 || node.x == M-1){
            return true;
        }
        for(int i=0;i<4;i++){
            int nY = dy[i] + node.y;
            int nX = dx[i] + node.x;
            if(!isValid(nY,nX)) continue;
            //b면서 visitA와 일치하면
            if(!isA && aVisit[nY][nX]) return aFlag;
            if(visit[nY][nX]) continue;
            visit[nY][nX] = true;
            if(map[nY][nX] != '*' && map[nY][nX] != '#'){
                return dfs(new Node(nY,nX),isA);
            }
        }
        return false;

    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >= M) return false;
        return true;
    }
}
