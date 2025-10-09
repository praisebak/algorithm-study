import java.util.*;

class Solution {
    int weakN;
    int distN;
    int[] dist;
    int[] weak;
    int answer = Integer.MAX_VALUE;
    int n;
    boolean[] visit;

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        this.weakN = weak.length;
        this.distN = dist.length;

        Arrays.sort(dist); // 큰 값부터 사용하면 더 최적화 가능
        visit = new boolean[weakN];
        comb(distN - 1, 0, 0);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public void comb(int distI, int count, int len) {
        if (count == weakN) {
            answer = Math.min(answer, len);
            return;
        }
        if (distI == -1) return;

        for (int i = 0; i < weakN; i++) {
            if (visit[i]) continue;

            // 현재 상태 저장 (깊은 복사)
            boolean[] tmp = Arrays.copyOf(visit, weakN);

            visit[i] = true;
            int newCount = count + 1;
            int friendDist = dist[distI];

            // 시계 방향으로 weakN만큼 탐색 (wrap-around)
            for (int j = 1; j < weakN; j++) {
                int idx = (i + j) % weakN;
                if (visit[idx]) continue;

                int distToNext = (weak[idx] - weak[i] + n) % n; // 시계 방향 거리
                if (distToNext <= friendDist) {
                    visit[idx] = true;
                    newCount++;
                } else {
                    break; // 현재 친구로 더 이상 이동 불가
                }
            }

            comb(distI - 1, newCount, len + 1);

            // 이전 상태 복원
            visit = tmp;
        }
    }
}
