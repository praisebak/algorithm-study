import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solve{
    class Answer{
        int idx;
        int count;

        public Answer(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }

    List<Answer> answerList = new ArrayList<>();

    List<List<Integer>> nodeList = new ArrayList<>();
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr= bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        for (int i = 0; i <= N; i++) {
            nodeList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int end = Integer.parseInt(sArr[0]);
            int start = Integer.parseInt(sArr[1]);
            nodeList.get(start).add(end);
        }
        Arrays.fill(dp,-1);

        for (int i = 1; i <= N; i++) {
            boolean[] visit = new boolean[N+1];
            int count = 0;
            if(dp[i] != -1){
                count = dp[i];
            }else{
                count = dfs(i,visit);
            }
                
            answerList.add(new Answer(i,count));
        }
        for (int i = 0; i <= N; i++) {
            System.out.print(dp[i] + " ");
            
        }
        System.out.println();

        Collections.sort(answerList,(o1,o2) -> o1.count == o2.count ? o1.idx - o2.idx : o2.count - o1.count);
        int prevAnswer = answerList.get(0).count;
        StringBuilder stringBuilder = new StringBuilder();
        for(Answer answer : answerList){
            if(prevAnswer == answer.count){
                stringBuilder.append(answer.idx + " ");
            }else{
                break;
            }
        }
        System.out.println(stringBuilder);
    }

    int[] dp = new int[10001];
    private int dfs(int cur,boolean[] visit) {
        dp[cur] = 1;
        for(int next : nodeList.get(cur)){
            if(dp[next] != -1){
                dp[cur] += dp[next];
            }else{
                dp[cur] += dfs(next,visit);
            }
        }
        return dp[cur];
    }
}

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
