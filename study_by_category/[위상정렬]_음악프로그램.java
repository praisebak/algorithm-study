import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    private int[] parent;
    private int[] weight;

    List<List<Integer>> nodeList = new ArrayList<>();
    private int n;
    private int m;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(sArr[0]);
        m = Integer.parseInt(sArr[1]);
        weight = new int[n +1];
        parent = new int[n +1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i <= n; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int size = Integer.parseInt(sArr[0]);
            int prev = -1;
            for (int j = 1; j <= size; j++) {
                int next = Integer.parseInt(sArr[j]);
                if(prev != -1){
                    weight[next]++;
                    nodeList.get(prev).add(next);
                }
                prev = next;
            }
        }

        bfs();
    }



    public void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if(weight[i] == 0){
                queue.add(i);
            }
        }

        if(queue.isEmpty()){
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()){
            int cur = queue.poll();
            result.add(cur);
            for(int next : nodeList.get(cur)){
                weight[next]--;
                if(weight[next] == 0){
                    queue.add(next);
                }
            }
        }

        if(result.size() != n){
            System.out.println(0);
            System.exit(0);
        }

        while (!result.isEmpty()){
            System.out.println(result.poll());
        }

    }
}
