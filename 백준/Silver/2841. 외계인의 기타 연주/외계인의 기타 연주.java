import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i <= 6 ; i++) {
            stacks.add(new Stack<>());
        }

        int answer = 0;
        for(int i=0;i<N;i++){
            sArr = bufferedReader.readLine().split(" " );
            int string = Integer.parseInt(sArr[0]);
            int p = Integer.parseInt(sArr[1]);
            Stack<Integer> stack = stacks.get(string);

            while (!stack.isEmpty() && stack.peek() > p){
                stack.pop();
                answer++;
            }
            if(!stack.isEmpty() && stack.peek() == p){
                continue;
            }

            stack.push(p);
            answer++;
        }

        System.out.println(answer);
    }
}
