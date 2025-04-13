import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        String[] sArr = bufferedReader.readLine().split(" ");
        int[] arr = new int[N+1];

        for (int i = 0; i < sArr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }

        //i번째 시점에서 제일 길이가긴 인덱스에 대한 dp
        int[] dp = new int[N];

        //0 1 1 3 3 4
        //dp[N-1]에서 추적하고 reverse 때리기
        Arrays.fill(dp,1);

        int[] trace = new int[N+1];
        trace[0] = 0;

        int maxIdx = 0;
        int maxLen = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[j] < arr[i] && dp[j] >= dp[i]){
                    dp[i] = dp[j]+1;
                    trace[i] = j;
                    maxLen = Math.max(dp[i],maxLen);
                    if(dp[i] > dp[maxIdx]){
                        maxIdx = i;
                    }
                }
            }
        }
        System.out.println(maxLen);

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < maxLen; i++) {
            list.add(arr[maxIdx]);
            maxIdx = trace[maxIdx];
        }
        StringBuilder stringBuilder = new StringBuilder();
        Collections.reverse(list);
        for(Integer num : list){
            stringBuilder.append(num).append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

}

