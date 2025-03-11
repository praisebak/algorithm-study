
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.print.DocFlavor.STRING;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    int[] zero = new int[41];
    int[] one = new int[41];

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        zero[0] = 1;
        one[1] = 1;

        fibo(40);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            stringBuilder.append(zero[num] +" " + one[num] + "\n");
        }
        System.out.println(stringBuilder.toString());
    }

    private void fibo(int num) {
        if(num <= 1){
            return;
        }

        if(zero[num] != 0){
            return;
        }
        fibo(num-1);
        fibo(num-2);

        zero[num] = zero[num-1] + zero[num-2];
        one[num] = one[num-1] + one[num-2];
    }
}
