import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    class Point{
        int y;
        int x;
        int count;
        boolean isWater;
        public Point(int y,int x,int count,boolean isWater){
            this.y=y;
            this.x=x;
            this.count = count;
            this.isWater = isWater;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        //물 먼저 이동
        //고슴도치 이동
        //물은 돌 통과 불가
        //고슴도치는 물 이동 불가
        //물은 비버의 소굴 이동 불가

        Point start = null;
        Point end = null;

        Queue<Point> queue = new LinkedList<>();
        char[][] map = new char[N][M ];
        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'S'){
                    start = new Point(i,j,0,false);
                }else if(map[i][j] == 'D'){
                    end = new Point(i,j,0,false);
                }
                if(map[i][j] == '*'){
                    queue.add(new Point(i,j,0,true));
                }
            }
        }
        queue.add(start);

        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};
        boolean[][] visit = new boolean[N][M];

        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            Point poll = queue.poll();
            if(poll.y == end.y && poll.x == end.x){
                answer = Math.min(answer,poll.count);
            }
            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if(nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if(visit[nY][nX]) continue;
                if(map[nY][nX] == 'X') continue;

                boolean isWater = poll.isWater;
                if (isWater && map[nY][nX] == 'D') continue;
                if(isWater){
                    map[nY][nX] = '*';
                }

                if(!isWater && (map[nY][nX] != '.' && map[nY][nX] != 'D')) continue;
                visit[nY][nX] = true;
                queue.add(new Point(nY,nX,poll.count+1,poll.isWater));
            }
        }

        if(answer == Integer.MAX_VALUE){
            System.out.println("KAKTUS");
            return;
        }
        System.out.println(answer);
    }
}

