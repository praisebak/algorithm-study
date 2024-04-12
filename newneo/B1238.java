package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


class B4485 {


    class Node{
        int idx;
        int weight;

        Node(int idx,int weight){
            this.weight = weight;
            this.idx = idx;
        }
    }


    List<List<Node>> go = new ArrayList<>();
    List<List<Node>> back = new ArrayList<>();
    int N;
    int M;
    int X;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputSplit = br.readLine().split(" ");
        N = Integer.parseInt(inputSplit[0]);
        M = Integer.parseInt(inputSplit[1]);
        X = Integer.parseInt(inputSplit[2]);

        for(int i=0;i<N;i++) {
            go.add(new ArrayList<>());
            back.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            inputSplit = br.readLine().split(" ");
            int a;
            int b;
            int weight;
            a = Integer.parseInt(inputSplit[0]);
            b = Integer.parseInt(inputSplit[1]);
            weight = Integer.parseInt(inputSplit[2]);

            go.get(a).add(new Node(b,weight));
            back.get(b).add(new Node(a,weight));
        }

        int [] go = dijstra(go,x);


        int[] dist = new int[N+1];

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



