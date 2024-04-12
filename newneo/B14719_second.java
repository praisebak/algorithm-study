package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class B14719_second {
    int H;
    int W;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public void solve() throws IOException {
        int sum = 0;
        String[] inputSplit = br.readLine().split(" ");
        H = Integer.parseInt(inputSplit[0]);
        W = Integer.parseInt(inputSplit[1]);
        inputSplit = br.readLine().split(" ");
        int[] arr = new int[W];
        for(int i=0;i<W;i++){
            arr[i] = Integer.parseInt(inputSplit[i]);
        }

        for(int i=1;i<W;i++){

            int leftHeight = 0;
            for(int j=0;j<i;j++){
                int height = arr[j];
                if(leftHeight < height){
                    leftHeight = height;
                }
            }

            int rightHeight = 0;
            for(int j= i+1;j<W;j++){
                int height = arr[j];
                if(rightHeight < height){
                    rightHeight = height;
                }
            }

            if(leftHeight == 0 || rightHeight == 0){
                continue;
            }else{
                if(leftHeight > arr[i] && rightHeight > arr[i]){
                    int minHeight = Math.min(leftHeight,rightHeight);
                    sum += minHeight - arr[i];
                }
            }
        }
        System.out.println(sum);

    }

}


