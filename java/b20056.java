
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

class Pair {
    int y;
    int x;

    Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class FireBall {
    int y;
    int x;
    int m;
    int d;
    int s;

    FireBall(int y, int x, int m, int d, int s) {
        this.y = y;
        this.x = x;
        this.m = m;
        this.d = d;
        this.s = s;
    }

}

class Solution {
    static public void main(String[] args) {
        Solve solve = new Solve();
        solve.start();
    }
}

class Solve {
    int N;
    int M;
    int K;
    List<FireBall>[][] map;
    List<FireBall> fireBalls;
    // 상 상우 우 우하 하 좌하 좌 좌상

    int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
    int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);

    public void start() {
        init();
        solve();
    }

    private void solve() {
        // k번 명령
        for (int i = 0; i < K; i++) {
            move();
            fireBallDiv();
        }
        print();

    }

    private void fireBallDiv() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int mSum = 0;
                int sSum = 0;
                int d = -1;
                int count = map[i][j].size();
                if (count >= 2) {
                    while (map[i][j].size() != 0) {
                        FireBall f = map[i][j].remove(0);
                        mSum += f.m;
                        sSum += f.s;
                        if (d != 2) {
                            // 짝
                            if (d == -1 && f.d % 2 == 0)
                                d = 0;
                            else if (d == -1 && f.d % 2 == 1)
                                d = 1;
                            else if (d != (f.d % 2)) {
                                d = 2;
                            }
                        }
                        fireBalls.remove(f);
                    }
                    mSum = mSum / 5;
                    sSum = sSum / count;
                    if (mSum == 0)
                        continue;

                    if (d == 2)
                        d = 1;
                    else
                        d = 0;

                    for (int k = 0; k < 8; k += 2) {
                        fireBalls.add(new FireBall(i, j, mSum, k + d, sSum));
                    }
                } else {
                    map[i][j].clear();
                }
            }
        }

    }

    private void print() {
        int sum = 0;
        for (FireBall fb : fireBalls) {
            sum += fb.m;
        }
        System.out.println(sum);
    }

    private void move() {
        for (FireBall fb : fireBalls) {
            fb.y = (N + fb.y + dy[fb.d] * (fb.s % N)) % N + 1;
            fb.x = (N + fb.x + dx[fb.d] * (fb.s % N)) % N + 1;
            map[fb.y][fb.x].add(fb);

        }
    }

    public void init() {
        try {
            String[] first = in.readLine().split(" ");
            N = Integer.parseInt(first[0]);
            M = Integer.parseInt(first[1]);
            K = Integer.parseInt(first[2]);

            map = new ArrayList[N + 1][N + 1];
            fireBalls = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] = new ArrayList<>();
                }
            }

            for (int i = 0; i < M; i++) {
                String[] tmp = in.readLine().split(" ");
                int y = Integer.parseInt(tmp[0]);
                int x = Integer.parseInt(tmp[1]);
                int m = Integer.parseInt(tmp[2]);
                int s = Integer.parseInt(tmp[3]);
                int d = Integer.parseInt(tmp[4]);
                fireBalls.add(new FireBall(y, x, m, d, s));

            }
        } catch (IOException e) {

        }
    }

}
