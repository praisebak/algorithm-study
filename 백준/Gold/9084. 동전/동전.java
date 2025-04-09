import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
  
class Main{  
  
    private static int[] arr;  
    private static int[] dp;  
  
    public static void main(String[] args) throws IOException {  
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));  
        int T = Integer.parseInt(bufferedReader.readLine());  
        for (int t = 0; t < T; t++) {  
            int N = Integer.parseInt(bufferedReader.readLine());  
            String[] sArr = bufferedReader.readLine().split(" ");  
            arr = new int[N];  
  
            for (int i = 0; i < sArr.length; i++) {  
                int num = Integer.parseInt(sArr[i]);  
                arr[i] = num;  
            }  
            int K = Integer.parseInt(bufferedReader.readLine());  
            dp = new int[K+1];  
            dp[0] = 1;  
            dp(K);  
            System.out.println(dp[K]);  
        }  
  
  
    }  
  
    private static void dp(int k) {  
        for (int curCoin : arr) {  
            for (int j = 1; j <= k; j++) {  
                if (j - curCoin < 0) {  
                    continue;  
                }  
                dp[j] += dp[j - curCoin];  
            }  
        }  
    }  
}