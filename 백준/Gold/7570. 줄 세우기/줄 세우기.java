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
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] sArr = bufferedReader.readLine().split(" ");
        int[] arr = new int[N+1];
        int[] position = new int[N+1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
            position[arr[i]] = i;
        }

        int answer = 1;
        for (int i = 0; i < N-1; i++) {
            int tmp = 1;
            int nextChild = arr[i]+1;

            int curI = i;
            if(nextChild >= N+1) continue;
            int nI = position[nextChild];

            while (curI < nI){
                tmp++;
                if(nextChild == N){
                    break;
                }

                curI = position[nextChild];
                nI = position[++nextChild];
            }

            answer = Math.max(answer,tmp);
        }
        System.out.println(N - answer);
    }
}
