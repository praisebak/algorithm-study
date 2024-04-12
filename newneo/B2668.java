package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class B2668_second{
    int N;
    int[] arr;
    boolean[] visit;
    int answer = 0;
    List<Integer> answerList = new ArrayList<>();

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        visit = new boolean[N+1];
        for(int i=1;i<=N;i++){
            visit[i] = true;
            dfs(i,i,1);
            visit[i] = false;
        }

        answerList.sort(Comparator.naturalOrder());
        System.out.println(answerList.size());
        for(Integer result : answerList){
            System.out.println(result);
        }
    }

    public void dfs(int startIdx,int idx,int depth){
            int next = arr[idx];

            if(visit[next] && startIdx == next){
                answerList.add(idx);
                return;
            }else if(visit[next]){
                return;
            }

            visit[next] = true;
            dfs(startIdx,next,depth+1);
            visit[next] = false;


    }
}
