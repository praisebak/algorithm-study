import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Main {

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve {

    class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private int N, M, P;
    private int[] moves;
    private char[][] map;
    private Queue<Node>[] playerQueues;

    private final int[] dy = {-1, 1, 0, 0};
    private final int[] dx = {0, 0, -1, 1};

    /**
     * 새롭게 정의된 moves 함수입니다.
     * 이 함수는 더 이상 재귀가 아닙니다.
     * 역할: 주어진 큐(currentFrontier)에 있는 모든 성들로부터 '정확히 한 칸만' 확장하고,
     * 그 결과로 새로 생긴 성들의 목록(큐)을 반환합니다.
     * @param currentFrontier 현재 턴에 확장할 성들의 큐
     * @param playerNumber 현재 플레이어 번호
     * @return 한 칸 확장 결과로 새로 생긴 성들의 큐
     */
    private Queue<Node> moves(Queue<Node> currentFrontier, int playerNumber) {
        Queue<Node> nextFrontier = new LinkedList<>();
        // 큐에 있는 모든 성에서 동시에 한 칸 확장을 시도
        while (!currentFrontier.isEmpty()) {
            Node currentNode = currentFrontier.poll();

            for (int d = 0; d < 4; d++) {
                int nY = currentNode.y + dy[d];
                int nX = currentNode.x + dx[d];

                if (nY < 0 || nX < 0 || nY >= N || nX >= M || map[nY][nX] != '.') {
                    continue;
                }
                
                map[nY][nX] = (char) (playerNumber + '0');
                nextFrontier.add(new Node(nY, nX));
            }
        }
        return nextFrontier;
    }

    @SuppressWarnings("unchecked")
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = br.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        P = Integer.parseInt(sArr[2]);

        moves = new int[P + 1];
        sArr = br.readLine().split(" ");
        for (int i = 0; i < P; i++) {
            moves[i + 1] = Integer.parseInt(sArr[i]);
        }

        map = new char[N][M];
        playerQueues = new LinkedList[P + 1];
        for (int i = 1; i <= P; i++) {
            playerQueues[i] = new LinkedList<>();
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (Character.isDigit(map[i][j])) {
                    int playerIdx = map[i][j] - '0';
                    playerQueues[playerIdx].add(new Node(i, j));
                }
            }
        }
        
        // 게임 루프
        while (true) {
            boolean expansionOccurredInThisRound = false;

            // 1번 ~ P번 플레이어 턴 진행
            for (int p = 1; p <= P; p++) {
                // 이동력만큼 'moves' 함수를 반복 호출하여 한 칸씩 확장
                for (int move = 0; move < moves[p]; move++) {
                    if (playerQueues[p].isEmpty()) {
                        break;
                    }

                    // moves 함수로 한 칸 확장하고, 새로 생긴 성들을 다시 큐에 넣음
                    Queue<Node> nextQueue = moves(playerQueues[p], p);
                    
                    if (!nextQueue.isEmpty()) {
                        expansionOccurredInThisRound = true;
                    }
                    playerQueues[p] = nextQueue;
                }
            }

            if (!expansionOccurredInThisRound) {
                break;
            }
        }

        // 결과 집계
        int[] resultCounts = new int[P + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Character.isDigit(map[i][j])) {
                    resultCounts[map[i][j] - '0']++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) {
            sb.append(resultCounts[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}