//인구 이동
/* Java(자바) Hello, World! 예제 */

import java.util.*;

//치킨집 폐업시키고나서 거리가 필요하니까 계산과정에서 더하자
class TmpCode {
    static public void main(String[] args) {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Pair {
    int y;
    int x;

    Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solve {
    // 상하좌우
    int[] dx = { 0, 0, -1, 1 };
    int[] dy = { -1, 1, 0, 0 };
    int N;
    int L;
    int R;
    int[][] map;
    private boolean[][] visit;
    int countArea;
    ArrayList<Pair> list = new ArrayList<>();

    public void solve() {
        init();
        start();
    }

    // bufferedReader로 예쁘게 받는 방법 참고하기
    private void start() {

        // 연합 개수 row,cal,count
        // dfs로 연합계산하기
        int day = 0;
        boolean flag = false;
        while (!flag) {
            // 인구이동 일어나지않으면 true임
            flag = true;
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        countArea = 1;
                        list = new ArrayList<>();
                        list.add(new Pair(i, j));
                        // visit 체크 및 sum과 count 계산
                        visit[i][j] = true;
                        int sum = dfs(i, j, 1, map[i][j]);
                        int aver = sum / countArea;
                        if (list.size() > 1) {
                            flag = false;
                            for (Pair p : list) {
                                map[p.y][p.x] = aver;
                            }
                        }
                    }
                }
            }
            if (!flag)
                day++;
        }
        System.out.println(day);

    }

    private int dfs(int row, int cal, int count, int sum) {

        for (int i = 0; i < 4; i++) {
            int nY = row + dy[i];
            int nX = cal + dx[i];
            if (isValid(nY, nX, row, cal) && !visit[nY][nX]) {
                visit[nY][nX] = true;
                countArea++;
                list.add(new Pair(nY, nX));
                sum = Math.max(dfs(nY, nX, count + 1, sum + map[nY][nX]), sum);
            }
        }
        return sum;
    }

    private boolean isValid(int nY, int nX, int cY, int cX) {
        if (nY == -1 || nX == -1 || nY == N || nX == N)
            return false;
        int val = Math.abs(map[nY][nX] - map[cY][cX]);
        return val >= L && val <= R;
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
    }
}
