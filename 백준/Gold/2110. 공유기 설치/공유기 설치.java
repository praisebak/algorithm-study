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

    private int M;

    private long answer = 0;
    private long[] arr;
    private int N;

    public void solve() throws IOException{
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);

        arr = new long[N];
        for(int i = 0; i< N; i++){
            int num = Integer.parseInt(bufferedReader.readLine());
            arr[i] = num;
        }

        int left = 1;
        long right = 1000000000;

        Arrays.sort(arr);
        binSearch(left,right);
        System.out.println(answer);
    }

    private void binSearch(long left, long right) {

        while(left <= right){
            long mid = (left + right)/ 2;
//            System.out.println(mid +"일떄");
            if(isValid(mid,left,right)){
                left = mid+1;
                answer = Math.max(answer,mid);
            }else{
                right =mid-1;
            }
        }
    }

    private boolean isValid(long dist,long left,long right) {
        int count = 1;
        int prev = (int) arr[0];
        for(int i = 1; i< N; i++){
            if(arr[i] - prev >= dist){
//                System.out.print(arr[i] + " ");
                prev = (int) arr[i];
                count++;
            }
        }
//        System.out.println("\n" + count);
        return count >= M;
    }
}
