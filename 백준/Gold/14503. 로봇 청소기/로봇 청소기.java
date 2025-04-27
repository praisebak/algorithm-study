import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve {
    int[] dy = {-1, 0, 1, 0}; // 북동남서
    int[] dx = {0, 1, 0, -1};
    private int[][] map;
    int N, M;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);

        sArr = bufferedReader.readLine().split(" ");
        int curR = Integer.parseInt(sArr[0]);
        int curC = Integer.parseInt(sArr[1]);
        int curDir = Integer.parseInt(sArr[2]);
        map = new int[N][M]; // 수정: N+1, M+1 필요 없음
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        machineRun(curR, curC, curDir);
    }

    private void machineRun(int curR, int curC, int curDir) {
        int count = 0;

        // 시작 위치 청소
        if (map[curR][curC] == 0) {
            map[curR][curC] = 2;
            count++;
        }

        while (true) {
            boolean moved = false;

            // 왼쪽부터 차례로 탐색
            for (int i = 0; i < 4; i++) {
                curDir = (curDir + 3) % 4; // 왼쪽으로 회전
                int nY = curR + dy[curDir];
                int nX = curC + dx[curDir];

                if (!isValid(nY, nX)) continue;

                if (map[nY][nX] == 0) { // 청소되지 않은 칸
                    curR = nY;
                    curC = nX;
                    map[curR][curC] = 2;
                    count++;
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                // 네 방향 모두 청소/벽이면 뒤로
                int backDir = (curDir + 2) % 4;
                int nY = curR + dy[backDir];
                int nX = curC + dx[backDir];

                if (!isValid(nY, nX) || map[nY][nX] == 1) {
                    // 뒤가 벽이면 끝
                    System.out.println(count);
                    return;
                } else {
                    // 뒤로 이동
                    curR = nY;
                    curC = nX;
                }
            }
        }
    }

    private boolean isValid(int nY, int nX) {
        return nY >= 0 && nX >= 0 && nY < N && nX < M;
    }
}
