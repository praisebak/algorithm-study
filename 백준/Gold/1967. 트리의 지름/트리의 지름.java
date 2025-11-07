import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{

    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private List<List<Node>> list;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        list = new ArrayList<>();
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++){
            String[] sArr= bufferedReader.readLine().split(" ");
            int left = Integer.parseInt(sArr[0]);
            int right = Integer.parseInt(sArr[1]);
            int val = Integer.parseInt(sArr[2]);
            list.get(left).add(new Node(right,val));
        }

        dfs(1,0);
        System.out.println(answer);
    }

    private int dfs(int i,int sum) {
        int max = 0;
        List<Integer> results = new ArrayList<>();

        int oneMax = 0;
        for(Node next : list.get(i)){
            int result = dfs(next.idx,next.weight);
            results.add(result);
            oneMax = Math.max(result,oneMax);
            answer = Math.max(oneMax,answer);
        }

        //전체 트리중 best 계산용
        for(int i1 = 0;i1<results.size();i1++){
            for (int j = 0; j < results.size(); j++) {
                if(i1 == j) continue;
                max = Math.max(results.get(i1) + results.get(j),max);
                answer = Math.max(answer,max);
            }
        }

        if(list.get(i).size() == 0){
            return sum;
        }
        answer = Math.max(answer,oneMax + sum);
        return oneMax + sum;
    }

    int answer = 0;
    class Node{
        int idx;
        int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}
