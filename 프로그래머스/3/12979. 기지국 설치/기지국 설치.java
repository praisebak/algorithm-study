class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int rangeS = 1;
        for (int i=0; i<stations.length; i++) {
            int rangeE = stations[i] - w;
            for (int j=rangeS; j<rangeE; ) { // i번 기지국의 앞쪽 중 아직 배치되지 않은 구역을 탐색
                answer++;
                j+=(2*w + 1);
            }
            rangeS = stations[i] + w + 1;
        }
        
        // 마지막 기지국을 기준으로 아직 배치되지 않은 뒤쪽을 탐색
        for (int j=rangeS; j<=n; ) {
            answer++;
            j+=(2*w + 1);
        }
        
        return answer;
    }
}