import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        new Solve().solve();
    }
}

class Solve {
    private int endY, endX, startX, startY, N;
    private int[] xArr, yArr;
    private boolean[] visit;
    private boolean answer;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        yArr = new int[100];
        xArr = new int[100];

        for (int t = 0; t < T; t++) {
            answer = false;
            N = Integer.parseInt(bufferedReader.readLine());

            String[] sArr = bufferedReader.readLine().split(" ");
            startY = Integer.parseInt(sArr[1]);
            startX = Integer.parseInt(sArr[0]);

            for (int j = 0; j < N; j++) {
                sArr = bufferedReader.readLine().split(" ");
                xArr[j] = Integer.parseInt(sArr[0]);
                yArr[j] = Integer.parseInt(sArr[1]);
            }

            sArr = bufferedReader.readLine().split(" ");
            endY = Integer.parseInt(sArr[1]);
            endX = Integer.parseInt(sArr[0]);

            visit = new boolean[N];
            dfs(startY, startX);
            System.out.println(answer ? "happy" : "sad");
        }
    }

    private void dfs(int currY, int currX) {
        if (getDistance(currY, currX, endY, endX) <= 1000) {
            answer = true;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visit[i] && getDistance(currY, currX, yArr[i], xArr[i]) <= 1000) {
                visit[i] = true;
                dfs(yArr[i], xArr[i]);
            }
        }
    }

    private int getDistance(int y1, int x1, int y2, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }
}
