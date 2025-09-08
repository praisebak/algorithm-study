import java.util.*;

class Solution {
    // <메뉴 조합, 주문 횟수>를 저장할 Map
    Map<String, Integer> combinationMap;

    public String[] solution(String[] orders, int[] course) {
        combinationMap = new HashMap<>();
        
        // 1. 각 order에 포함된 문자를 오름차순으로 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] orderChars = orders[i].toCharArray();
            Arrays.sort(orderChars);
            orders[i] = new String(orderChars);
        }
        
        // 2. 각 order에 대해 course에 명시된 길이의 모든 조합을 생성하여 Map에 저장
        for (String order : orders) {
            for (int courseLength : course) {
                if (order.length() >= courseLength) {
                    generateCombinations(order, "", 0, courseLength);
                }
            }
        }
        
        List<String> resultList = new ArrayList<>();
        
        // 3. course 길이별로 가장 많이 주문된 횟수를 찾음
        Map<Integer, Integer> maxCountMap = new HashMap<>();
        for (String key : combinationMap.keySet()) {
            int length = key.length();
            int count = combinationMap.get(key);
            maxCountMap.put(length, Math.max(maxCountMap.getOrDefault(length, 0), count));
        }

        // 4. 최대 주문 횟수를 만족하는 조합만 결과에 추가 (최소 2회 이상)
        for (int courseLength : course) {
            int maxCount = maxCountMap.getOrDefault(courseLength, 0);
            
            // 최소 2명 이상의 손님으로부터 주문된 조합만 후보에 포함
            if (maxCount >= 2) {
                for (String key : combinationMap.keySet()) {
                    if (key.length() == courseLength && combinationMap.get(key) == maxCount) {
                        resultList.add(key);
                    }
                }
            }
        }
        
        // 5. 결과를 사전순으로 정렬
        Collections.sort(resultList);
        
        return resultList.toArray(new String[0]);
    }

    /**
     * 재귀적으로 메뉴 조합을 생성하는 함수
     * @param order 현재 주문
     * @param currentCombination 현재까지 만들어진 조합
     * @param startIdx 조합을 만들 때 시작할 인덱스
     * @param targetLength 만들고자 하는 조합의 길이
     */
    public void generateCombinations(String order, String currentCombination, int startIdx, int targetLength) {
        // 목표 길이에 도달하면 Map에 저장하고 재귀 종료
        if (currentCombination.length() == targetLength) {
            combinationMap.put(currentCombination, combinationMap.getOrDefault(currentCombination, 0) + 1);
            return;
        }
        
        // 조합 생성
        for (int i = startIdx; i < order.length(); i++) {
            generateCombinations(order, currentCombination + order.charAt(i), i + 1, targetLength);
        }
    }
}