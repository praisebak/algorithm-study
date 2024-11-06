import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main{
    public static void main(String[] args) throws IOException {
        SolvE solvE = new SolvE();
        solvE.solve();
    }
}

class SolvE{
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        HashMap<String,String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            s = bufferedReader.readLine().split(" ");
            map.put(s[0],s[1]);
        }
        for (int i = 0; i < M; i++) {
            s = bufferedReader.readLine().split(" ");
            System.out.println(map.get(s[0]));
        }

    }
}
