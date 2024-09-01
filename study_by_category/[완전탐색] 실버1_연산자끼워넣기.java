//https://www.acmicpc.net/problem/14888
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}



class Solve{
    int[] operandArr = new int[4];
    int[] arr;
    private int N;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.N = Integer.parseInt(bufferedReader.readLine());

        String[] sArr = bufferedReader.readLine().split(" ");
        arr = new int[sArr.length];
        int idx = 0;
        for (String s : sArr){
            int val = Integer.parseInt(s);
            arr[idx++] = val;
        }

        String[] operandStrArr = bufferedReader.readLine().split(" ");
        for(int i=0;i<4;i++){
            operandArr[i] = Integer.parseInt(operandStrArr[i]);
        }


        //n-1개만큼
        //depth,sum
        perm(0,arr[0],operandArr);

        System.out.println(max);
        System.out.println(min);
    }

    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    private void perm(int depth, int sum, int[] operandArr) {
        if(N-1 == depth){
            max = Math.max(max,sum);
            min = Math.min(min,sum);
            return;
        }

        for(int i= 0;i<4;i++)
        {
            if(operandArr[i] != 0){
                operandArr[i]--;
                int val = sum;
                if(i == 0){
                    val += arr[depth+1];
                }else if(i == 1){
                    val -= arr[depth+1];
                }else if(i == 2){
                    val *= arr[depth+1];
                }else{
                    val /= arr[depth+1];
                }

                perm(depth+1,val,operandArr);
                operandArr[i]++;
            }
        }

    }
}
