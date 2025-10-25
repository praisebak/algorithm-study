import java.util.*;

class Solution {
    
    // Job 클래스는 요청 시간(start)과 소요 시간(time)만 필요합니다.
    class Job {
        int start;
        int time;
        
        public Job(int start, int time) {
            this.start = start;
            this.time = time;
        }
    }

    public int solution(int[][] jobs) {
        
        // 1. (대기 리스트) 모든 작업을 '요청 시간(start)' 기준으로 정렬합니다.
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        // 2. (준비 큐) '처리 시간(time)'이 짧은 순으로 정렬되는 우선순위 큐
        //    (현재 실행 가능한 작업 목록)
        PriorityQueue<Job> readyQueue = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);

        int totalResponseTime = 0; // 총 (요청~종료) 시간의 합
        int curTime = 0;           // 현재 시각
        int jobIndex = 0;          // 'jobs' 배열(대기 리스트)에서 다음으로 넣을 인덱스
        int processedCount = 0;    // 처리 완료된 작업 수

        // 3. 모든 작업을 처리할 때까지 반복
        while (processedCount < jobs.length) {
            
            // 4-1. (채우기) '현재 시각(curTime)'까지 요청된 모든 작업을 
            //      '대기 리스트(jobs)'에서 '준비 큐(readyQueue)'로 옮깁니다.
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= curTime) {
                readyQueue.add(new Job(jobs[jobIndex][0], jobs[jobIndex][1]));
                jobIndex++;
            }

            // 4-2. (실행) '준비 큐'에 실행할 작업이 있는지 확인
            if (!readyQueue.isEmpty()) {
                // [Case 1: 실행할 작업이 있음 (디스크가 바쁨)]
                // -> '처리 시간이 가장 짧은' 작업을 꺼냅니다. (SJF)
                Job jobToRun = readyQueue.poll();
                
                // 현재 시각(curTime)에 처리 시간을 더해 종료 시각을 업데이트
                curTime += jobToRun.time;
                
                // 총 응답 시간 계산: (작업 종료 시각 - 작업 요청 시각)
                totalResponseTime += (curTime - jobToRun.start);
                
                processedCount++;
            } else {
                // [Case 2: 실행할 작업이 없음 (디스크가 쉼)]
                // -> '대기 리스트'에 남은 작업이 있다면, 
                //    '현재 시각'을 '다음 작업의 요청 시각'으로 점프시킵니다.
                // (이 시점에는 jobIndex < jobs.length가 항상 보장됩니다)
                curTime = jobs[jobIndex][0];
            }
        }

        // 5. 평균 시간 반환
        return totalResponseTime / jobs.length;
    }
}