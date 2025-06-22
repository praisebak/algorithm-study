import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");

        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        int left = 1;
        int right = 0;

        int[] candy = new int[M];
        sArr = bufferedReader.readLine().split(" ");

        for (int i = 0; i < M; i++) {
            candy[i] = Integer.parseInt(sArr[i]);
        }

        Arrays.sort(candy);
        right = candy[M-1];

        int answer = 0;

        while (left <= right){
            int mid = (left + right) / 2;

            int count = 0;

            for (int i = M-1; i >= 0; i--) {
                if(candy[i] < mid) break;
                count += candy[i] / mid;
            }

            if(count >= N) {
                answer = Math.max(answer,mid);
                left = mid + 1;
            }else{
                right = mid-1;
            }
        }

        System.out.println(answer);
    }
}
