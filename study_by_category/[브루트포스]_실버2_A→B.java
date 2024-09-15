import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    int answer = Integer.MAX_VALUE;
    int M;
    public void solve() throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);

        if(N == M){
            System.out.println(1);
            return;
        }
        dfs(N,0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private void dfs(long n,int depth) {
        if(n == M){
            answer = Math.min(depth+1,answer);
            return;
        }
        if(n > M) return;
        dfs(n * 2,depth+1);
        dfs(n * 10 + 1,depth+1);
    }

}
