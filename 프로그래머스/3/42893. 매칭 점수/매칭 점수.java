import java.util.*;

class Solution {

    class Node {
        String url;        // 페이지 URL
        int outLinkCount;  // 외부 링크 수
        int basicScore;    // 기본점수
        public Node(String url, int outLinkCount, int basicScore) {
            this.url = url;
            this.outLinkCount = outLinkCount;
            this.basicScore = basicScore;
        }
    }

    public int solution(String word, String[] pages) {
        word = word.toLowerCase();

        int idx = 0;
        Map<Integer, Node> pageMap = new HashMap<>();          // idx -> Node
        Map<String, List<Integer>> linkMap = new HashMap<>(); // URL -> idx list (링크된 페이지)

        for (String page : pages) {
            String lowerPage = page.toLowerCase();

            // 1. meta URL 추출
            String url = "";
            int metaIdx = lowerPage.indexOf("<meta property=\"og:url\"");
            if (metaIdx >= 0) {
                int contentIdx = lowerPage.indexOf("content=\"https://", metaIdx);
                if (contentIdx >= 0) {
                    int start = contentIdx + "content=\"".length();
                    int end = lowerPage.indexOf("\"", start); // 바로 다음 큰따옴표까지
                    if (end > start) url = lowerPage.substring(start, end);
                }
            }

            // 2. 본문 텍스트 검색어 카운트
            int basicScore = 0;
            boolean inTag = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lowerPage.length(); i++) {
                char c = lowerPage.charAt(i);

                if (c == '<') {
                    inTag = true;
                    if (word.equals(sb.toString())) basicScore++;
                    sb.setLength(0);
                    continue;
                } else if (c == '>') {
                    inTag = false;
                    sb.setLength(0);
                    continue;
                }

                if (inTag) continue;

                if (Character.isAlphabetic(c)) {
                    sb.append(c);
                } else {
                    if (word.equals(sb.toString())) basicScore++;
                    sb.setLength(0);
                }
            }
            if (word.equals(sb.toString())) basicScore++; // 마지막 단어 체크

            // 3. 외부 링크 추출
            int outLinkCount = 0;
            int fromIdx = 0;
            while (true) {
                int aIdx = lowerPage.indexOf("<a", fromIdx);
                if (aIdx == -1) break;
                int hrefIdx = lowerPage.indexOf("href=\"https://", aIdx);
                if (hrefIdx == -1) break;
                int start = hrefIdx + "href=\"".length();
                int end = lowerPage.indexOf("\"", start);
                if (end == -1) break;
                String hrefLink = lowerPage.substring(start, end);

                outLinkCount++;
                List<Integer> idxList = linkMap.getOrDefault(hrefLink, new ArrayList<>());
                idxList.add(idx);
                linkMap.put(hrefLink, idxList);

                fromIdx = end;
            }

            pageMap.put(idx, new Node(url, outLinkCount, basicScore));
            idx++;
        }

        // 4. 링크 점수 계산 및 매칭 점수
        double maxScore = -1;
        int resultIdx = 0;

        for (Integer i : pageMap.keySet()) {
            Node node = pageMap.get(i);

            double linkScore = 0.0;
            List<Integer> linkedIdxList = linkMap.getOrDefault(node.url, new ArrayList<>());
            for (Integer linkedIdx : linkedIdxList) {
                Node linkedNode = pageMap.get(linkedIdx);
                if (linkedNode.outLinkCount > 0)
                    linkScore += (double) linkedNode.basicScore / linkedNode.outLinkCount;
            }

            double matchScore = node.basicScore + linkScore;

            if (matchScore > maxScore) {
                maxScore = matchScore;
                resultIdx = i;
            } else if (matchScore == maxScore) {
                resultIdx = Math.min(resultIdx, i);
            }
        }

        return resultIdx;
    }
}
