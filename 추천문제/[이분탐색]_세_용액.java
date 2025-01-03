import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

class Main{

    private static int[] arr;
    private static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N];
        String[] sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }

        Arrays.sort(arr);

        for (int i = 0; i < N-2; i++) {
            bs(i);
        }
        Arrays.sort(resultArr);
        System.out.println(resultArr[0] + " " + resultArr[1] + " " + resultArr[2]);

    }

    static int[] resultArr = new int[3];
    static long answer = Long.MAX_VALUE;

    private static void bs(int cur) {
        int left = cur+1;
        int right = arr.length-1;

        while (left <right){
            long val = arr[left] + arr[right] + arr[cur];
            long absVal = Math.abs(val);
            if(absVal < answer){
                answer = absVal;
                resultArr[0] = arr[left];
                resultArr[1] = arr[cur];
                resultArr[2] = arr[right];
            }

            if(val > 0){
                right--;
            }else{
                left++;
            }
        }
    }
}

class Solve{
    public void solve() throws IOException{

    }
}

