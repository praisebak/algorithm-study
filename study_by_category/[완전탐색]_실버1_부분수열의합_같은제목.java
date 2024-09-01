//https://www.acmicpc.net/problem/14225
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int N;
    int[] arr;


    boolean[] visit = new boolean[100000 * 20 + 1];
    boolean[] check;
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        this.N = Integer.parseInt(s);
        arr = new int[N];
        check = new boolean[N];
        s = bufferedReader.readLine();

        String[] sArr = s.split(" ");
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(sArr[i]);
        }

        //sum depth
        dfs(0,0);

        for(int i=1;i<visit.length;i++){
            if(!visit[i]) {
                System.out.println(i);
                return;
            }
        }
    }

    private void dfs(int sum, int depth) {
        if(depth == N) {
            return;
        }
        for(int i=depth;i<N;i++){
            if(check[i]) continue;
            check[i] = true;
            visit[sum + arr[i]] = true;
            dfs(sum + arr[i],i);
            check[i] = false;
        }
    }

}
