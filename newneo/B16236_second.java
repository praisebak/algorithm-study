package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class Main{
    public static void main(String[] args) throws IOException{
        new B16236_second().solve();

    }
}


class B16236_second{

    class Node{
        int x;
        int y;
        int time;
        Node(int y,int x,int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    int N;

    Node start = new Node(0,0,0);

    boolean[][] visit;
    int[][] arr;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        visit = new boolean[N+1][N+1];

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            int count = 0;
            for(String s: str){
                int val = Integer.parseInt(s);
                arr[i][count++] = val;
                if(val == 9){
                    start = new Node(i,count-1,0);
                }
            }
        }
        bfs();
    }
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};


    private boolean isValid(int nY,int nX){
        if(nY < 0 || nX < 0 || nX >= N || nY >=N){
            return false;
        }
        return true;
    }

    private void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        visit[start.y][start.x] = true;
        List<Node> canEatFishList = new ArrayList<>();
        int curSize = 2;
        int curTime = 0;

        Node curLocation = new Node(start.y,start.x,0);

        int eatCount = 0;
        boolean isEat = true;

        while(isEat){
            isEat = false;
            //여기서 더돌아야하는데
            while(!q.isEmpty()){
                Node cur = q.remove();
                for(int i=0;i<4;i++){
                    int nY = dy[i] + cur.y;
                    int nX = dx[i] + cur.x;

                    if(!isValid(nY,nX)) continue;
                    if(visit[nY][nX]) continue;
                    if(arr[nY][nX] > curSize) continue;
                    if(arr[nY][nX] == 9) continue;

                    visit[nY][nX] = true;


                    //먹을 물고기는 더이상 큐에안넣어도됨
                    if(arr[nY][nX] != curSize && arr[nY][nX] != 0){
                        canEatFishList.add(new Node(nY,nX,cur.time+1));
//                        System.out.println(nY + "," + nX + "를 먹을까?");


                    } else{
//                        System.out.println(nY + "," + nX + "를 넣었다");
                        q.add(new Node(nY,nX,cur.time+1));
                    }
                }
//                System.out.println();
            }


            //먹을게생겼다 그럼 판별 후 거기로 이동
            //또한 크기가 바뀌었으니 visit 초기화
            if(!canEatFishList.isEmpty()){
                isEat = true;
                Node answerFish = canEatFishList.get(0);

                for(int i=1;i<canEatFishList.size();i++){
                    Node curFish = canEatFishList.get(i);
                    if(curFish.time < answerFish.time ){
                        answerFish = curFish;
                    }else if(curFish.time == answerFish.time){
                        if(curFish.y < answerFish.y){
                            answerFish = curFish;
                        }else if(curFish.y == answerFish.y){
                            if(curFish.x < answerFish.x){
                                answerFish = curFish;
                            }
                        }
                    }
                }
                //이전에있던곳은 0으로
                arr[curLocation.y][curLocation.x] = 0;
                curTime = answerFish.time;
                arr[answerFish.y][answerFish.x] = 9;
                curLocation = new Node(answerFish.y,answerFish.x,0);
                q.clear();
                q.add(new Node(answerFish.y,answerFish.x,curTime));
                visit = new boolean[N][N];
                visit[answerFish.y][answerFish.x] = true;
                canEatFishList = new ArrayList<>();
                eatCount++;
                if(eatCount == curSize){
                    curSize++;
                    eatCount = 0;
                }
//                System.out.println(answerFish.y + "," + answerFish.x + "를 먹음");
            }
        }

        System.out.println(curTime);
    }

}



