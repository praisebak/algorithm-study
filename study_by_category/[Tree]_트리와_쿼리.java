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
    List<List<Integer>> childList = new ArrayList<>();
    List<List<Integer>> graph = new ArrayList<>();
    private int N;
    private int root;
    private int q;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String[] sArr = s.split(" ");
        N = Integer.parseInt(sArr[0]);
        root = Integer.parseInt(sArr[1]);
        q = Integer.parseInt(sArr[2]);

        for (int i = 0; i <= 100001; i++) {
            childList.add(new ArrayList<>());
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        makeTree();
        for (int i = 0; i < q; i++) {
            int node = Integer.parseInt(bufferedReader.readLine());
            System.out.println(count[node]);
        }
                
    }

    private void makeTree() {
        boolean[] visit = new boolean[100001];
        int node = root;
        visit[node] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()){
            int next = queue.poll();
            for(Integer nextNode : graph.get(next)){
                if(visit[nextNode]){
                    continue;
                }
                visit[nextNode] = true;
                childList.get(next).add(nextNode);
                queue.add(nextNode);
            }
        }
        count[root] = dfs(root,0);
    }

    private int dfs(int root,int sum) {
        int curSum = sum;
        for(int n : childList.get(root)){
            sum += dfs(n,curSum);
        }
        count[root] = sum+1;
        return sum+1;
    }

    int[] count = new int[100001];

}
