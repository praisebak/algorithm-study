import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    int[][] map;
    int N;
    private int answer = Integer.MAX_VALUE;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visit = new boolean[N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }


        comb(-1,0,0);
        System.out.println(answer);
    }

    boolean[] visit;
    private void comb(int prev,int depth,int sum) {
        if(depth == N / 2){
            cal();
        }
        for (int i = prev+1; i < N ; i++) {
            visit[i] = true;
            comb(i,depth+1,sum);
            visit[i] = false;
        }
    }

    private void cal() {
        int sum = 0;
        for (int cur = 0; cur < N; cur++) {
            if(visit[cur]){
                for (int next = cur+1; next < N; next++) {
                    if(visit[next]){
                        sum += map[cur][next];
                        sum += map[next][cur];
                    }

                }
            }
        }

        int nextSum = 0;

        for (int cur = 0; cur < N; cur++) {
            if(!visit[cur]){
                for (int next = cur+1; next < N; next++) {
                    if(!visit[next]){
                        nextSum += map[cur][next];
                        nextSum += map[next][cur];    
                    }
                }
            }
        }


        answer =  Math.min(Math.abs(nextSum - sum),answer);
    }
}
