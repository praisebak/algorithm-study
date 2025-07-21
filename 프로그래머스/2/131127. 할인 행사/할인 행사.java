import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        Map<String, Integer> originalMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            originalMap.put(want[i], number[i]);
        }

        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> tempMap = new HashMap<>(originalMap);
            for (int j = i; j < i + 10; j++) {
                String item = discount[j];
                if (tempMap.containsKey(item)) {
                    tempMap.put(item, tempMap.get(item) - 1);
                }
            }

            boolean isValid = true;
            for (int cnt : tempMap.values()) {
                if (cnt > 0) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) answer++;
        }

        return answer;
    }
}
