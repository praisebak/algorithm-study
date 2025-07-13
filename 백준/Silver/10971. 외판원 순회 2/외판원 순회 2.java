import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import javax.print.attribute.IntegerSyntax;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{

    private int N;
    private boolean[] visit;
    private int[][] map;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visit = new boolean[N+1];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            travel(i,0,0,i);
        }
        System.out.println(answer);
    }



    private void travel(int start, int val,int len,int cur) {
        if(len == N){
            if(cur != start){
                return;
            }
            answer = Math.min(val,answer);
            return;
        }

        for(int i=0;i<N;i++){
            if(visit[i]) continue;
            if(map[cur][i] == 0) continue;
            visit[i] = true;
            travel(start,val + map[cur][i],len+1,i);
            visit[i] = false;
        }
    }
    int answer = Integer.MAX_VALUE;
}
