package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


class B4485 {
    int N;
    int[][] arr;

    int[][] dp;

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    class Node{
        int y;
        int x;
        int weight;

        Node(int y,int x,int val){
            this.y = y;
            this.x = x;
            this.weight = val;
        }
    }

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;


        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) return;

            dp = new int[N][N];
            arr = new int[N][N];
            for(int i=0;i<N;i++){
                String[] inputSplit = br.readLine().split(" ");
                for(int j=0;j<N;j++){
                    arr[i][j] = Integer.parseInt(inputSplit[j]);
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }


            dp[0][0] = 0;
            PriorityQueue<Node> pq = new PriorityQueue<Node>((o1,o2) -> Integer.compare(o1.weight,o2.weight));
            pq.offer(new Node(0,0,arr[0][0]));

            while(!pq.isEmpty()){
                Node cur = pq.poll();

                for(int i=0;i<4;i++){
                    int nY = cur.y + dy[i];
                    int nX = cur.x + dx[i];
                    if(!isValid(nY,nX)) continue;

                    if(cur.weight + arr[nY][nX] < dp[nY][nX]){
                        dp[nY][nX] = cur.weight + arr[nY][nX];
                        pq.offer(new Node(nY,nX,dp[nY][nX]));
                    }
                }
            }

            System.out.println("Problem " + (count++) + ": " + dp[N-1][N-1]);
        }

    }

    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= N || nX >=N) return false;
        return true;
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        new B4485().solve();
    }
}



