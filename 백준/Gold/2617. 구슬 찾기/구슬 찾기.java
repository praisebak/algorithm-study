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

    public void solve() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var sArr = br.readLine().split(" ");
        int n = Integer.parseInt(sArr[0]);
        int m = Integer.parseInt(sArr[1]);

        var graph = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            sArr = br.readLine().split(" ");
            int heavy = Integer.parseInt(sArr[0]);
            int light = Integer.parseInt(sArr[1]);
            graph[heavy][light] = true;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        var heavierCounts = new int[n + 1];
        var lighterCounts = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (graph[i][j]) { // i가 j보다 무겁다
                    lighterCounts[i]++;
                    heavierCounts[j]++;
                }
            }
        }

        int answer = 0;
        int limit = (n - 1) / 2;
        for (int i = 1; i <= n; i++) {
            if (heavierCounts[i] > limit || lighterCounts[i] > limit) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}