import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}


class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");

        int N= Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        int[][] map = new int[N+1][N+1];
        int[][] weights = new int[N+1][N+1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(map[i],987654321);
            map[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int left = Integer.parseInt(sArr[0]);
            int right = Integer.parseInt(sArr[1]);
            int val = Integer.parseInt(sArr[2]);
            map[left][right] = val;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(map[i][k] + map[k][j] < map[i][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int num = Integer.parseInt(bufferedReader.readLine());
        sArr = bufferedReader.readLine().split(" ");

        int[] obj = new int[N+1];

        for (int j = 1; j <= N; j++) {
            int maxRoundTrip = 0; // j번 도시에서의 최대 왕복 시간을 저장할 변수

            // 2. 모든 친구들에 대해 j까지의 왕복 시간을 계산
            for (int i = 0; i < num; i++) {
                int friendLocation = Integer.parseInt(sArr[i]);
                int roundTrip = map[friendLocation][j] + map[j][friendLocation];

                // 3. 현재까지의 최대 왕복 시간보다 더 오래 걸리는 친구가 있다면 갱신
                if (roundTrip > maxRoundTrip) {
                    maxRoundTrip = roundTrip;
                }
            }
            // 4. j번 도시의 점수(최대 왕복 시간)를 obj 배열에 저장
            obj[j] = maxRoundTrip;
        }


        List <Integer> results = new ArrayList<>();

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            if(answer == obj[i]){
                results.add(i);
            }else if(answer > obj[i]){
                results = new ArrayList<>();
                results.add(i);
                answer = obj[i];
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(results);

        for(Integer curNum : results){
            stringBuilder.append(curNum).append(" ");
        }

        System.out.println(stringBuilder.toString());
    }
}
