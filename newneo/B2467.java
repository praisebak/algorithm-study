package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class B2467 {

    long[] arr;
    int N;

    public void secondSolve() throws IOException{
        String result = "";
        long resultMin = Integer.MAX_VALUE;
        int l = 0;
        int r = N-1;

        while (l < r){
            long sum = arr[l] + arr[r];

            if(Math.abs(sum) < resultMin){
                resultMin = Math.abs(sum);
                result = arr[l] + " " + arr[r];
            }

            //더 큰값으로
            if(sum < 0){
                l++;
            }else{
                r--;
            }
        }
        System.out.println(result);

    }

    public void firstSolve() throws IOException{
        String result = "";
        long resultMin = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            long cur = arr[i];

            int left = i+1;
            int right = N-1;

            while (left <= right){
                int mid = (left + right) / 2;
                long next = arr[mid];

                long sum = next + cur;


                if(resultMin > Math.abs(sum)){
                    resultMin = Math.abs(sum);
                    result = cur + " " +  next;
                }

                //값이 -면 오른쯕으로
                if(sum < 0){
                    left = mid +1;
                }else{
                    right = mid -1;
                }
            }
        }
        System.out.println(result);
    }

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        arr = new long[N];
        String[] sArr = bufferedReader.readLine().split(" ");
        int count = 0;
        for(String s : sArr){
            arr[count++] = Integer.parseInt(s);
        }

//        firstSolve();
        secondSolve();
    }
}

