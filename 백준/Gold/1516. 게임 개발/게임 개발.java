import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] degree = new int[N+1];

        int[] weight = new int[N+1];

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N ; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int left = Integer.parseInt(sArr[0]);
            weight[i+1] = left;
            for (int j = 1; j < sArr.length-1; j++) {
                int right = Integer.parseInt(sArr[j]);
                degree[i+1]++;
                list.get(right).add(i+1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            if(degree[i] == 0){
                queue.add(i);
                dp[i] = weight[i];
            }
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int next : list.get(cur)){
                degree[next]--;
                dp[next] = Math.max(dp[next],dp[cur] + weight[next]);

                if(degree[next] == 0){
                    queue.add(next);
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=1;i<=N;i++){
            stringBuilder.append(dp[i]).append("\n");
        }

        System.out.println(stringBuilder.toString());
    }
}
