package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class B10159_저울 {

    public static void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());
        int[][] map = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int bigNum = Integer.parseInt(stringTokenizer.nextToken());
            int smallNum = Integer.parseInt(stringTokenizer.nextToken());
            map[bigNum][smallNum] = 1;
            map[smallNum][bigNum] = -1;
        }

        for (int i = 1; i <= N; i++) {
            map[i][i] = 1;
        }

        for(int k=1; k<N+1; k++) {
            for(int i=1; i<N+1; i++) {
                for(int j=1; j<N+1; j++) {
                    if(map[i][k] == 1 && map[k][j] ==1) {
                        map[i][j] =1;
                    }

                    if(map[i][k] == -1 && map[k][j] == -1) {
                        map[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <=N ; j++) {
                if(map[i][j] != 0) count++;
            }
            stringBuilder.append(N-count + "\n");
        }

        System.out.print(stringBuilder.toString());
    }

    public static void main(String[] args) throws IOException{
        solve();
    }
}
