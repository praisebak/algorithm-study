import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // ✅ 그래프 구성 (양방향)
        for (int[] edge : costs) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
        }

        // ✅ Prim 알고리즘 수행
        return prim(n, graph);
    }

    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static int prim(int n, List<List<Node>> graph) {
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.offer(new Node(0, 0)); // 임의의 시작 정점 (0)

        int total = 0;
        int count = 0;

        while (!pq.isEmpty() && count < n) {
            Node now = pq.poll();
            if (visited[now.to]) continue;
            visited[now.to] = true;
            total += now.weight;
            count++;

            for (Node next : graph.get(now.to)) {
                if (!visited[next.to]) pq.offer(next);
            }
        }

        return total;
    }
}
