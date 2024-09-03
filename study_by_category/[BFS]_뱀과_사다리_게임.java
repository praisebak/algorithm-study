import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    int N;
    int M;




    HashMap<Integer,Integer> ladderMap = new HashMap<>();
    HashMap<Integer,Integer> snakeMap = new HashMap<>();


    public void solve() throws IOException {

        //100개의 칸
        int[] arr = new int[101];

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(input," ");

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());


        for(int i=0;i<N;i++){

            input = bufferedReader.readLine();
            stringTokenizer = new StringTokenizer(input," ");
            int i1 = Integer.parseInt(stringTokenizer.nextToken());
            int j1 = Integer.parseInt(stringTokenizer.nextToken());
            ladderMap.put(i1,j1);
        }

        for(int i=0;i<M;i++){
            input = bufferedReader.readLine();
            stringTokenizer = new StringTokenizer(input," ");
            int i1 = Integer.parseInt(stringTokenizer.nextToken());
            int j1 = Integer.parseInt(stringTokenizer.nextToken());
            snakeMap.put(i1,j1);
        }

        bfs();

    }
    class Node{
        int cur;
        int next;

        public Node(int cur, int next) {
            this.cur = cur;
            this.next = next;
        }
    }

    class Game{
        int curIdx;
        int curDepth;


        public Game(int curIdx, int curDepth) {
            this.curIdx = curIdx;
            this.curDepth = curDepth;
        }
    }


    private void bfs() {

        Queue<Game> queue = new LinkedList<>();
        queue.add(new Game(1,0));


        boolean[] visit = new boolean[101];
        while(!queue.isEmpty()){
            Game cur = queue.poll();

            if(cur.curIdx == 100){
                System.out.println(cur.curDepth);
                return;
            }

            for(int i=1;i<=6;i++){
                int nextIdx = i + cur.curIdx;
                if(nextIdx > 100) break;


                if(snakeMap.get(nextIdx) != null){
                    nextIdx = snakeMap.get(nextIdx);
                    if(visit[nextIdx]) continue;
                    visit[nextIdx] = true;
                    queue.add(new Game(nextIdx,cur.curDepth+1));
                }else if(ladderMap.get(nextIdx) != null){
                    nextIdx = ladderMap.get(nextIdx);
                    if(visit[nextIdx]) continue;
                    visit[nextIdx] = true;
                    queue.add(new Game(nextIdx,cur.curDepth+1));
                }else{
                    if(visit[nextIdx]) continue;
                    visit[nextIdx] = true;
                    queue.add(new Game(nextIdx,cur.curDepth+1));
                }

            }

        }



    }
}
