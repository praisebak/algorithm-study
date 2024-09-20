import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String[] sArr = s.split(" ");
        int N =Integer.parseInt(sArr[0]);
        int M =Integer.parseInt(sArr[1]);
        int[] arr = new int[N+1];
        sArr = bufferedReader.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i-1] + Integer.parseInt(sArr[i-1]);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(sArr[0]);
            int end = Integer.parseInt(sArr[1]);
            stringBuilder.append(arr[end] - arr[start-1] + "\n");
        }
        System.out.println(stringBuilder);
    }
}
