import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int answer = Integer.MAX_VALUE;
    private int N;
    private int M;
    private int L;
    private int[] arr;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        L = Integer.parseInt(sArr[2]);

        arr = new int[N +2];

        if(N != 0){
            sArr = bufferedReader.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                arr [i] = Integer.parseInt(sArr[i]);
            }
        }

        arr[N] = 0;
        arr [N +1] = L;
        Arrays.sort(arr);

        int left = 1;
        int right = L;

        while (left <= right){
            int mid = (left + right) / 2;

            if(calculate(mid)){
                answer = mid;
                right = mid-1;
            }else{
                left = mid +1;
            }
        }
        System.out.println(answer);
    }

    private boolean calculate(int minDist) {
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            int curDist = arr[i] - arr[i-1];
            count += (curDist - 1) / minDist;
        }
        return count <= M;
    }
}



