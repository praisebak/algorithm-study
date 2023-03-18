
import java.util.*;

class MyNum implements Comparable<MyNum> {
    int num;
    int count;

    MyNum(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo(MyNum o) {
        int result = count - o.count;
        if (result == 0)
            result = num - o.num;
        return result;
    }
}

class Solution {
    public static void main(String[] args) {
        Solve solve = new Solve();
        solve.init();
        solve.solve();

    }
}

class Solve {
    int N;
    int M;
    int K;
    Scanner sc = new Scanner(System.in);
    int[][] map = new int[100][100];
    int yLen = 3;
    int xLen = 3;

    public void init() {
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = sc.nextInt();
            }
        }
    }

    public void solve() {
        for (int i = 0; i <= 100; i++) {
            if (map[N - 1][M - 1] == K) {
                System.out.println(i);
                return;
            }

            if (yLen >= xLen) {
                // System.out.print("R");
                R();
            } else {
                // System.out.print("C");
                C();
            }
            // System.out.println(i + " 번째" + yLen + "," + xLen);
            // print();
        }
        System.out.println(-1);

    }

    private void print() {
        System.out.println();
        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void R() {
        int tLen = 0;
        for (int i = 0; i < yLen; i++) {
            HashMap<Integer, Integer> countMap = new HashMap<>();
            for (int j = 0; j < xLen; j++) {
                int num = map[i][j];
                if (num == 0)
                    continue;
                int v = countMap.getOrDefault(num, 0) + 1;
                countMap.put(num, v);
            }
            List<MyNum> list = new ArrayList<>();
            for (Integer key : countMap.keySet()) {
                list.add(new MyNum(key, countMap.get(key)));
            }
            list.sort(null);

            int tmpLen = 0;
            // 0 1, 2 3, 98 99
            for (int j = 0; j < 99 && j < list.size(); j++) {
                MyNum cur = list.get(j);
                map[i][tmpLen++] = cur.num;
                map[i][tmpLen++] = cur.count;
            }
            // xLen 0부터 세야하는거 아닌가..
            // 일단 해보고 맞다뜨면 0부터 세도록해서 다시 제출해보기
            tLen = Math.max(tLen, tmpLen);

            for (int j = tmpLen; j < 100; j++) {
                map[i][j] = 0;
            }
        }
        xLen = tLen;
    }

    private void C() {
        int tLen = 0;
        for (int i = 0; i < xLen; i++) {
            HashMap<Integer, Integer> countMap = new HashMap<>();
            for (int j = 0; j < yLen; j++) {
                int num = map[j][i];
                if (num == 0)
                    continue;
                int v = countMap.getOrDefault(num, 0) + 1;
                countMap.put(num, v);
            }

            List<MyNum> list = new ArrayList<>();
            for (Integer key : countMap.keySet()) {
                list.add(new MyNum(key, countMap.get(key)));
            }
            list.sort(null);

            int tmpLen = 0;
            // 0 1, 2 3, 98 99
            for (int j = 0; j < 100 && j < list.size(); j++) {
                MyNum cur = list.get(j);
                map[tmpLen++][i] = cur.num;
                map[tmpLen++][i] = cur.count;
            }

            // xLen 0부터 세야하는거 아닌가..
            // 일단 해보고 맞다뜨면 0부터 세도록해서 다시 제출해보기
            tLen = Math.max(tLen, tmpLen);
            for (int j = tmpLen; j < 100; j++) {
                map[j][i] = 0;
            }

        }
        yLen = tLen;

    }
}
