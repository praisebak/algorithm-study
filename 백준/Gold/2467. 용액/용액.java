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
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N];
        String[] sArr = bufferedReader.readLine().split(" ");

        for (int i = 0; i < sArr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }

        int left = 0;
        int right = N-1;
        int answerLeft = 0;
        int answerRight = 0;
        int answerValue = Integer.MAX_VALUE;
        while (left < right){
            int sum = arr[left] + arr[right];

            if(Math.abs(sum) < answerValue){
                answerValue = Math.abs(sum);
                answerLeft = arr[left];
                answerRight = arr[right];
            }
            //0보다 작으면 오른쪽으로 가자
            if(sum < 0){
                left++;
            }else{
                right--;
            }
        }

        System.out.println(answerLeft + " " + answerRight);


    }
}
