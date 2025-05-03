import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Node{
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}


class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < N; j++) {
                char ch = s.charAt(j);
                map[i][j] = ch;
            }
        }
        boolean[][] visit = new boolean[N][N];

        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};
        int answer = 0;
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]) continue;
                if(map[i][j] == '0') continue;

                Queue<Node> queue = new LinkedList<>();
                answer++;
                queue.add(new Node(i,j));
                visit[i][j] = true;
                int count = 0;
                while (!queue.isEmpty()){
                    Node cur = queue.poll();
                    count++;
                    for (int k = 0; k < 4; k++) {
                        int nY = cur.y + dy[k];
                        int nX = cur.x + dx[k];
                        if(nY < 0 || nX >= N || nX < 0 || nY >= N) continue;
                        if(visit[nY][nX]) continue;
                        if(map[nY][nX] == '0') continue;
                        queue.add(new Node(nY,nX));
                        visit[nY][nX] = true;
                    }
                }
                answers.add(count);
            }
        }
        Collections.sort(answers);
        System.out.println(answer);
        System.out.println(answers.stream()
                        .map(String::valueOf)
                                .collect(Collectors.joining("\n")));
    }
}
