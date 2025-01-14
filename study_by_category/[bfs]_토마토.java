import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve {
    private List<Node> nodeList;
    private boolean[][][] visit;
    private int zeroSize;
    private int n;
    private int m;
    private int h;
    private int[][][] arr;

    class Node {
        public Node(int i, int w, int h, int weight) {
            this.i = i;
            this.w = w;
            this.h = h;
            this.weight = weight;
        }
        int weight;
        int i;  // 높이
        int w;  // 가로
        int h;  // 세로
    }

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        m = Integer.parseInt(sArr[0]); // 가로
        n = Integer.parseInt(sArr[1]); // 세로
        h = Integer.parseInt(sArr[2]); // 높이

        nodeList = new ArrayList<>();
        arr = new int[h][n][m];
        visit = new boolean[h][n][m];
        zeroSize = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                sArr = bufferedReader.readLine().split(" ");
                for (int k = 0; k < m; k++) {
                    arr[i][j][k] = Integer.parseInt(sArr[k]);
                    if (arr[i][j][k] == 1) {
                        nodeList.add(new Node(i, k, j, 0));
                    } else if (arr[i][j][k] == 0) {
                        zeroSize++;
                    }
                }
            }
        }
        bfs(nodeList);
    }

    // 6방향: 위, 아래, 앞, 뒤, 왼쪽, 오른쪽
    int[] dh = {-1, 1, 0, 0, 0, 0}; // 높이 방향
    int[] dy = {0, 0, -1, 1, 0, 0}; // 세로 방향
    int[] dx = {0, 0, 0, 0, -1, 1}; // 가로 방향

    private void bfs(List<Node> nodeList) {
        int answerCount = 0;
        int answerTime = 0;
        
        if (zeroSize == 0) {
            System.out.println(0);
            return;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        for (Node node : nodeList) {
            queue.add(node);
            visit[node.i][node.h][node.w] = true;
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            answerTime = Math.max(answerTime, cur.weight);

            for (int i = 0; i < 6; i++) {  // 6방향 체크
                int nH = cur.i + dh[i];  // 높이
                int nY = cur.h + dy[i];  // 세로
                int nX = cur.w + dx[i];  // 가로

                if (!isValid(nH, nY, nX)) continue;
                if (visit[nH][nY][nX]) continue;
                if (arr[nH][nY][nX] != 0) continue;

                visit[nH][nY][nX] = true;
                answerCount++;
                queue.add(new Node(nH, nX, nY, cur.weight + 1));
            }
        }

        if (answerCount == zeroSize) {
            System.out.println(answerTime);
        } else {
            System.out.println(-1);
        }
    }

    private boolean isValid(int nH, int nY, int nX) {
        return nH >= 0 && nH < h && nY >= 0 && nY < n && nX >= 0 && nX < m;
    }
}
