import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
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
        int N =Integer.parseInt(bufferedReader.readLine());
        String s = bufferedReader.readLine();
        Stack<Integer> idxStack = new Stack<>();

        int answer = 0;
        boolean[] visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            char cur = s.charAt(i);
            if(cur == '('){
                idxStack.push(i);
            }else if(cur == ')' && !idxStack.isEmpty()){
                int idx = idxStack.pop();
                visit[i] = true;
                visit[idx] = true;
            }
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if(visit[i]) {
                sum++;
                answer = Math.max(sum,answer);
            }
            else sum = 0;
        }

        System.out.println(answer);

    }
}
