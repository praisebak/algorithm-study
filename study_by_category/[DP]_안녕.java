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
    int N;
    int[] healthArr;
    int[] joyArr;
    int maxJoy;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        healthArr = new int[N];
        joyArr = new int[N];
        maxJoy = 0;

        String[] health = br.readLine().split(" ");
        String[] joy = br.readLine().split(" ");

        int currentJoy = 0;
        for (int i = 0; i < N; i++) {
            healthArr[i] = Integer.parseInt(health[i]);
            joyArr[i] = Integer.parseInt(joy[i]);
            // health가 0인 아이템은 무조건 선택
            if (healthArr[i] == 0) {
                currentJoy += joyArr[i];
            }
        }

        // 남은 체력과 현재 인덱스로 재귀 호출
        findMaxJoy(100, currentJoy, 0);
        System.out.println(maxJoy);
    }

    private void findMaxJoy(int remainHealth, int currentJoy, int index) {
        // 현재까지의 기쁨이 최대값보다 크면 갱신
        maxJoy = Math.max(maxJoy, currentJoy);

        // 모든 아이템에 대해 선택/비선택 시도
        for (int i = index; i < N; i++) {
            // health가 0인 아이템은 이미 처리했으므로 스킵
            if (healthArr[i] == 0) continue;

            // 현재 아이템을 선택할 수 있는 경우
            if (remainHealth - healthArr[i] > 0) {
                // 현재 아이템 선택하고 재귀 호출
                findMaxJoy(remainHealth - healthArr[i],
                        currentJoy + joyArr[i],
                        i + 1);
            }
        }
    }
}
