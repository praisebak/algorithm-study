import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class B6189 {
    public static void main(String[] args) throws IOException {
        SolveB6189 solveB6189 = new SolveB6189();
        solveB6189.solve();
    }
}


class SolveB6189{
    Stack<Integer> stack = new Stack<>();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    void solve() throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());

        long count = 0;
        for (int i = 0; i < N; i++) {
            int curV = Integer.parseInt(bufferedReader.readLine());

            if(stack.isEmpty()){
                stack.add(curV);
            }else{
                if(stack.peek() > curV){
                    count += stack.size();
                    stack.add(curV);
                }else{
                    while (!stack.isEmpty() && stack.peek() <= curV){
                        stack.pop();
                    }
                    count += stack.size();
                    stack.add(curV);
                }
            }

        }
        System.out.println(count);
    }

}
