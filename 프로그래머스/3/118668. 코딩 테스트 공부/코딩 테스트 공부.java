import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxA = 0;
        int maxB = 0;
        // 1. 모든 문제를 풀기 위한 최종 목표 능력치를 계산합니다.
        for (int[] p : problems) {
            maxA = Math.max(maxA, p[0]);
            maxB = Math.max(maxB, p[1]);
        }

        // 2. 이미 목표치를 달성했다면 즉시 0을 반환합니다.
        if (alp >= maxA && cop >= maxB) {
            return 0;
        }

        // 3. 시작 능력치가 목표치를 초과한 경우, 목표치로 맞춰줍니다.
        //    (e.g., 목표 alp가 150인데 현재 160이라면, 150으로 간주)
        alp = Math.min(alp, maxA);
        cop = Math.min(cop, maxB);

        // 4. DP 테이블을 초기화합니다. dp[i][j]는 능력치 (i, j)를 달성하는 데 걸리는 최소 시간입니다.
        int[][] dp = new int[maxA + 1][maxB + 1];
        for (int i = 0; i <= maxA; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 5. 시작 상태를 설정합니다.
        dp[alp][cop] = 0;

        // 6. DP 테이블을 채워나갑니다.
        for (int i = alp; i <= maxA; i++) {
            for (int j = cop; j <= maxB; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue; // 도달할 수 없는 상태는 건너뜁니다.
                }

                // 선택 1: 알고리즘 공부 (시간 1 소요, 알고력 1 증가)
                if (i + 1 <= maxA) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }

                // 선택 2: 코딩 공부 (시간 1 소요, 코딩력 1 증가)
                if (j + 1 <= maxB) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 선택 3: 문제 풀기
                for (int[] p : problems) {
                    // 현재 능력치로 이 문제를 풀 수 있다면
                    if (p[0] <= i && p[1] <= j) {
                        int nextA = Math.min(maxA, i + p[2]); // 능력치는 목표치를 넘지 않도록 Capping
                        int nextB = Math.min(maxB, j + p[3]);
                        dp[nextA][nextB] = Math.min(dp[nextA][nextB], dp[i][j] + p[4]);
                    }
                }
            }
        }

        // 7. 최종 목표 능력치(maxA, maxB)에 도달하는 데 걸린 최소 시간을 반환합니다.
        return dp[maxA][maxB];
    }
}