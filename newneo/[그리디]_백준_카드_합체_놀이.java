import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

class Main{


    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer  = new StringTokenizer(bufferedReader.readLine()," ");
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        long[] arr = new long[N];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i] = Long.parseLong(stringTokenizer.nextToken());
        }

        for(int i=0;i<M;i++){
            Arrays.sort(arr);
            long sum = arr[0] + arr[1];
            arr[0] = sum;
            arr[1] = sum;
        }

        long sum = 0;
        for(int i=0;i<N;i++){
            sum += arr[i];
        }
        System.out.println(sum);

    }


}
