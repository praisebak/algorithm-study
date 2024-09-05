//https://www.acmicpc.net/problem/11501
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

        String s = "";
        for(int i=0;i<N;i++){
            int K = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            long[] arr = new long[K];
            for(int j=0;j<K;j++){
                arr[j] = Long.parseLong(stringTokenizer.nextToken());
            }

            long sum = 0;
            long max = arr[K-1];
            for(int j=K-2;j>=0;j--){
                long cur = arr[j];
                if(cur >= max){
                    max = cur;
                }else{
                    sum += (max - cur);
                }
            }

            System.out.println(sum);
        }

    }
}
