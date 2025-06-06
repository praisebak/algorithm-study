
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int[] dy = { -1,1,0,0};
    private int[] dx = {0,0,-1,1};

    class Node{
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[][] likeMap = new int[(int) (Math.pow(N,2)+1)][4];
        int[][] resultMap = new int[N+1][N+1];

        List<Integer> likePeople = new ArrayList<>();

        for (int i = 0; i < Math.pow(N,2); i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int curNum = Integer.parseInt(sArr[0]);
            for (int j = 0; j < 4; j++) {
                int nextNum = Integer.parseInt(sArr[j+1]);
                likeMap[curNum][j] = nextNum;
            }
            likePeople.add(curNum);
        }

        for(int curPeople : likePeople){
            int maxLikeCount = 0;

            List<Node> nodeList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(resultMap[i][j] != 0) continue;
                    int curLikeCount = 0;

                    for (int k = 0; k < 4; k++) {
                        int nY = dy[k] + i;
                        int nX = dx[k] + j;

                        if(nY < 0 || nY >= N || nX < 0 || nX >= N) continue;

                        int currentPeople = resultMap[nY][nX];
                        //좋아하는애 있으면 더하기
                        for (int likeIdx = 0;likeIdx < 4; likeIdx++){
                            if(currentPeople == likeMap[curPeople][likeIdx]){
                                curLikeCount++;
                                break;
                            }
                        }
                    }

                    if(curLikeCount > maxLikeCount){
                        nodeList.clear();
                        nodeList.add(new Node(i,j));
                    } else if (curLikeCount == maxLikeCount) {
                        nodeList.add(new Node(i,j));
                    }
                    maxLikeCount = Math.max(curLikeCount,maxLikeCount);
                }
            }
            if(nodeList.size() == 1){
                int y = nodeList.get(0).y;
                int x = nodeList.get(0).x;
                resultMap[y][x] = curPeople;
                continue;
            }

            List<Node> emptyList = new ArrayList<>();
            if(nodeList.size() >= 2){
                int maxEmptyCount = 0;
                for(Node node : nodeList){
                    int emptyCount = 0;
                    for (int i = 0; i < 4; i++) {
                        int nY = dy[i] + node.y;
                        int nX = dx[i] + node.x;
                        if(nY < 0 || nY >= N || nX < 0 || nX >= N) continue;
                        if(resultMap[nY][nX] == 0){
                            emptyCount++;
                        }
                    }

                    if(emptyCount > maxEmptyCount){
                        emptyList.clear();
                        emptyList.add(new Node(node.y,node.x));
                    }else if(emptyCount == maxEmptyCount){
                        emptyList.add(new Node(node.y,node.x));
                    }

                    maxEmptyCount = Math.max(emptyCount,maxEmptyCount);
               }
            }
            if(emptyList.size() == 1){
                int y = emptyList.get(0).y;
                int x = emptyList.get(0).x;
                resultMap[y][x] = curPeople;
                continue;
            }
            emptyList.sort((o1, o2) -> {
                if (o1.y == o2.y) {
                    return o1.x - o2.x;
                }
                return o1.y - o2.y;
            });

            Node front = emptyList.get(0);
            resultMap[front.y][front.x] = curPeople;
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int curNum = resultMap[i][j];
                int curLikeCount = 0;
                for (int k = 0; k < 4; k++) {
                    int nY = dy[k] + i;
                    int nX = dx[k] + j;

                    if(nY < 0 || nY >= N || nX < 0 || nX >= N) continue;

                    int currentPeople = resultMap[nY][nX];
                    //좋아하는애 있으면 더하기
                    for (int likeIdx = 0;likeIdx < 4; likeIdx++){
                        if(currentPeople == likeMap[curNum][likeIdx]){
                            curLikeCount++;
                            break;
                        }
                    }
                }
                if(curLikeCount == 0){
                    continue;
                }
                sum += (int) Math.pow(10,curLikeCount-1);
            }
        }
        System.out.println(sum);
    }
}
