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
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            int[] arr = new int[N];
            String[] sArr= bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(sArr[j]);
            }
            Arrays.sort(arr);

            int M = Integer.parseInt(bufferedReader.readLine());
            sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(sArr[j]);
                if(binSearch(arr,num)) System.out.println("큰돌이는 기억해!");
                else System.out.println("큰돌이는 까먹었어!");
            }
        }
    }

    private boolean binSearch(int[] arr, int num) {
        int left = 0;
        int right = arr.length-1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] == num) return true;
            if(arr[mid] > num){
                right = mid-1;
            }else if(arr[mid] < num){
                left = mid+1;
            }


        }
        return false;
    }
}
