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
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] sArr =  bufferedReader.readLine().split(" ");
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }

        Arrays.sort(arr);

        int sum = 0;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            sum = sum + arr[i];
            answer += sum;
        }
        System.out.println(answer);
    }
}
