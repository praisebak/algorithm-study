
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main {
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int[] trace;
    private int[] dp;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        dp = new int[N+1];
        trace = new int[N+1];

        int next = N;
        StringBuilder stringBuilder = new StringBuilder(N + " ");
        dp[1] = 1;
        System.out.println(perm(N,0)-1);
        while (next != 1){
            next = trace[next];
            stringBuilder.append(next).append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

    private int perm(int num,int count) {
        if(dp[num] != 0){
            return dp[num];
        }

        int nextDP = Integer.MAX_VALUE;
        int nextNum = 0;

        if(num % 3 == 0){
            nextDP = Math.min(perm(num / 3,count+1),nextDP)+1;
            nextNum = num / 3;
        }

        if(num % 2 == 0){
            int cur  = perm(num / 2,count+1)+1;
            if(nextDP > cur){
                nextDP = cur;
                nextNum = num / 2;
            }
        }

        int cur = perm(num-1,count+1)+1;
        if(nextDP > cur){
            nextDP = cur;
            nextNum = num-1;
        }

        dp[num] = nextDP;
        trace[num] = nextNum;
        return nextDP;
    }
}

