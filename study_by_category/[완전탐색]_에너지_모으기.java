//https://www.acmicpc.net/problem/16198



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{


    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{

    private int N;
    private int max = 0;


    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N];
        String[] sArr = bufferedReader.readLine().split(" ");
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(sArr[i]));
        }

        dfs(0,0,list);
        System.out.println(max);
    }

    private void dfs(int depth, int sum, List<Integer> list) {
        max = Math.max(sum,max);
        if(list.size() == 2) return;

        for(int i=1;i<= list.size()-2;i++){
            int nextSum = sum + (list.get(i-1) *list.get(i+1));

            List<Integer> nextList = new ArrayList<>();
            nextList.addAll(list);
            nextList.remove(i);
            dfs(depth+1,nextSum,nextList);
        }

    }
}
