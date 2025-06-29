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
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] sArr = br.readLine().split(" ");
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(sArr[i]);
        }

        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;

        int leftAnswer = 0;
        int rightAnswer = 0;

        for(int i=0;i<N;i++){
            int right = N-1;
            int left = i+1;

            while(left <= right){
                int mid = (int) (((long)left + right) / 2);
                int absVal = Math.abs(arr[mid] + arr[i]);

                if(arr[mid] + arr[i] > 0){
                    right = mid-1;
                }else{
                    left = mid +1;
                }

                if(absVal < answer){
                    leftAnswer = arr[i];
                    rightAnswer = arr[mid];
                    answer = absVal;
                }
            }

        }
        System.out.println(leftAnswer + " " + rightAnswer);
    }
}
