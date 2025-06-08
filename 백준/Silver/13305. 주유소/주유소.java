
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] dist = new int[N + 1];
        String[] sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < N-1; i++) {
            dist[i+1] = Integer.parseInt(sArr[i]);
        }
        sArr = bufferedReader.readLine().split(" ");
        int[] money = new int[N+1];

        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(sArr[i]);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int spendMoney = 0;

        for (int i = 0; i < N; i++) {
            queue.add(money[i]);
            spendMoney += queue.peek() * dist[i+1];
        }

        System.out.println(spendMoney);
    }
}
