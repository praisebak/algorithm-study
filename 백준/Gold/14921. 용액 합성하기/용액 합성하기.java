import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException  {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N];
        String[] sArr = bufferedReader.readLine().split(" ");

        for (int i = 0; i < sArr.length;i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }

        int left = 0;
        int right =  arr.length-1;

        int answer = Integer.MAX_VALUE;
        int originVal = 0;
        while (left < right){
            int mid = arr[left] + arr[right];
            if(mid > 0){
                right--;
            }else{
                left++;
            }

            answer = Math.min(Math.abs(mid), answer);
            if(answer == Math.abs(mid)){
                originVal = mid;
            }
        }

        System.out.println(originVal);
    }
}
