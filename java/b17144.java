import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

class Main {
    static public void main(String[] args) {
        Solve solve = new Solve();
        solve.start();
    }
}

class Pair {
    int r;
    int c;
    int val;

    public Pair(int r, int c, int val) {
        this.r = r;
        this.c = c;
        this.val = val;
    }
}

class Solve {

    int N;
    int M;
    int S;
    int[][] map;

    // 상하좌우
    int[] dx = { 0, 0, -1, 1 };
    int[] dy = { -1, 1, 0, 0 };

    Pair up = null;
    Pair down = null;

    public void start() {
        init();
        solve();
    }

    private void solve() {
        for (int t = 0; t < S; t++) {
            dustExpand();
            blowStart(0);
            blowStart(1);
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != -1)
                    count += map[i][j];
            }
        }
        System.out.println(count);
    }

    private void blowStart(int mode) {
        int cY = mode == 0 ? up.r : down.r;
        int cX = mode == 0 ? up.c : down.c;
        int d = 3;
        int prev = 0;
        int tmp = 0;

        do {
            if (!isRangeOk2(cY + dy[d], cX + dx[d])) {
                // 상
                if (mode == 0) {
                    if (d == 3) {
                        d = 0;
                    } else if (d == 0) {
                        d = 2;
                    } else {
                        d = 1;
                    }
                } else {
                    if (d == 3) {
                        d = 1;
                    } else if (d == 1) {
                        d = 2;
                    } else {
                        d = 0;
                    }
                }
            }

            cY += dy[d];
            cX += dx[d];

            tmp = map[cY][cX];
            if (map[cY][cX] != -1)
                map[cY][cX] = prev;
            prev = tmp;

        } while (!(cY == up.r && cX == up.c));

    }

    private boolean isRangeOk2(int nY, int nX) {
        if (nY == -1 || nX == -1 || nY == N || nX == M)
            return false;
        return true;
    }

    private void dustExpand() {
        List<Pair> resultPair = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != -1 && map[i][j] != 0) {
                    int nVal = map[i][j] / 5;
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int nY = dy[d] + i;
                        int nX = dx[d] + j;

                        if (!isRangeOk(nY, nX))
                            continue;
                        count++;
                        resultPair.add(new Pair(nY, nX, nVal));
                    }
                    int remainVal = map[i][j] - (map[i][j] / 5) * count;
                    map[i][j] = remainVal;
                }

            }
        }
        for (Pair p : resultPair) {
            map[p.r][p.c] += p.val;
        }

    }

    private boolean isRangeOk(int nY, int nX) {
        if (nY == -1 || nX == -1 || nY == N || nX == M)
            return false;
        if (map[nY][nX] == -1)
            return false;
        return true;
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        S = sc.nextInt();
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == -1) {
                    if (up == null)
                        up = new Pair(i, j, 0);
                    else {
                        down = new Pair(i, j, 0);
                    }
                }
            }
        }

    }

}
