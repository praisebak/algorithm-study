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
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[] arr = new int[N];
        String[] sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }


        boolean[] visit= new boolean[100001];
        long answer = 0;
        int left = 0;
        int leftSize = 0;

        for (int i = 0; i < N; i++) {
            int cur = arr[i];
            while (visit[cur]){
                visit[arr[left]] = false;
                left++;
                leftSize--;
            }
            visit[cur] = true;
            leftSize++;
            answer += leftSize;
        }
        System.out.println(answer);
        
    }
}
