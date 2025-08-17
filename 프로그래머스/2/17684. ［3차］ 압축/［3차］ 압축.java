import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> dictionary = new HashMap<>();
        int nextCode = 1;

        // 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
        for (char c = 'A'; c <= 'Z'; c++) {
            dictionary.put(String.valueOf(c), nextCode++);
        }

        int index = 0;
        while (index < msg.length()) {
            String w = "";
            // 2. 현재 위치(index)에서 시작하는 가장 긴 문자열 w를 찾는다.
            for (int i = 1; index + i <= msg.length(); i++) {
                String wc = msg.substring(index, index + i);
                if (dictionary.containsKey(wc)) {
                    w = wc;
                } else {
                    break;
                }
            }

            // 3. w에 해당하는 사전의 색인 번호를 출력한다.
            answer.add(dictionary.get(w));

            // 4. 입력에서 처리되지 않은 다음 글자가 있다면(w + c), 사전에 새로 등록한다.
            if (index + w.length() < msg.length()) {
                char c = msg.charAt(index + w.length());
                dictionary.put(w + c, nextCode++);
            }

            // 5. 인덱스를 처리한 문자열(w)의 길이만큼 이동시킨다.
            index += w.length();
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}