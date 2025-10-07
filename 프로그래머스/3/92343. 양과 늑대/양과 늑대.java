import java.util.*;

class Solution {
    int answer = 0;
    int[] info;
    List<Integer>[] tree;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        int n = info.length;
        tree = new List[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0); // 시작 노드
        dfs(0, 0, 0, nextNodes);
        return answer;
    }

    void dfs(int curr, int lamb, int wolf, List<Integer> nextNodes) {
        if (info[curr] == 0) lamb++;
        else wolf++;

        if (wolf >= lamb) return; // 늑대가 양보다 많으면 종료

        answer = Math.max(answer, lamb);

        List<Integer> nextList = new ArrayList<>(nextNodes);
        nextList.remove((Integer) curr); // 현재 노드 제거
        for (int child : tree[curr]) nextList.add(child); // 자식 노드 추가

        for (int next : nextList) {
            dfs(next, lamb, wolf, nextList);
        }
    }
}
