import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> graph = new ArrayList <>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        int[][] map = new int[N+1][N+1];

        for(int i=0;i<=N;i++){
            for(int j=0;j<=N;j++){
                if(i==j){
                    map[i][j] = 0;
                    continue;
                }
                map[i][j] = 987654321;
            }
        }

        while(true){
            String[] sArr = br.readLine().split(" ");
            int left = Integer.parseInt(sArr[0]);
            int right = Integer.parseInt(sArr[1]);
            if(left == -1 && right == -1){
                break;
            }

            map[left][right] = Math.min(1,map[left][right]);
            map[right][left] = Math.min(1,map[right][left]);
        }

        for(int k=0;k<=N;k++){
            for(int i=0;i<=N;i++){
                for(int j=0;j<=N;j++){
                    map[i][j] = Math.min(map[i][j],map[i][k] + map[k][j]);
                }
            }
        }

        int prevAnswer = Integer.MAX_VALUE;
        int currentAnswer = Integer.MAX_VALUE;
        List<Integer> sameList = new ArrayList<>();
        for(int i=1;i<=N;i++){
            currentAnswer = 0;

            for(int j=1;j<=N;j++){
                if(i == j) continue;
                currentAnswer = Math.max(currentAnswer,map[i][j]);
            }

            if(prevAnswer < currentAnswer){
                continue;
            }

            if(prevAnswer > currentAnswer){
                prevAnswer = currentAnswer;
                sameList = new ArrayList<>();
            }
            sameList.add(i);
        }

        StringBuilder sb = new StringBuilder(prevAnswer + " " + sameList.size()).append("\n");

        for(int i=0;i<sameList.size();i++){
            int current = sameList.get(i);
            sb.append(current + " ");
        }

        System.out.println(sb.toString());
    }
}
