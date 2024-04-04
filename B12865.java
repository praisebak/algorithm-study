import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class B12865{
    private int N;
    private int W;
    private int K;
    private int V;
    private int maxV;

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



        String line = null;
        try {
            line = bufferedReader.readLine();
            N = Integer.parseInt(line.split(" ")[0]);
            K = Integer.parseInt(line.split(" ")[1]);

            for(int i=0;i<N;i++){
                line = bufferedReader.readLine();
                int w = Integer.parseInt(line.split(" ")[0]);
                int v = Integer.parseInt(line.split(" ")[1]);
                Node node = new Node(w,v);
                list.add(node);
            }

            for(int i=0;i<N;i++){
                //이전에 선처리해주냐 나중에 후처리해주냐 차이가있음.
                //현재 인덱스,visit,총값
                //전처리를 더 선호
                Node curNode = list.get(i);
                if(curNode.w <= W){
                    dfs(i,curNode.v,curNode.w);
                }
            }

            System.out.println(dfs);

        } catch (IOException e) {

        }
    }


    void dfs(int index, int val,int weight){
        //무게초과
        if(weight + n.w > W) {
            maxV = Math.max(weight,maxV);
            return;
        }

        for(int i=index+1;i<N;i++){
            Node next = list.get(i);
            if(weight + next.w > W) continue;

            dfs(i+1,val + next.v,weight + next.w);
        }

    }

}

