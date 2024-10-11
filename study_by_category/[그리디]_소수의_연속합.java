import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

        boolean[] visit = new boolean[N+1];
        for (int i = 2; i <= N; i++) {
            if(visit[i]) continue;
            for (int j = i*2; j <=N ; j+=i) {
                visit[j] = true;
            }
        }

        List<Integer> pList = new ArrayList<>();
        for (int i = 2; i <= N ; i++) {
            if(!visit[i]) pList.add(i);
        }

        int answer = 0;
        for (int i = 0; i < pList.size(); i++) {
            int cur = pList.get(i);
            int j = i+1;

            if(cur == N) answer++;
            while (cur < N && j < pList.size()){
                cur += pList.get(j++);
                if(cur == N) answer++;
            }
        }
        System.out.println(answer);




    }
}
