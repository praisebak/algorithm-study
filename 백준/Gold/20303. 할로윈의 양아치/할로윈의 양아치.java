import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve {

    private int[] parent;
    // 삭제: private boolean[] visit;
    private int[] vals;
    private int K;
    private List<Result> results;

    // Result 클래스는 그대로 사용합니다.
    class Result {
        int sum;
        int len;

        public Result(int sum, int len) {
            this.sum = sum;
            this.len = len;
        }
    }

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        K = Integer.parseInt(sArr[2]);

        vals = new int[N + 1];

        sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            vals[i + 1] = Integer.parseInt(sArr[i]);
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int left = Integer.parseInt(sArr[0]);
            int right = Integer.parseInt(sArr[1]);
            union(left, right);
        }

        // ▼▼▼▼▼ 핵심 수정 부분 시작 ▼▼▼▼▼

        // 1. 각 그룹의 정보를 임시로 저장할 배열 생성
        var groupSizes = new int[N + 1];
        var groupCosts = new int[N + 1];

        // 2. 모든 아이들을 순회하며 그룹 정보 집계 (전수 조사)
        for (int i = 1; i <= N; i++) {
            int root = find(i); // 각 아이의 최종 대표(루트)를 찾음
            groupSizes[root]++; // 대표에게 인원수 +1
            groupCosts[root] += vals[i]; // 대표에게 사탕값 누적
        }

        // 3. 집계된 정보를 기존 results 리스트에 변환하여 저장
        results = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (groupSizes[i] > 0) { // 인원이 있는 그룹(즉, 루트)만 리스트에 추가
                results.add(new Result(groupCosts[i], groupSizes[i]));
            }
        }

        // ▲▲▲▲▲ 핵심 수정 부분 끝 ▲▲▲▲▲


        var dp = new int[K]; // K명의 아이들 미만이므로, 배열 크기는 K (인덱스 0 ~ K-1)

        // 이 DP 로직은 올바른 `results` 정보가 들어오면 정상적으로 동작합니다.
        for (Result result : results) {
            for (int i = K - 1; i >= result.len; i--) {
                dp[i] = Math.max(dp[i], dp[i - result.len] + result.sum);
            }
        }

        // 최종 정답은 dp 배열의 전체 값 중 최댓값입니다.
        int answer = 0;
        for (int val : dp) {
            answer = Math.max(answer, val);
        }
        System.out.println(answer);
    }

    // 삭제: calSum 메서드

    private void union(int cur, int next) {
        int c = find(cur);
        int n = find(next);
        if (c == n) return;
        if (c < n) {
            parent[n] = c;
        } else {
            parent[c] = n;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        // 경로 압축(Path Compression)으로 성능 개선
        return parent[x] = find(parent[x]);
    }
}