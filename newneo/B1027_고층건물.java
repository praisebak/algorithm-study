package newneo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main_B1027{

    public static void main(String[] args) throws IOException{
        B1027_고층건물 b1027 = new B1027_고층건물();
        b1027.solve();
    }
}

class B1027_고층건물 {
    int N;
    int[] arr;

    public void solve() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N];

        String[] split = bufferedReader.readLine().split(" ");
        for(int i = 0;i<N;i++){
            arr[i] = Integer.parseInt(split[i]);
        }

        int result = 0;

        for(int i=0;i<N;i++){
            boolean isStart = true;
            double leftHeight = Double.MAX_VALUE;
            int leftSum = 0;
            double rightHeight = Double.MIN_VALUE;
            int rightSum = 0;

//            System.out.println("curI = " + i);
//            System.out.println();
            for(int j=i-1;j>=0;j--){
//                double newLineLength = getLineLength(j,i,arr[j],arr[i]);
                double newLineLength = getLineLength(i,j,arr[i],arr[j]);
//                System.out.println(newLineLength);
                if(leftHeight > newLineLength || isStart){
                    isStart =false;
                    leftSum++;
                    leftHeight = newLineLength;
                }
            }
            isStart = true;

//            System.out.println("leftSum = " + leftSum);
            for(int j=i+1;j<N;j++){
                double newLineLength = getLineLength(i,j,arr[i],arr[j]);
//                double newLineLength = getLineLength(j,i,arr[j],arr[i]);
//                System.out.println(newLineLength);
                if(rightHeight < newLineLength || isStart){
                    isStart =false;
                    rightSum++;
                    rightHeight = newLineLength;
                }
            }
//            System.out.println("rightSum = " + rightSum);
//            System.out.println("result = " + leftSum + "," + rightSum + " " + (leftSum + rightSum) );
//            System.out.println();
            result = Math.max(rightSum + leftSum,result);
        }

        System.out.println(result);


    }


    private double getLineLength(double colA, double colB, double rowA, double rowB) {

        return (double) ((rowB - rowA) / (colB - colA));

    }
}
