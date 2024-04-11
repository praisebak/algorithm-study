package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class B14719 {


    int H;
    int W;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        H = Integer.parseInt(tmp[0]);
        W = Integer.parseInt(tmp[1]);
        String[] inputArr = br.readLine().split(" ");

        int[] arr = new int[W];

        int index = 0;
        for(String s : inputArr){
            int v = Integer.parseInt(s);
            arr[index++] = v;
        }

        int result = 0;
        for(int i=1;i<W-1;i++){
            int curHeight = arr[i];

            int left = 0;
            for(int j=0;j<i;j++){
                if(arr[j] > left){
                    left = arr[j];
                }
            }

            int right = 0;
            for(int j= i+1;j<W;j++){
                if(arr[j] > right){
                    right = arr[j];
                }
            }

            if(right != 0 && left != 0 && left > arr[i] && right > arr[i]){
                int minHegiht = Math.min(right,left);
                result += minHegiht - curHeight;
//                System.out.println(left + "," + right + "=" +  result);
            }
        }




        System.out.println(result);
    }

}

