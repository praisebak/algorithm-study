import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    int[] weight;
    List<List<Integer>> graph = new ArrayList<>();
    int sum = 0;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt( bufferedReader.readLine());
        weight = new int[N+1];
        String[] sArr = bufferedReader.readLine().split(" ");

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            weight[i] =Integer.parseInt(sArr[i-1]);
            sum += weight[i];
        }

        for (int i = 1; i <= N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int m = Integer.parseInt(sArr[0]);
            for (int j = 0; j < m; j++) {
                graph.get(i).add(Integer.parseInt(sArr[j+1]));
                graph.get(Integer.parseInt(sArr[j+1])).add(i);
            }
        }

        visit = new boolean[N+1];
        comb(0,0,0);
        System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private void comb(int prev, int depth,int sum) {
        if(depth != 0){
            boolean flag = false;
            for (int i = 1; i <= N; i++) {
                if(visit[i]){
                    int nextWeight = nextConnected2(i,N-depth);
                    if(nextWeight == -1) {
                        flag = true;
                        break;
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                if(flag) break;

                if(!visit[i]){
                    int nextWeight = nextConnected(i,depth);
//                    System.out.println(i + " 로 next 탐색 결과 = " + nextWeight);

                    if(nextWeight != -1) answer = Math.min(answer,Math.abs(nextWeight-sum));
                    break;
                }
            }
        }

        if(depth == N-1){
            return;
        }

        for (int i = prev+1; i <= N; i++) {
            visit[i] = true;
            comb(i,depth+1,sum + weight[i]);
            visit[i] = false;
        }


    }

    private int nextConnected2(int startIdx, int depth) {
        boolean[] curVisit = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startIdx);
        curVisit[startIdx] = true;
        int curDepth = 1;
        int sum = weight[startIdx];


        while (!queue.isEmpty()){
            int cur = queue.poll();
            for(int next : graph.get(cur)){
                if(!visit[next] || curVisit[next]) continue;
                curVisit[next] = true;
                queue.add(next);
                sum += weight[next];
                curDepth++;
            }
        }

        if(depth+curDepth == N) return sum;
        return -1;
    }

    private int nextConnected(int startIdx, int depth) {
        boolean[] curVisit = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startIdx);
        curVisit[startIdx] = true;
        int curDepth = 1;
        int sum = weight[startIdx];


        while (!queue.isEmpty()){
            int cur = queue.poll();
            for(int next : graph.get(cur)){
                if(visit[next] || curVisit[next]) continue;
                curVisit[next] = true;
                queue.add(next);
                sum += weight[next];
                curDepth++;
            }
        }

        if(depth+curDepth == N) return sum;
        return -1;
    }

    int answer = Integer.MAX_VALUE;
    boolean[] visit;
    int N;

}
