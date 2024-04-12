package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class B2467_second {
    int N;
    int[] arr;

    public void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] inputSplit = br.readLine().split(" ");
        for(int i=0;i<N;i++){

            int num = Integer.parseInt(inputSplit[i]);
            arr[i] = num;
        }

        binarySearch();
    }

    private void binarySearch() {
        int answer = Integer.MAX_VALUE;
        String str = "";
        for(int i=0;i<N;i++){
            int end = N-1;
            int start = i+1;

            while(end >= start){
                int mid = (start + end)/2;
                int val = arr[mid] + arr[i];

                if(val > 0){
                    end = mid -1;
                }else{
                    start = mid +1;
                }
                if(Math.abs(answer) > Math.abs(val)){
                    answer = val;
                    str = arr[i] + " " + arr[mid];
                }
            }
        }
        System.out.println(str);
    }




}



