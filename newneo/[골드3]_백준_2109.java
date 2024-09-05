//https://www.acmicpc.net/problem/2109
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solve{

    class Node implements Comparable<Node>{
        int day;
        int money;
        public Node(int day,int money){
            this.day = day;
            this.money = money;
        }

        @Override
        public int compareTo(Node o) {
            if(this.money == o.money){
                return day - o.day;
            }
            return o.money - money;
        }
    }

    int N;
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        N = Integer.parseInt(s);


        List<Node> nodeList = new ArrayList<>();
        for(int i=0;i<N;i++){
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
            int money = Integer.parseInt(stringTokenizer.nextToken());
            int day = Integer.parseInt(stringTokenizer.nextToken());
            Node node = new Node(day,money);
            nodeList.add(node);
        }

        Collections.sort(nodeList);


        int curSum = 0;
        boolean[] visit = new boolean[10001];
        for(int i=0;i< N;i++){
            Node cur = nodeList.get(i);
            for(int j=cur.day; j >= 1;j--){
                if(!visit[j]){
                    visit[j] = true;
                    curSum += cur.money;
                    break;
                }
            }
        }
        System.out.println(curSum);
    }

}

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
