import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int n = gems.length;
        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int typeCount = gemTypes.size();
        
        Map<String, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int minLen = n + 1;
        int[] answer = new int[2];
        
        while (right < n) {
            // 현재 보석 추가
            window.put(gems[right], window.getOrDefault(gems[right], 0) + 1);
            right++;
            
            // 모든 보석 종류가 포함되면 left 줄이기
            while (window.size() == typeCount) {
                if (right - left < minLen) {
                    minLen = right - left;
                    answer[0] = left + 1;  // 1-based index
                    answer[1] = right;     // 1-based index
                }
                
                // left 보석 제거
                String leftGem = gems[left];
                window.put(leftGem, window.get(leftGem) - 1);
                if (window.get(leftGem) == 0) {
                    window.remove(leftGem);
                }
                left++;
            }
        }
        
        return answer;
    }
}
