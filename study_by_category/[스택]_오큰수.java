import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] sArr= bufferedReader.readLine().split(" ");
        Stack<Integer> rightStack = new Stack<>();
        int[] result = new int[N];
        for (int i = N-1; i>= 0; i--) {
            int num = Integer.parseInt(sArr[i]);

            while (!rightStack.isEmpty() && rightStack.peek() <= num){
                rightStack.pop();
            }
            if(rightStack.isEmpty()) result[i] = -1;
            else result[i] = rightStack.peek();
            rightStack.push(num);
        }

        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < N; i++) {
            stringBuilder1.append(result[i] + " ");
        }
        System.out.println(stringBuilder1);
    }
}
