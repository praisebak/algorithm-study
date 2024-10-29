import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve {
    // 성냥개비의 필요 개수
    int[] matches = {6, 2, 5, 5, 4, 5, 6, 3, 7, 5}; // 인덱스 0은 사용하지 않음
    String MAX = "11111111111111111111111111111111111111111111111111111";
    int N;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(bufferedReader.readLine());
            long minStr = findMin(N);
            String maxStr = findMax(N);
            System.out.println(minStr + " " + maxStr);
        }
    }

    private String findMax(int here) {
        StringBuilder ret = new StringBuilder();
        if ((here & 1) != 0) { // 홀수일 경우
            ret.append('7');
            here -= 3;
        }
        while (here > 0) {
            ret.append('1');
            here -= 2;
        }
        return ret.toString();
    }

    private long findMin(int left) {
        long[] minDp = new long[101];
        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;

        String[] add = {"1", "7", "4", "2", "0", "8"};

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                if (i - j >= 0) { // 인덱스 범위 체크
                    String s = "" + minDp[i - j] + add[j - 2];
                    minDp[i] = Math.min(Long.parseLong(s), minDp[i]);
                }
            }
        }
        return minDp[left];
    }
}
