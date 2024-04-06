package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        new B14719().solve();
    }
}


class B14719 {
    private int H;
    private int W;

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

        int sum = 0;

        for(int i=1;i< W-1;i++){
            int left = 0;
            int right = 0;

            for(int j=0;j<i;j++){
                left = Math.max(left,arr[j]);
            }

            for(int j=i+1;j<W;j++){
                right = Math.max(right,arr[j]);
            }

            if(left > arr[i] && right > arr[i]){
                sum += Math.min(left,right) - arr[i];
            }
        }
        System.out.println(sum);
    }

}

