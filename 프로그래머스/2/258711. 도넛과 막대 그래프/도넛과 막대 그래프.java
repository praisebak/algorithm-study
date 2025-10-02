import java.util.*;

class Solution {

    // 그래프 정보를 클래스 멤버로 이동시켜 메서드 간 공유
    private List<List<Integer>> graph;
    private int[] outDegree;

    public int[] solution(int[][] edges) {
        // 1. 진입/진출 차수 계산 및 그래프 생성 (효율적인 방식)
        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= maxNode; i++) {
            graph.add(new ArrayList<>());
        }
        outDegree = new int[maxNode + 1];
        int[] inDegree = new int[maxNode + 1];

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            outDegree[from]++;
            inDegree[to]++;
        }

        // 2. 생성 정점 찾기 (효율적인 방식)
        int creatorNode = -1;
        for (int i = 1; i <= maxNode; i++) {
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                creatorNode = i;
                break;
            }
        }

        // 3. 그래프 종류 판별
        int donutCount = 0;
        int barCount = 0;
        int eightCount = 0;
        
        boolean[] visited = new boolean[maxNode + 1];

        // 생성 정점에서 시작하는 각 서브 그래프에 대해 판별 실행
        for (int startNode : graph.get(creatorNode)) {
            if (!visited[startNode]) {
                int[] counts = bfsToClassify(startNode, visited);
                int nodeCount = counts[0];
                int edgeCount = counts[1];

                if (nodeCount == edgeCount) {
                    donutCount++;
                } else if (nodeCount == edgeCount + 1) {
                    barCount++;
                } else { // nodeCount < edgeCount + 1 (8자 모양)
                    eightCount++;
                }
            }
        }

        return new int[]{creatorNode, donutCount, barCount, eightCount};
    }

    /**
     * 하나의 BFS 탐색으로 정점 수와 간선 수를 세는 메서드
     * @param startNode 탐색 시작 노드
     * @param visited 전체 방문 배열
     * @return new int[]{정점 수, 간선 수}
     */
    private int[] bfsToClassify(int startNode, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;

        int nodeCount = 0;
        int edgeCount = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            nodeCount++;
            edgeCount += outDegree[curr]; // 현재 노드의 진출 간선 수를 더함

            for (int next : graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return new int[]{nodeCount, edgeCount};
    }
}