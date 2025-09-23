import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        // 1. 종료 지점(end)을 기준으로 오름차순 정렬합니다.
        Arrays.sort(targets, Comparator.comparingInt(o -> o[1]));

        int answer = 0;
        int interceptionPoint = -1; // 현재 요격 미사일의 위치

        // 2. 정렬된 미사일을 순회합니다.
        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            // 3. 현재 미사일이 이전 요격 미사일의 범위를 벗어난 경우
            if (start >= interceptionPoint) {
                // 4. 새로운 요격 미사일이 필요함
                answer++;
                interceptionPoint = end; // 요격 지점을 현재 미사일의 끝으로 갱신
            }
        }

        return answer;
    }
}