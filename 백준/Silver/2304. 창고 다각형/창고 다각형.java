import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve {
    class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Node[] nodes = new Node[N];

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(sArr[0]);
            int y = Integer.parseInt(sArr[1]);
            nodes[i] = new Node(x, y);
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
        }

        Arrays.sort(nodes, Comparator.comparingInt(o -> o.x));

        int[] heights = new int[1001];  // x좌표는 1~1000까지 존재 가능
        for (Node node : nodes) {
            heights[node.x] = node.y;
        }

        // 왼쪽 -> 오른쪽 최대 높이
        int[] leftMax = new int[1001];
        int max = 0;
        for (int i = minX; i <= maxX; i++) {
            max = Math.max(max, heights[i]);
            leftMax[i] = max;
        }

        // 오른쪽 -> 왼쪽 최대 높이
        int[] rightMax = new int[1001];
        max = 0;
        for (int i = maxX; i >= minX; i--) {
            max = Math.max(max, heights[i]);
            rightMax[i] = max;
        }

        // 각 위치에서 지붕 높이 = 좌우 중 min
        int sum = 0;
        for (int i = minX; i <= maxX; i++) {
            sum += Math.min(leftMax[i], rightMax[i]);
        }

        System.out.println(sum);
    }
}
