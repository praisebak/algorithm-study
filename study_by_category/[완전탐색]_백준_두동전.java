//https://www.acmicpc.net/problem/16197
import javax.management.monitor.CounterMonitorMBean;
import javax.swing.plaf.IconUIResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookiePolicy;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.CountedCompleter;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{


    private int N;
    private int M;
    class Coin{
        int y;
        int x;

        public Coin(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    class Game{
        Coin a;
        Coin b;
        int depth;

        public Game(Coin a, Coin b,int depth) {
            this.a = a;
            this.b = b;
            this.depth = depth;

        }
    }

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(strings[0]);
        M = Integer.parseInt(strings[1]);
        char[][] map = new char[N][M];

        Coin first = null;
        Coin second = null;

        for(int i=0;i<N;i++){
            String s = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char cur = s.charAt(j);
                if(cur == 'o'){
                    Coin coin = new Coin(i,j);
                    if(first == null){
                        first = coin;
                    }else{
                        second = coin;
                    }
                }

                map[i][j] = cur;
            }
        }
        Game game = new Game(first, second,0);

        Queue<Game> queue = new LinkedList<>();
        queue.add(game);

        while (!queue.isEmpty()){
            Game curGame = queue.poll();
            if(curGame.depth == 10) continue;
            for(int i=0;i<4;i++){
                int aY = dy[i] + curGame.a.y;
                int bY = dy[i] + curGame.b.y;

                int aX = dx[i] + curGame.a.x;
                int bX = dx[i] + curGame.b.x;


                boolean aValid = isValid(aY,aX);
                boolean bValid = isValid(bY,bX);

                if(aValid && !bValid ){
                    System.out.println(curGame.depth+1);
                    return;
                }
                if(!aValid && bValid ){
                    System.out.println(curGame.depth+1);
                    return;
                }
                if(!(aValid && bValid)) continue;

                Coin a = new Coin(curGame.a.y,curGame.a.x);
                Coin b = new Coin(curGame.b.y,curGame.b.x);

                if(map[aY][aX] != '#'){
                    a = new Coin(aY,aX);
                }
                if(map[bY][bX] != '#'){
                    b = new Coin(bY,bX);
                }
                queue.add(new Game(a,b,curGame.depth+1));

            }
        }
        System.out.println(-1);

    }

    private boolean isValid(int bY, int bX) {
        if(bY < 0 || bX < 0 || bY >= N || bX >= M) return false;
        return true;
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1} ;

}
