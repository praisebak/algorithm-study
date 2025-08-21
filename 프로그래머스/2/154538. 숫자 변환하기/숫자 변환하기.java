import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        // 방문 여부 및 연산 횟수를 저장할 배열
        int[] visit = new int[1000001];
        Queue<Integer> queue = new LinkedList<>();

        // 시작점 초기화
        visit[x] = 1; // 0으로 하면 미방문과 구분이 안 되므로 1로 시작 (count는 0)
        queue.add(x);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 목표 지점에 도달하면 연산 횟수 반환
            // visit[current]는 1부터 시작했으므로 1을 빼준다.
            if (current == y) {
                return visit[y] - 1;
            }

            // 1. +n 연산
            int next1 = current + n;
            if (next1 <= y && visit[next1] == 0) {
                visit[next1] = visit[current] + 1;
                queue.add(next1);
            }

            // 2. *2 연산
            int next2 = current * 2;
            if (next2 <= y && visit[next2] == 0) {
                visit[next2] = visit[current] + 1;
                queue.add(next2);
            }

            // 3. *3 연산
            int next3 = current * 3;
            if (next3 <= y && visit[next3] == 0) {
                visit[next3] = visit[current] + 1;
                queue.add(next3);
            }
        }

        // 큐가 비워질 때까지 y에 도달하지 못하면 불가능한 경우
        return -1;
    }
}