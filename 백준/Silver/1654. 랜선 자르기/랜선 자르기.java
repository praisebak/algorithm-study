import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int OBJ = Integer.parseInt(sArr[1]);

        long left = 1;
        long right = 0;
        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
            right = Math.max(arr[i],right);
        }

        Arrays.sort(arr);

        long answer = 0;
        while (left <= right){
            long mid = (left + right) / 2;
            long count = 0;
            for (int i = 0; i < N; i++) {
                if(arr[i] >= mid){
                    count += arr[i] / mid;
                }
            }
            if(count >= OBJ){
                answer = Math.max(answer,mid);
                left = mid +1;
            }else{
                right = mid -1;
            }
        }
        System.out.println(answer);
    }
}
