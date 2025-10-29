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
        int[] weight = new int[N];
        int[] degree = new int[N];

        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int idx= 0;
            for(String s : sArr){
                if(idx == 0){
                    int val = Integer.parseInt(s);
                    weight[i] = val;
                }else{
                    int next = Integer.parseInt(s);

                    if(idx == 1) {
                        idx++;
                        continue;
                    }

                    degree[i]++;
                    list.get(next-1).add(i);
                }
                idx++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] dp = new int[N];

        int answer = weight[0];

        for(int i=0;i<N;i++){
            dp[i] = weight[i];

            if(degree[i] == 0){
                answer = Math.max(dp[i],answer);
                queue.add(i);
                degree[i]--;
            }
        }

        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            for(int next : list.get(poll)){
                dp[next] =  Math.max(dp[next],dp[poll] + weight[next]);
                answer=  Math.max(dp[next],answer);
                degree[next]--;
                if(degree[next] == 0){
                    queue.add(next);
                }
            }
        }

        System.out.println(answer);
    }
}
