import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        while (true) {
            String[] sArr = br.readLine().split(" ");
            int u = Integer.parseInt(sArr[0]);
            int v = Integer.parseInt(sArr[1]);

            if (u == -1 && v == -1) break;

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] scores = new int[N + 1];
        int minScore = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int score = bfs(i);
            scores[i] = score;
            minScore = Math.min(minScore, score);
        }

        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (scores[i] == minScore) {
                candidates.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minScore).append(" ").append(candidates.size()).append("\n");
        for (int c : candidates) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }

    // BFS로 각 노드의 eccentricity (가장 멀리 있는 거리)를 구함
    static int bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) return Integer.MAX_VALUE; // 연결되지 않은 경우
            max = Math.max(max, dist[i]);
        }
        return max;
    }
}
