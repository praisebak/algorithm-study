class Solution {

    public int solution(int[] people, int limit) {
        int count = 0;
        int[] weights = new int[241]; // 몸무게 카운팅 배열 (40~240)

        // 각 몸무게별 인원 수 카운트
        for (int w : people) {
            weights[w]++;
        }

        // 무거운 사람부터 처리
        for (int i = 240; i >= 1; i--) {
            while (weights[i] > 0) {
                weights[i]--; // 가장 무거운 사람 태움

                // 같이 탈 수 있는 가장 가벼운 사람 탐색
                for (int j = 1; j <= limit - i; j++) {
                    if (weights[j] > 0) {
                        weights[j]--;
                        break;
                    }
                }

                count++; // 보트 하나 사용
            }
        }

        return count;
    }
}
