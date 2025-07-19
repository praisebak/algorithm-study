import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{

    private String[] sArr;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        sArr = bufferedReader.readLine().split(" ");
        Arrays.sort(sArr);

        bfs(-1, N, M, 0, "",0,0);
        System.out.print(stringBuilder.toString());
    }

    private StringBuilder stringBuilder = new StringBuilder();

    static Set<String> bSet = new HashSet<>();
    static {
        bSet.add("a");
        bSet.add("e");
        bSet.add("i");
        bSet.add("o");
        bSet.add("u");
    }

    private void bfs(final int start, final int n, final int m, final int curI, final String curS,int a,int b) {
        if(curI == n && a >= 2 && b >= 1){
            stringBuilder.append(curS).append("\n");
            return;
        }

        for(int s = start+1;s < m;s++){
            boolean isB = bSet.contains(sArr[s]);
            if(isB){
                bfs(s,n,m,curI+1,curS + sArr[s],a,b+1);
            }else{
                bfs(s,n,m,curI+1,curS + sArr[s],a+1,b);
            }
        }
    }
}
