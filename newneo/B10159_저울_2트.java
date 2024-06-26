package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10159_저울_2트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer;

        int[][] map = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            map[i][i] = 1;
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int biggerNum = Integer.parseInt(stringTokenizer.nextToken());
            int smallerNum = Integer.parseInt(stringTokenizer.nextToken());
            map[biggerNum][smallerNum] = 1;
            map[smallerNum][biggerNum] = -1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(map[i][k] == 1 && map[k][j] == 1){
                        map[i][j] = 1;
                    }

                    if(map[i][k] == -1 && map[k][j] == -1){
                        map[i][j] = -1;
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if(map[i][j] == 1){
                    count++;
                }
                if(map[i][j] == -1){
                    count++;
                }
            }

            stringBuilder.append(N-count + "\n");
        }
        System.out.println(stringBuilder.toString());

    }
}