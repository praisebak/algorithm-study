import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException  {
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{

    private List<List<Integer>> nodeList;
    private int N;
    private int[] weight;
    private int[] degree;

    class Node{
        int idx;
        int weight;
        int time;

        public Node(int idx, int weight,int time) {
            this.idx = idx;
            this.weight = weight;
            this.time = time;
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < T; t++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(sArr[0]);
            int M = Integer.parseInt(sArr[1]);
            nodeList = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                nodeList.add(new ArrayList<>());
            }
            sArr = bufferedReader.readLine().split(" ");
            weight = new int[N +1];
            degree = new int[N+1];
            int idx= 1;
            for (String s : sArr ){
                weight[idx++] = Integer.parseInt(s);
            }

            for (int i = 0; i < M; i++) {
                sArr = bufferedReader.readLine().split(" ");
                int a = Integer.parseInt(sArr[0]);
                int b = Integer.parseInt(sArr[1]);
                degree[b]++;
                nodeList.get(a).add(b);
            }

            int end = Integer.parseInt(bufferedReader.readLine());
            topologi(end);
        }
    }

    private void topologi(int end) {
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[N+1];

        for (int i = 1; i <= N; i++) {
            if(degree[i] == 0){
                queue.add(i);
            }
            result[i] = weight[i];
        }

        while (!queue.isEmpty()){
            Integer cur = queue.poll();

            for(Integer next : nodeList.get(cur)){
                result[next] = Math.max(result[next],weight[next] + result[cur]);
                degree[next]--;
                if(degree[next] == 0){
                    queue.add(next);
                }
            }
        }

        System.out.println(result[end]);

    }
}
