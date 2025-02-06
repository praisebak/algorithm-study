import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}


class Solve{

    int[][] weightMap = new int[20][400000];
    private int N;
    private int[][] map;

    public boolean isNewWeightBetter(int newWeight, int visit,int curIdx){
        if(weightMap[curIdx][visit] <= newWeight){
            return false;
        }
        return true;
    }

    public boolean checkValid(int visit, int nextWeight, int idx){
        int nextVisit = visit | 1 << idx;

        if(!isNewWeightBetter(nextWeight,nextVisit,idx)){
            return false;
        }
        return true;
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s =  bufferedReader.readLine();
        N = Integer.parseInt(s);
        for (int i = 0; i < 20; i++) {
            Arrays.fill(weightMap[i],987654321);
        }

        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int idx = 1;
            while (stringTokenizer.hasMoreTokens()){
                map[i][idx++] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        dfs(1,0,0,0,1);
        System.out.println(answer);
    }

    int answer = Integer.MAX_VALUE;
    private void dfs(int curIdx, int curWeight, int cityCount,int curVisit,int startIdx) {
        if(cityCount == N){
            if(map[curIdx][startIdx] == 0){
                return;
            }
            answer = Math.min(curWeight + map[curIdx][startIdx],answer);
            return;
        }


        for (int i = 1; i <= N; i++) {
            if(map[curIdx][i] == 0) continue;
            if((curVisit & (1 << i)) != 0) continue;
            if(checkValid(curVisit,curWeight + map[curIdx][i],i)){
                weightMap[curIdx][curVisit | (1 << i)] = curWeight + map[curIdx][i];
                dfs(i,curWeight + map[curIdx][i],cityCount+1,curVisit | 1 << i,startIdx);
            }
        }

    }
}

/**
 4
 0 10 15 20
 5 0 9 10
 6 13 0 12
 8 8 9 0
 */


//3
//0 10 1
//1 0 9
//7 13 0
