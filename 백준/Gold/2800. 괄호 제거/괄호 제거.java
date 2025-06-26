import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve {
    // 괄호 쌍의 시작과 끝 인덱스를 저장하는 내부 클래스
    static class Pair {
        int left;
        int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private Set<String> resultSet = new HashSet<>(); // 생성된 모든 유효한 식을 저장 (중복 제거)
    private List<Pair> bracketPairs = new ArrayList<>(); // 원본 문자열의 모든 괄호 쌍 정보
    private String originalExpression; // 원본 수식

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        originalExpression = bufferedReader.readLine(); // 원본 수식 읽기

        // 1. 모든 괄호 쌍의 인덱스를 미리 식별
        findBracketPairs();

        // 2. 백트래킹 (DFS)을 통해 괄호 제거 조합 탐색
        // index: 현재 처리할 괄호 쌍의 인덱스
        // removed: 각 괄호 쌍이 제거되었는지 여부를 표시하는 boolean 배열
        dfs(0, new boolean[bracketPairs.size()]);

        // 3. 결과 정렬 및 출력
        List<String> sortedResults = new ArrayList<>(resultSet);
        Collections.sort(sortedResults); // 사전순 정렬

        StringBuilder stringBuilder = new StringBuilder();
        for (String result : sortedResults) {
            stringBuilder.append(result).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    // 스택을 이용하여 모든 괄호 쌍의 인덱스를 찾는 메서드
    private void findBracketPairs() {
        Stack<Integer> stack = new Stack<>(); // 여는 괄호의 인덱스를 저장
        for (int i = 0; i < originalExpression.length(); i++) {
            char ch = originalExpression.charAt(i);
            if (ch == '(') {
                stack.push(i); // 여는 괄호 인덱스 푸시
            } else if (ch == ')') {
                // 닫는 괄호를 만나면 스택에서 짝이 되는 여는 괄호 인덱스를 팝
                bracketPairs.add(new Pair(stack.pop(), i));
            }
        }
    }

    /**
     * 백트래킹을 통해 괄호 쌍 제거 조합을 탐색하는 DFS 메서드
     * @param index 현재 처리할 괄호 쌍의 bracketPairs 리스트 내 인덱스
     * @param removed 각 괄호 쌍이 제거되었는지 여부를 나타내는 boolean 배열
     */
    private void dfs(int index, boolean[] removed) {
        // 모든 괄호 쌍에 대한 제거 여부가 결정되었을 때 (종료 조건)
        if (index == bracketPairs.size()) {
            StringBuilder currentResult = new StringBuilder();
            boolean isAnyBracketRemoved = false; // 실제로 괄호가 하나라도 제거되었는지 확인

            for (int i = 0; i < originalExpression.length(); i++) {
                boolean isCurrentCharMarkedForRemoval = false; // 현재 문자가 제거 대상 괄호인지

                // 모든 괄호 쌍을 순회하며 현재 문자가 제거 대상 괄호에 속하는지 확인
                for (int j = 0; j < bracketPairs.size(); j++) {
                    if (removed[j]) { // j번째 괄호 쌍이 제거 대상으로 표시되었다면
                        // 현재 문자의 인덱스가 이 괄호 쌍의 시작 또는 끝 인덱스와 일치하는지 확인
                        if (i == bracketPairs.get(j).left || i == bracketPairs.get(j).right) {
                            isCurrentCharMarkedForRemoval = true;
                            isAnyBracketRemoved = true; // 적어도 하나의 괄호 쌍이 제거됨
                            break; // 더 이상 이 문자에 대해 다른 괄호 쌍을 확인할 필요 없음
                        }
                    }
                }
                // 제거 대상이 아닌 문자만 결과에 추가
                if (!isCurrentCharMarkedForRemoval) {
                    currentResult.append(originalExpression.charAt(i));
                }
            }

            // 아무 괄호도 제거하지 않은 원본 문자열은 문제 조건에 따라 결과에 포함하지 않음
            if (isAnyBracketRemoved) {
                resultSet.add(currentResult.toString());
            }
            return; // 재귀 종료
        }

        // --- 재귀 단계: 현재 index의 괄호 쌍을 처리 ---

        // 1. 현재 괄호 쌍(index)을 제거하지 않는 경우
        removed[index] = false; // 제거 안 함으로 표시
        dfs(index + 1, removed); // 다음 괄호 쌍으로 재귀

        // 2. 현재 괄호 쌍(index)을 제거하는 경우
        removed[index] = true; // 제거 함으로 표시
        dfs(index + 1, removed); // 다음 괄호 쌍으로 재귀
    }
}