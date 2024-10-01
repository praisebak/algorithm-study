import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }

}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr=  bufferedReader.readLine().split( " ");
        int A = Integer.parseInt(sArr[0]);
        int B = Integer.parseInt(sArr[1]);

        bfs(A,B);
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = B; i != -1; i = parent[i]) {
            stack.push(i);
            if(i == A) break;
        }
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pop() + " ");
        }
        System.out.println(stringBuilder);
    }
    class Node{
        public Node(int cur, int time) {
            this.cur = cur;
            this.time = time;
        }

        int cur;
        int time;
        String s;
    }

    public boolean isValid(int nY){
        if(nY < 0 || nY >= 100001) return false;
        return true;
    }
    int[] dy = {-1,1,2};

    int[] parent = new int[100001];
    private void bfs(int cur,int obj) {
        boolean[] visit = new boolean[100001];
        visit[cur] = true;

        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> o1.time - o2.time);
        queue.add(new Node(cur,0));
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            if(curNode.cur == obj){
                System.out.println(curNode.time);
                return;
            }
            for (int i = 0; i < 3; i++) {
                int nY = dy[i] + curNode.cur;
                if(i == 2) nY = dy[i] * curNode.cur;
                if(isValid(nY) && !visit[nY]){
                    visit[nY] = true;
                    queue.add(new Node(nY,curNode.time+1));
                    parent[nY] = curNode.cur;
                }
            }
        }
    }
}
