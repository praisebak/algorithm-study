import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.KeyStroke;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    class Item{
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int K = Integer.parseInt(sArr[1]);
        Item[] arr = new Item[N];

        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int left = Integer.parseInt(sArr[0]);
            int right = Integer.parseInt(sArr[1]);
            arr[i] = new Item(left,right);
        }

        int[] dp = new int[K+1];
        int answer = 0;
        for(int i=0;i<N;i++){
            Item cur = arr[i];
            for (int j = K; j >=0; j--) {
                if(j - cur.weight >= 0){
                    dp[j] = Math.max(dp[j-cur.weight] + cur.value,dp[j]);
                    answer = Math.max(answer,dp[j]);
                }
            }
        }

        System.out.println(answer);
    }
}
