import java.util.*;

class Solution {
    public int solution(String[] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }

        List<long[]> logTimes = new ArrayList<>();
        List<Long> eventPoints = new ArrayList<>();

        // 1. 모든 로그를 밀리초 단위로 변환하고,
        //    모든 시작 시간과 끝 시간을 eventPoints 리스트에 저장합니다.
        for (String line : lines) {
            String[] parts = line.split(" ");
            String[] timeParts = parts[1].split(":");

            long hour = Long.parseLong(timeParts[0]) * 3600 * 1000;
            long minute = Long.parseLong(timeParts[1]) * 60 * 1000;
            long second = (long) (Double.parseDouble(timeParts[2]) * 1000);
            long endTime = hour + minute + second;

            String durationStr = parts[2].replace("s", "");
            long duration = (long) (Double.parseDouble(durationStr) * 1000);
            
            long startTime = endTime - duration + 1;

            logTimes.add(new long[]{startTime, endTime});
            eventPoints.add(endTime);
        }

        int maxThroughput = 0;

        // 2. ⭐ 모든 이벤트 시점을 기준으로 1초 윈도우를 시작시켜 최대 처리량을 계산합니다.
        for (long point : eventPoints) {
            long windowStartTime = point;
            long windowEndTime = point + 999;

            int currentThroughput = 0;
            for (long[] log : logTimes) {
                // 로그 구간이 1초 윈도우와 겹치는지 확인
                if (log[0] <= windowEndTime && log[1] >= windowStartTime) {
                    currentThroughput++;
                }
            }
            maxThroughput = Math.max(maxThroughput, currentThroughput);
        }

        return maxThroughput;
    }
}