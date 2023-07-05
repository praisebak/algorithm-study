import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B16235 {
    public static void main(String[] args) {
        SolveB16235 solveB16235 = new SolveB16235();

        solveB16235.init();
        solveB16235.solve();
        //iter test
    }








}


class Tree implements Comparable<Tree>{
    int age;
    int x;
    int y;

    @Override
    public int compareTo(Tree o) {
        return age - o.age;
    }
}

class SolveB16235{

    int[][] map;
    //양분
    int[][] eat;
    Deque<Tree> treeList = new LinkedList<>();
    ArrayList<Tree> deadTreeList = new ArrayList<>();


    int N,M,K;


    void init(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer s = new StringTokenizer(br.readLine());
            N = Integer.parseInt(s.nextToken());
            M = Integer.parseInt(s.nextToken());
            K = Integer.parseInt(s.nextToken());
            eat = new int[N+1][N+1];
            map = new int[N+1][N+1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(map[i],5);
            }
            for(int i=1;i<=N;i++){
                s = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    eat[i][j] = Integer.parseInt(s.nextToken());
                }
            }
            for (int i = 0; i < M; i++) {
                s = new StringTokenizer(br.readLine());
                Tree tree = new Tree();
                tree.y = Integer.parseInt(s.nextToken());
                tree.x = Integer.parseInt(s.nextToken());
                tree.age = Integer.parseInt(s.nextToken());
                treeList.add(tree);
            }
        }catch (IOException e){

        }

    }

    //대각선 왼쪽
    int[] dirY = {-1,-1,-1,0,0,1,1,1};
    int[] dirX = {-1,0,1,-1,1,-1,0,1};

    public void solve() {
        //K년 뒤
        for (int k = 0; k < K ; k++) {
            Deque<Tree> tmpList = new LinkedList<>();

            deadTreeList = new ArrayList<>();

            //봄
            //조건문 확인을 계속 한다 -> treeList.size가 동적으로 바뀐다
            for(int l = 0; l < treeList.size(); ){
                Tree t = treeList.poll();
                //양분 있는지
                int leftEat = map[t.y][t.x] - t.age;

                if(leftEat < 0){
                    deadTreeList.add(t);
                    continue;
                }

                map[t.y][t.x] -=  t.age;
                //나이 추가
                t.age++;
                l++;
                treeList.add(t);


                //번식 정보 저장 가을이었다
                if(t.age % 5 == 0){
                    for(int i=0;i<8;i++){
                        int nY = t.y + dirY[i];
                        int nX = t.x + dirX[i];

                        if(isValid(nY,nX)){
                            Tree nT = new Tree();
                            nT.age = 1;
                            nT.x = nX;
                            nT.y = nY;
                            tmpList.add(nT);
                        }
                    }
                }

            }

            //여름
            for(Tree t : deadTreeList){
                map[t.y][t.x] += Math.floor(t.age / 2);
            }


            //가을
            for (Tree t : tmpList) {
                treeList.addFirst(t);
            }

            //양분 주자!! 겨울이었다
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] += eat[i][j];
                }
            }

        }
        System.out.println(treeList.size());
    }

    private boolean isValid(int nY, int nX) {
        return nY > 0 && nX > 0 && nY <= N && nX <= N;
    }
}
