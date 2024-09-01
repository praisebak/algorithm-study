//https://www.acmicpc.net/problem/14889
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    private int N;
    private int[][] arr;
    private int min = Integer.MAX_VALUE;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N][N];

        for(int i=0;i<N;i++){
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        //depth, N/2일때 return,//visit
        boolean[] visit = new boolean[N];
        visit[0] = true;
        perm(1,visit,0);
        System.out.println(min);
    }

    private void perm(int depth, boolean[] visit,int prev) {
        if(depth == N / 2){
            cal(visit);
            return;
        }
        for(int i=prev+1;i<N;i++){
            if(visit[i]) continue;
            visit[i] = true;
            perm(depth+1,visit,i);
            visit[i] = false;
        }

    }

    private void cal(boolean[] visit) {
        List<Integer> firstTeam = new ArrayList<>();
        List<Integer> secondTeam = new ArrayList<>();

        for(int i=0;i<visit.length;i++){
            if(visit[i]) firstTeam.add(i);
            else secondTeam.add(i);
        }

        int sumA = 0;
        int sumB = 0;
        //1 2 모였음
        for (int i=0;i<firstTeam.size();i++) {
            int cur = firstTeam.get(i);
            for (int j = i+1; j < firstTeam.size(); j++) {
                int next = firstTeam.get(j);
                sumA += arr[cur][next];
                sumA += arr[next][cur];
            }
        }

        for (int i=0;i<secondTeam.size();i++) {
            int cur = secondTeam.get(i);
            for (int j = i+1; j < secondTeam.size(); j++) {
                int next = secondTeam.get(j);
                sumB += arr[cur][next];
                sumB += arr[next][cur];
            }
        }

        min = Math.min(min,Math.abs(sumA-sumB));
    }
}
