import java.util.Scanner;

class TmpCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] people = new int[N * N];
        int[][] love = new int[N * N + 1][4];

        int idx = 0;
        for (int i = 0; i < N * N; i++) {
            people[idx] = sc.nextInt();
            for (int j = 0; j < 4; j++) {
                love[people[idx]][j] = sc.nextInt();
            }
            idx++;
        }

        Solve solve = new Solve(N, people, love);
        solve.solve();

    }
}

class Solve {
    int[] dx = { 0, 0, -1, 1 };
    int[] dy = { -1, 1, 0, 0 };
    int N;
    int[] people;
    int[][] love;
    int[][] seats;

    Solve(int N, int[] people, int[][] love) {
        this.N = N;
        this.people = people;
        this.love = love;
        this.seats = new int[N][N];
    }

    public void solve() {
        for (int i : people) {
            findSeat(i);
        }
        cal();

    }

    private void cal() {
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int curChild = seats[i][j];
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        int nY = dy[l] + i;
                        int nX = dx[l] + j;
                        if (!isRangeOk(nY, nX))
                            continue;

                        if (love[curChild][k] == seats[nY][nX]) {
                            count++;
                            break;
                        }
                    }
                }
                int tmpNum = count >= 1 ? 1 : 0;
                for (int mul = 1; mul < count; mul++) {
                    tmpNum *= 10;
                }
                result += tmpNum;
            }
        }
        System.out.println(result);
    }

    public void print() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int getEmptyCount(int r, int c) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nY = r + dy[i];
            int nX = c + dx[i];
            if (isRangeOk(nY, nX)) {
                if (seats[nY][nX] == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    private void findSeat(int curChild) {
        int curLoveCount = -1;
        int y = 0;
        int x = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (seats[i][j] != 0)
                    continue;
                int newLoveCount = getLoveCount(i, j, curChild);
                if (newLoveCount >= curLoveCount) {
                    if (newLoveCount == curLoveCount) {
                        int newEmptyCount = getEmptyCount(i, j);
                        int curEmptyCount = getEmptyCount(y, x);
                        if (newEmptyCount < curEmptyCount) {
                            continue;
                        } else if (newEmptyCount == curEmptyCount) {
                            if (y > i) {

                            } else if (y == i) {
                                if (x <= j) {
                                    continue;
                                }

                            } else {
                                continue;
                            }
                        }
                    }
                    curLoveCount = newLoveCount;
                    y = i;
                    x = j;
                }
            }
        }

        seats[y][x] = curChild;
        // print();
        // System.out.println();

    }

    private int getLoveCount(int r, int c, int curChild) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nextY = r + dy[i];
            int nextX = c + dx[i];
            if (!isRangeOk(nextY, nextX))
                continue;
            for (int j = 0; j < 4; j++) {
                if (love[curChild][j] == seats[nextY][nextX]) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean isRangeOk(int nY, int nX) {
        if (nY < 0 || nX < 0 || nY == N || nX == N)
            return false;
        return true;
    }

}