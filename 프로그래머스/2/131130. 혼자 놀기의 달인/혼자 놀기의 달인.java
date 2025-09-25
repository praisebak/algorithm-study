import java.util.*;

class Solution {
    boolean[] visit;
    int[] cards;
    List<Integer> lists = new ArrayList<>();

    public int solution(int[] cards) {
        this.cards = cards;
        // visit 배열 크기를 cards.length로 맞추고 0-based 인덱싱을 사용합니다.
        visit = new boolean[cards.length];

        // 1. 모든 상자를 순회하며 아직 방문하지 않은 그룹(사이클)을 찾습니다.
        for (int i = 0; i < cards.length; i++) {
            if (!visit[i]) {
                // i번째 상자에서 시작하는 그룹(사이클)을 찾고 그 크기를 lists에 추가합니다.
                findAndAddGroupSize(i);
            }
        }

        // 2. 그룹(사이클)이 2개 미만이면 점수는 0점입니다.
        if (lists.size() < 2) {
            return 0;
        }

        // 3. 찾은 그룹 크기들을 정렬하여 가장 큰 두 그룹의 점수를 곱합니다.
        Collections.sort(lists, Collections.reverseOrder());
        return lists.get(0) * lists.get(1);
    }

    // 기존 bfs 메서드를 사이클 탐색 및 크기 계산 로직으로 명확하게 수정합니다.
    public void findAndAddGroupSize(int startIndex) {
        int count = 0;
        int currentIndex = startIndex;

        // 현재 그룹(사이클)에 속한 상자들을 모두 방문처리하고 크기를 셉니다.
        // 이미 방문한 상자를 다시 만날 때까지 계속 따라갑니다.
        while (!visit[currentIndex]) {
            visit[currentIndex] = true;
            count++;
            // 카드 번호는 1부터 시작하고, 배열 인덱스는 0부터 시작하므로 -1을 해줍니다.
            currentIndex = cards[currentIndex] - 1;
        }
        
        // 찾은 그룹의 크기를 전역 리스트에 추가합니다.
        lists.add(count);
    }
}