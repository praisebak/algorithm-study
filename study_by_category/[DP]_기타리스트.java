import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{

    class Music{
        int v;
        int depth;

        public Music(int v, int depth) {
            this.v = v;
            this.depth = depth;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr =bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int S = Integer.parseInt(sArr[1]);
        int M = Integer.parseInt(sArr[2]);

        int[] arr = new int[N];
        sArr =bufferedReader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        PriorityQueue<Music> queue = new PriorityQueue<>((o1,o2) -> {
            if(o1.depth == o2.depth){
                return o2.v - o1.v;
            }
            return  o1.depth - o2.depth;
        });
        queue.add(new Music(S,0));
        boolean[][] visit = new boolean[N+1][2001];
        visit[0][M] = true;
        int answer = -1;
        while (!queue.isEmpty()){
            Music music = queue.poll();
            if(music.depth == N){
                answer = Math.max(music.v,answer);
                break;
            }
            int cur = arr[music.depth];
            int up = music.v + cur;
            int down = music.v - cur;
            if(up <= M){
                if(!visit[music.depth+1][up]){
                    queue.add(new Music(up, music.depth+1));
                    visit[music.depth+1][up] = true;
                }
            }
            if(down >= 0){
                if(!visit[music.depth+1][down]){
                    queue.add(new Music(down, music.depth+1));
                    visit[music.depth+1][down] = true;
                }
            }
        }
        System.out.println(answer);
    }
}
