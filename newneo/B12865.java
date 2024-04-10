package newneo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


class B12865{

    private int N;
    private int W;
    private int V;
    private int maxV = 0;

    List<Node> list = new ArrayList<>();

    class Node{
        int w;
        int v;
        Node(int w,int v){
            this.w = w;
            this.v = v;

        }

    }

    public void solve(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        Node[] valArr = new Node[N+1];
        String line = null;
        try {
            line = bufferedReader.readLine();
            N = Integer.parseInt(line.split(" ")[0]);
            W = Integer.parseInt(line.split(" ")[1]);
            valArr = new Node[N+1];
            valArr[0] = new Node(0,0);
            for(int i=1;i<=N;i++){
                line = bufferedReader.readLine();
                int w = Integer.parseInt(line.split(" ")[0]);
                int v = Integer.parseInt(line.split(" ")[1]);
                Node node = new Node(w,v);
                valArr[i] = node;
            }
        } catch (IOException e) {

        }

        int[][] dp = new int[W+1][N+1];


        //i는 현재 내가 담을 수 있는weight
        for(int i=1;i<=W;i++){
            for(int j=1;j<=N;j++){
                Node n = valArr[j];

                //내가 담을수있는 양보다 같거나 작다면 비교시작
                if(n.w <= i){
                    dp[i][j] = Math.max(dp[i - n.w][j-1] + n.v
                            ,dp[i][j-1]);
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }

        //System.out.println(dp[W][N]);
    }


}

