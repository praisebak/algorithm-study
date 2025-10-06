import java.util.*;

class Solution {
    int N;
    int M;
    int A;
    int B;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = 98765432;
        int[][] map = new int[n+1][n+1];

        // 1. 초기화
        for(int i=1; i<=n; i++){
            Arrays.fill(map[i], INF);
            map[i][i] = 0; // 자기 자신은 0
        }

        // 2. 간선 정보 세팅
        for(int[] fare : fares){
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            map[u][v] = cost;
            map[v][u] = cost; // 양방향
        }

        // 3. 플로이드–워셜
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(map[i][k] == INF || map[k][j] == INF) continue; // overflow 방지
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        // 4. 최소 합승 요금 계산
        int answer = INF;
        for(int k=1; k<=n; k++){ // k는 분기점
            int cost = map[s][k] + map[k][a] + map[k][b];
            answer = Math.min(answer, cost);
        }

        return answer;
    }
}
