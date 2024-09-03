//https://www.acmicpc.net/problem/16946 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    int N;
    int M;

    int[][] idxArr;
    char[][] map;
    class Node{
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(s);
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new char[N][M];
        idxArr = new int[N][M];

        for(int i=0;i<N;i++){
            s = bufferedReader.readLine();
            for (int j = 0; j <M; j++) {
                map[i][j] = s.charAt(j);
            }
        }


        int curIdx = 1;
        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '0' && idxArr[i][j] == 0){
                    bfs(i,j, curIdx);
                    curIdx++;
                }
            }
        }

        

        int[][] sumArr = new int[N][M];

        for(int i = 0;i<N;i++){
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '1'){
                    Node start = new Node(i,j);
                    int sum = 0;
                    HashSet<Integer> set = new HashSet<>();

                    for(int d=0;d<4;d++){
                        int nY = dy[d] + start.y;
                        int nX = dx[d] + start.x;

                        if(!isValid(nY,nX)) continue;
                        int idx = idxArr[nY][nX];
                        if(idx == 0) continue;
                        set.add(idx);
                    }

                    for(Integer idx : set){
                        sum += idxSumMap.get(idx);
                    }

                    sumArr[start.y][start.x] = sum+1;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                stringBuilder.append(sumArr[i][j] % 10);
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }


    //idx to sum
    HashMap<Integer,Integer> idxSumMap = new HashMap<>();
    int[] dy=  {-1,1,0,0};
    int[] dx=  {0,0,-1,1};


    private void bfs(int r, int c,int curIdx) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(r,c));
        idxArr[r][c] = curIdx;

        int sum = 0;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            sum++;

            for(int i=0;i<4;i++){
                int nY = dy[i] + node.y;
                int nX = dx[i] + node.x;

                if(!isValid(nY,nX)) continue;

                if(idxArr[nY][nX] == 0 && map[nY][nX] == '0'){
                    idxArr[nY][nX] = curIdx;
                    queue.add(new Node(nY,nX));
                }
            }
        }

        int val = sum;
        idxSumMap.put(curIdx,val);
    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX <0 || nY >=N || nX >=M) return false;
        return true;
    }

}










