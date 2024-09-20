import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Node implements Comparable<Node> {
    int a;
    int b;
    int c;
    int depth;
    int sum;

    public Node(int a, int b, int c, int depth, int sum) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.depth = depth;
        this.sum = sum;
    }



    @Override
    public int compareTo(Node o) {
        if(depth - o.depth == 0){
            return sum - o.sum;
        }
        return depth - o.depth;

    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        //a b c 중복됐으면 ret
        boolean[][][] visit=  new boolean[61][61][61];
        String[] sArr =bufferedReader.readLine().split(" ");
        int a = Integer.parseInt(sArr[0]);
        int b = sArr.length > 1 ? Integer.parseInt(sArr[1]) : 0;
        int c = sArr.length > 2 ? Integer.parseInt(sArr[2]) : 0;

        int[][] cal = {{1,3,9},{1,9,3},{3,1,9},{3,9,1},{9,1,3},{9,3,1}};
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 6; i++) {
            int a1 = Math.max(a - cal[i][0],0);
            int b1 = Math.max(b - cal[i][1],0);
            int c1 = Math.max(c - cal[i][2],0);
            int depth = 1;
            int sum = a1 + b1 + c1;
            if(visit[a1][b1][c1]) continue;
            visit[a1][b1][c1] = true;
            priorityQueue.add(new Node(a1,b1,c1,depth,sum));
        }

        while (!priorityQueue.isEmpty()){
            Node node = priorityQueue.poll();
            if(node.sum <= 0){
                System.out.println(node.depth);
                return;
            }
            for(int i=0;i<6;i++){
                int a1 = Math.max(node.a - cal[i][0],0);
                int b1 = Math.max(node.b - cal[i][1],0);
                int c1 = Math.max(node.c - cal[i][2],0);
                int depth = node.depth;
                int sum = a1 + b1 + c1;
                if(visit[a1][b1][c1]) continue;
                visit[a1][b1][c1] = true;
                priorityQueue.add(new Node(a1,b1,c1,depth+1,sum));
            }
        }

    }
}
