import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // 1. 원하는 제품과 수량을 map에 저장 (기존 코드와 동일)
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }

        // 2. 첫 10일간의 할인 품목으로 필요 수량 차감 (기존 코드와 동일)
        // 이 작업으로 map은 "1일차에 가입했을 때 10일 후 남은 필요 품목 수량" 상태가 됨
        for (int i = 0; i < 10; i++) {
            String item = discount[i];
            if (map.containsKey(item)) {
                map.put(item, map.get(item) - 1);
            }
        }
        
        // 3. [수정된 부분 1] 첫 10일(가입일 1일차)에 대한 성공 여부 확인
        boolean isSuccess = true;
        for (String key : want) {
            if (map.get(key) > 0) { // 아직 더 필요한 품목이 있다면
                isSuccess = false;
                break;
            }
        }
        if (isSuccess) {
            answer++; // 성공했으므로 카운트 증가
        }

        // 4. [수정된 부분 2] 11일차부터 할인 마지막 날까지 슬라이딩 윈도우 진행
        // i는 새로 추가되는 할인 품목의 인덱스. (i-10)은 창문에서 빠지는 품목의 인덱스.
        for (int i = 10; i < discount.length; i++) {
            // 창문에서 가장 오래된 날(i-10)의 품목은 다시 필요 수량에 추가
            String prevItem = discount[i - 10];
            if (map.containsKey(prevItem)) {
                map.put(prevItem, map.get(prevItem) + 1);
            }
            
            // 새로 들어온 날(i)의 품목은 필요 수량에서 차감
            String nextItem = discount[i];
            if (map.containsKey(nextItem)) {
                map.put(nextItem, map.get(nextItem) - 1);
            }
            
            // 현재 상태가 모든 조건을 만족하는지 다시 확인
            isSuccess = true;
            for (String key : want) {
                if (map.get(key) > 0) {
                    isSuccess = false;
                    break;
                }
            }
            
            // 만족하면 정답 카운트 증가 (기존의 break 제거)
            if (isSuccess) {
                answer++;
            }
        }
        
        return answer;
    }
}