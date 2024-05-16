package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class B2631 {

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            int cur = Integer.parseInt(br.readLine());
            arr[i] = cur;
        }

        int[] dp = new int[N];
        Arrays.fill(dp,1);

        int max = 0;

        for(int i=1;i<N;i++){
            for(int j=0;j<i;j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    if(dp[i] > max){
                        max = dp[i];
                    }
                }
            }
        }

        System.out.println(N - max);



    }
}


class Main{

    public static void main(String[] args) throws IOException{
        B2631 b2631 = new B2631();
        b2631.solve();
    }
}
