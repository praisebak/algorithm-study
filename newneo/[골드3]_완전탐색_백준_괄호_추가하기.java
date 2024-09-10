import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    int N;
    int operN = 0;
    int[] arr;
    char[] operArr;
    public void solve() throws IOException {
        BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s =bufferedReader.readLine();
        N = Integer.parseInt(s);
        arr = new int[N];
        operArr = new char[N];

        s = bufferedReader.readLine();
        int numIdx = 0;
        int operIdx = 0;
        for(int i=0;i<N;i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                arr[numIdx++] = s.charAt(i) - '0';
            }else{
                operArr[operIdx++] = s.charAt(i);
            }
        }
        operN = N / 2;
        visit = new boolean[operN];

        dfs(0,arr[0]);
        System.out.println(answer);
    }
    boolean[] visit;
    int answer = Integer.MIN_VALUE;

    private void dfs(int operIdx,int sum){
        if(operIdx >= operN) {
            answer = Math.max(answer,sum);
            return;
        }

        int first = cal(operIdx,sum,arr[operIdx+1]);
        dfs(operIdx+1,first);

        //괄호치고 진행하기
        if(operIdx+1 < operN) {
            int second = cal(operIdx+1,arr[operIdx+1],arr[operIdx+2]);
            int result = cal(operIdx,sum,second);
            dfs(operIdx+2,result);
        }
    }

    private int cal(int operIdx, int sum, int nextNum) {
        char operand = operArr[operIdx];
        switch (operand) {
            case '*' :
                return sum * nextNum;
            case '/' :
                return sum / nextNum;
            case '+' :
                return sum + nextNum;
            case '-' :
                return sum - nextNum;
        }
        return 0;
    }
}
